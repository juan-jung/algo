import java.util.*;

class Solution {
    final int INF = Integer.MAX_VALUE;
    public int solution(int[][] info, int n, int m) {
        int answer = INF;

        int[][][] dp = new int[info.length+1][n][m];
        for(int i=0;i<info.length+1;i++) {
            for(int j=0;j<n;j++) {
                for(int k=0;k<m;k++) {
                    dp[i][j][k] = INF;   
                }
            }
        }
        dp[0][0][0] = 0;
        
        for(int i=0;i<info.length;i++) {
            boolean flag = true;
            
             for(int a=0;a<n;a++) {
                 for(int b=0;b<m;b++) {
                     if(dp[i][a][b]==INF) continue;
                     if(a+info[i][0] < n) {
                         dp[i+1][a+info[i][0]][b] = Math.min(dp[i+1][a+info[i][0]][b], dp[i][a][b]+info[i][0]);
                     }
                     if(b+info[i][1] < m) {
                         dp[i+1][a][b+info[i][1]] = Math.min(dp[i+1][a][b+info[i][1]], dp[i][a][b]);
                     }
                     flag = false;
                 }
             } 
            
            if(flag) return -1;
        }
        
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                answer = Math.min(answer, dp[info.length][i][j]);
            }
        }
        
        return answer!=INF ? answer : -1;
    }
}