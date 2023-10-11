import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] v = new boolean[n];
        
        for(int i=0;i<n;i++) {
            if(v[i]) continue;
            answer++;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.offer(i);
            while(!q.isEmpty()){
                int cur = q.poll();
                
                for(int j=0;j<n;j++) {
                    if(v[j] || computers[cur][j] == 0 || i==j) continue;
                    q.offer(j);
                    v[j] = true;
                }
            }
        }
        
        
        return answer;
    }
}