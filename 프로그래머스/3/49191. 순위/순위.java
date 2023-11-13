import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] map = new int[n+1][n+1];
        for(int i=0;i<results.length;i++) {
            map[results[i][0]][results[i][1]] = 1;
            map[results[i][1]][results[i][0]] = -1;
        }
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i==j) continue;
                for(int k=1;k<=n;k++) {
                    if(i==k || j==k) continue;
                    if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                    else if(map[i][k] == -1 && map[k][j] == -1) map[i][j] = -1;
                }
            }
        }
        
         for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i==j) continue;
                for(int k=1;k<=n;k++) {
                    if(i==k || j==k) continue;
                    if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                    else if(map[i][k] == -1 && map[k][j] == -1) map[i][j] = -1;
                }
            }
        }
        for(int[] a : map) {
            int cnt =0;
            for(int b:a) if(b!=0) cnt++;
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}

   
    