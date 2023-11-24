import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] sum = new int[N][M];
        for(int i=0;i<skill.length;i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 1) degree*= -1;
            
            sum[r1][c1] += degree;
            if(c2+1<M) sum[r1][c2+1] += degree*(-1);
            if(r2+1<N) sum[r2+1][c1] += degree*(-1);
            if(r2+1<N&&c2+1<M) sum[r2+1][c2+1] += degree;
        }
        
        
        for(int i=0;i<N;i++) {
            for(int j=1;j<M;j++) {
                sum[i][j] += sum[i][j-1];
            } 
        }
        
        for(int i=0;i<M;i++) {
            for(int j=1;j<N;j++) {
                sum[j][i] += sum[j-1][i];
            }
        }
        
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(sum[i][j] + board[i][j] > 0) answer++;        
            }
        }
        return answer;
    }
}

/*
-4 -1 0 0 1
2 0 -2 0 0
-2 0 0 0 2
2 0 0 0 -2

행
-4 -5 -5 -5 -4
2 2 0 0 0
-2 -2 -2 -2 0
2 2 2 2 0

열
-4 -5 -5 -5 -4
-2 -3 -5 -5 -4
-4 -5 -7 -7 -4
-2 -3 -5 -5 -4

합
1 0 0 0 1
3 2 0 0 1
1 0 -2 -2 1
3 2 0 0 1
*/