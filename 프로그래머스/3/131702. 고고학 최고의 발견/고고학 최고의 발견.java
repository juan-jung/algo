import java.util.*;
class Solution {
    int answer;
    int[] dx = {0,-1,1,0,0}, dy = {0,0,0,-1,1};
    public int solution(int[][] clockHands) {
        answer = Integer.MAX_VALUE;
        findMin(0,0,0,clockHands);
        return answer;
    }
    
    void rotate(int x, int y, int degree, int[][] clockHands) {
        for(int d=0;d<5;d++) {
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx<0||nx>=clockHands.length||ny<0||ny>=clockHands.length) continue;
            clockHands[nx][ny] = (clockHands[nx][ny]+degree)%4;
        }
    }
    
    void findMin(int x, int y, int cnt, int[][] clockHands) {
        if(y==clockHands.length) {
            y=0;
            x++;
        }
        if(x==clockHands.length) {
            for(int i=0;i<clockHands.length;i++) {
                if(clockHands[x-1][i]!=0) return;
            }
            
            answer = Math.min(answer,cnt);
            return;
        }
        
        if(x==0) {
            for(int i=0;i<4;i++) {
                rotate(x,y,i,clockHands);
                findMin(x,y+1,cnt+i,clockHands);
                rotate(x,y,-i+4,clockHands);
            }
        }
        else {
            int rotate_cnt = (4-clockHands[x-1][y])%4;
            rotate(x,y,rotate_cnt,clockHands);
            findMin(x,y+1,cnt+rotate_cnt,clockHands);
            rotate(x,y,-rotate_cnt+4,clockHands);
        }
    }
}