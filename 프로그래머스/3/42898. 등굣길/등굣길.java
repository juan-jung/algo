import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[n+1][m+1];
        boolean[][] puddle = new boolean[n+1][m+1];
        for(int i=0;i<puddles.length;i++) {
            puddle[puddles[i][1]][puddles[i][0]] = true;
        }
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(puddle[i][j]) continue;
                if(i==1 && j==1) map[i][j]=1;
                else map[i][j] = (map[i-1][j] + map[i][j-1])%1000000007;
            }
        }
        
        // for(int[] a : map) {
        //     System.out.println(Arrays.toString(a));
        // }
        
        return map[n][m];
    }
}