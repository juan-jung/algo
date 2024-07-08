import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = 0;
        
        int x = 0;
        int y = 0;
        
        end:for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length();j++) {
                if(board[i].charAt(j)=='R') {
                    x = i;
                    y = j;
                    break end;
                }
            }
        }
        
        boolean[][] v = new boolean[board.length][board[0].length()];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y,0});
        v[x][y] = true;
        
        int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(board[cur[0]].charAt(cur[1])=='G') {
                answer = cur[2];
                break;
            }
            
            for(int d=0;d<4;d++) {
                int nx = cur[0];
                int ny = cur[1];
                while(nx>=0&&nx<board.length&&ny>=0&&ny<board[0].length()&&board[nx].charAt(ny)!='D') {
                    nx += dx[d];
                    ny += dy[d];
                }
                nx -= dx[d];
                ny -= dy[d];
                
                if(nx==cur[0] && ny==cur[1]) continue;
                if(v[nx][ny]) continue;
                
                q.offer(new int[]{nx,ny,cur[2]+1});
                v[nx][ny] = true;
            }
            
        }
        
        
        return answer == 0 ? -1 : answer;
    }
}