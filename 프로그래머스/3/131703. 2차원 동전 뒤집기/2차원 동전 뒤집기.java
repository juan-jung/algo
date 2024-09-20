import java.util.*;
class Solution {
    int answer;
    public int solution(int[][] beginning, int[][] target) {
        answer = Integer.MAX_VALUE;
        
        dfs(0,0,beginning, target);
        
        return answer==Integer.MAX_VALUE ? -1 : answer;
    }
    
    void dfs(int row, int cnt, int[][] beginning, int[][] target) {
        if(row==beginning.length) {
            int col_change_cnt = 0;
            for(int i=0;i<beginning[0].length;i++) {
                int diff = 0;
                for(int j=0;j<beginning.length;j++) {
                    if(beginning[j][i]!=target[j][i]) diff++;
                }
                if(diff==beginning.length) col_change_cnt++;
                else if(diff!=0) return;
            }
            
            answer = Math.min(answer, cnt+col_change_cnt);
            return;
        }
        
        dfs(row+1,cnt,beginning,target);
        
        for(int i=0;i<beginning[row].length;i++) {
            beginning[row][i] = beginning[row][i]==0? 1:0;
        }
        dfs(row+1,cnt+1,beginning,target);
        for(int i=0;i<beginning[row].length;i++) {
            beginning[row][i] = beginning[row][i]==0? 1:0;
        }
    }
}