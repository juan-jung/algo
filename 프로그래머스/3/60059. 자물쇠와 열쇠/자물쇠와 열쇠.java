import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        int hole_cnt = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(lock[i][j]==0) hole_cnt++;
            }
        }
        
        int[][] new_lock = new int[n+2*m-2][n+m*2-2];
        for(int i=0;i<n+2*m-2;i++) Arrays.fill(new_lock[i], 100);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                new_lock[i+m-1][j+m-1] = lock[i][j];
            }
        }
        
        for(int d=0;d<4;d++) {
            if(d!=0) key = rotateKey(key,m);
            
            for(int i=0;i<n+m-1;i++) {
                end:for(int j=0;j<n+m-1;j++) {
                    int filled = 0;
                    for(int x=0;x<m;x++) {
                        for(int y=0;y<m;y++) {
                            if(new_lock[x+i][y+j]==100) continue;
                            if(key[x][y]==new_lock[x+i][y+j]) continue end;
                            if(key[x][y]==1) filled++;
                        }
                    }
                    if(hole_cnt == filled) return true;
                }
            }
        }
        return false;
    }
    
    int[][] rotateKey(int[][] key, int m) {
        int[][] temp = new int[m][m];
        for(int i=0;i<m;i++) {
            for(int j=m-1;j>=0;j--) {
                temp[i][m-1-j] = key[j][i];
            }
        }
        return temp;
    }
}