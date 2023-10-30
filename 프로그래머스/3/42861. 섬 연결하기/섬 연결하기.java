import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        ArrayList<int[]>[] g = new ArrayList[n];
        int[] dp = new int[n];
        for(int i=0;i<n;i++) {
            g[i] = new ArrayList<int[]>();
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i=0;i<costs.length;i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];
            g[start].add(new int[]{end, cost});
            g[end].add(new int[]{start, cost});
        }
        
        boolean[] v = new boolean[n];
        dp[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        pq.offer(new int[]{0,0});
        int cnt = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(v[cur[0]]) continue;
            
            v[cur[0]] = true;
            answer += cur[1];
            
            if(cnt++ == n-1) break;
            
            for(int[] target : g[cur[0]]) {
                if(v[target[0]] || dp[target[0]] <= target[1]) continue;
                dp[target[0]] = target[1];
                pq.offer(new int[]{target[0], target[1]});
            }
        }
        
        
        return answer;
    }
}