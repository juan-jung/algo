import java.io.*;
import java.util.*;

public class Main {
    static int max;
    public static void main(String[] args) throws Exception{
        int ans  = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //체스판의 크기,  10이하
        boolean[][] isAvailable =  new boolean[N][N];
        StringTokenizer st;
        int blackCnt = 0;
        int whiteCnt = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++)  {
                if(st.nextToken().equals("1")) {
                    isAvailable[i][j] = true;
                   if((i%2==0 && j%2==0)|| (i%2==1 && j%2==1)) blackCnt++;
                   else if((i%2==0 && j%2==1) || (i%2==1 && j%2==0))  whiteCnt++;
                }
            }
        }

        boolean[] row = new boolean[N*2];
        boolean[] col = new boolean[N*2];
        max = 0;
        countBishop(0,0,N,0,blackCnt,isAvailable,row,col);
        ans += max;

        max = 0;
        countBishop(0,1,N,0,whiteCnt,isAvailable,row,col);
        ans += max;
        System.out.println(ans);
    }

    static void countBishop(int x, int y, int N, int count, int availableCnt, boolean[][] isAvailable, boolean[] row, boolean[] col) {
        if(count + availableCnt <= max) {
            return;
        }

        if(y >= N) {
            x++;
            if(y%2==0) y=1;
            else y=0;
        }

        if(x==N) {
            max = Math.max(max,count);
            return;
        }

       if(isAvailable[x][y] && !row[x+y] && !col[x-y+N]) {
           row[x+y] = true;
           col[x-y+N] = true;
           countBishop(x,y+2,N,count+1,availableCnt-1,isAvailable,row,col);
           row[x+y] = false;
           col[x-y+N] = false;
       }
       countBishop(x,y+2,N,count,availableCnt,isAvailable,row,col);

    }
}

/*
칸에 비숍을 놓거나 놓지 않거나   -> 2^(10*10)  = 1024^1024 = 안되

검은 칸 / 흰 칸 나누기
2 * 2^(5*5) = 2* 2^25 = 2* 3.3554432E7 = 2* 34,000,000

  02
11  13
20  24



 */
