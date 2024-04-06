

import java.io.*;
import  java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] box = new char[N][M];
        int[] init = new int[6]; // rx, ry, bx, by, cnt, d
        init[5] = -1;
        for(int i=0;i<N;i++) {
            String row = br.readLine();
            for(int j=0;j<M;j++) {
                box[i][j] = row.charAt(j);
                if(box[i][j] == 'R') {
                    init[0] = i;
                    init[1] = j;
                    box[i][j] = '.';
                }
                else if(box[i][j] == 'B') {
                    init[2] = i;
                    init[3] = j;
                    box[i][j] = '.';
                }
            }
        }

        //도달 할 수 있는 최대 지점 미리 계산
        int[][][] dist = new int[N][M][4];

        //상좌
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(box[i][j]=='#') {
                    dist[i][j][0] = -1;
                    dist[i][j][2] = -1;
                }
                else if(box[i][j]=='O') {
                    dist[i][j][0] = 0;
                    dist[i][j][2] = 0;
                }
                else {
                    dist[i][j][0] = dist[i-1][j][0] + 1;
                    dist[i][j][2] = dist[i][j-1][2]+1;
                }
            }
        }

        //하우
        for(int i=N-1;i>=0;i--) {
            for(int j=M-1;j>=0;j--) {
                if(box[i][j]=='#') {
                    dist[i][j][1] = -1;
                    dist[i][j][3] = -1;
                }
                else if(box[i][j]=='O') {
                    dist[i][j][1] = 0;
                    dist[i][j][3] = 0;
                }
                else {
                    dist[i][j][1] = dist[i+1][j][1]+1;
                    dist[i][j][3] = dist[i][j+1][3]+1;
                }
            }
        }

        int ans = -1;
        ArrayDeque<int[]> pq = new ArrayDeque<>();
        pq.offer(init);
        boolean[][][][] v = new boolean[N][M][N][M];
        v[init[0]][init[1]][init[2]][init[3]] = true;
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[4] > 10 || box[cur[2]][cur[3]]=='O') continue;

            if(box[cur[0]][cur[1]]=='O') {
                ans = cur[4];
                break;
            }

            for(int d=0;d<4;d++) {
                if((d==0&&cur[5]==1) || (d==1&&cur[5]==0) || (d==2&&cur[5]==3) || (d==3&&cur[5]==2)) continue;
                int[] moved = new int[6];
                moved[0] = cur[0] + dx[d]*dist[cur[0]][cur[1]][d];
                moved[1] = cur[1] + dy[d]*dist[cur[0]][cur[1]][d];
                moved[2] = cur[2] + dx[d]*dist[cur[2]][cur[3]][d];
                moved[3] = cur[3] + dy[d]*dist[cur[2]][cur[3]][d];


                if(moved[0]==moved[2] && moved[1]==moved[3]) {
                    if(box[moved[0]][moved[1]]=='O') continue;
                    if(dist[cur[0]][cur[1]][d] > dist[cur[2]][cur[3]][d]) {
                        moved[0]-=dx[d];
                        moved[1]-=dy[d];
                    }
                    else {
                        moved[2]-=dx[d];
                        moved[3]-=dy[d];
                    }
                }

                if(v[moved[0]][moved[1]][moved[2]][moved[3]]) continue;
                v[moved[0]][moved[1]][moved[2]][moved[3]] = true;
                moved[4]=cur[4]+1;
                moved[5]= d;
                pq.offer(moved);
            }
        }
        System.out.println(ans);
        br.close();
    }
}
