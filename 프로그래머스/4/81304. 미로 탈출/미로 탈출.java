import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int index;
        int cost;
        int state;

        Node(int index, int cost, int state) {
            this.index = index;
            this.cost = cost;
            this.state = state;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int INF = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2], 0});
            graph.get(road[1]).add(new int[]{road[0], road[2], 1});
        }

        Set<Integer> trapSet = new HashSet<>();
        for (int trap : traps) {
            trapSet.add(trap);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        Map<String, Integer> dist = new HashMap<>();
        dist.put(start + "," + 0, 0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curNode = current.index;
            int curCost = current.cost;
            int curState = current.state;

            if (curNode == end) {
                return curCost;
            }

            for (int[] edge : graph.get(curNode)) {
                int nextNode = edge[0];
                int nextCost = edge[1];
                int direction = edge[2];

                boolean curNodeIsTrap = trapSet.contains(curNode);
                boolean nextNodeIsTrap = trapSet.contains(nextNode);

                int actualDirection = direction;
                if (curNodeIsTrap && nextNodeIsTrap) {
                    actualDirection = (curState & (1 << (trapSet.size() - 1 - (curNode - 1)))) != 0 ? direction ^ 1 : direction;
                    actualDirection = (curState & (1 << (trapSet.size() - 1 - (nextNode - 1)))) != 0 ? actualDirection ^ 1 : actualDirection;
                } else if (curNodeIsTrap) {
                    actualDirection = (curState & (1 << (trapSet.size() - 1 - (curNode - 1)))) != 0 ? direction ^ 1 : direction;
                } else if (nextNodeIsTrap) {
                    actualDirection = (curState & (1 << (trapSet.size() - 1 - (nextNode - 1)))) != 0 ? direction ^ 1 : direction;
                }

                if (actualDirection == 0) {
                    int nextState = curState;
                    if (nextNodeIsTrap) {
                        nextState ^= 1 << (trapSet.size() - 1 - (nextNode - 1));
                    }
                    String nextKey = nextNode + "," + nextState;
                    int totalCost = curCost + nextCost;

                    if (!dist.containsKey(nextKey) || dist.get(nextKey) > totalCost) {
                        dist.put(nextKey, totalCost);
                        pq.offer(new Node(nextNode, totalCost, nextState));
                    }
                }
            }
        }

        return INF;
    }
}
