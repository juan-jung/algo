import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        List<Integer>[] g = new List[n+1];
        int[] dist = new int[n+1];
        for(int i=0;i<n+1;i++) {
            g[i] = new ArrayList<Integer>();
            dist[i] = Integer.MAX_VALUE;
        }
        for(int i=0;i<roads.length;i++) {
            g[roads[i][0]].add(roads[i][1]);
            g[roads[i][1]].add(roads[i][0]);
        }
        dist[destination] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        pq.offer(new int[]{destination, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(Integer target : g[cur[0]]) {
                if(dist[target] > cur[1] + 1) {
                    dist[target] = cur[1] + 1;
                    pq.offer(new int[]{target, dist[target]});
                }
            }
        }
        
        answer = new int[sources.length];
        for(int i=0;i<answer.length;i++) {
            answer[i] = dist[sources[i]];
            if(answer[i] == Integer.MAX_VALUE) answer[i] = -1;
        }
        return answer;
    }
}