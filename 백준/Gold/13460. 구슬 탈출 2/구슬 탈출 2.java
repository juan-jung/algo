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
        init[5] = 4; // 0,0
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

        int ans = -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[4],o2[4])));
        pq.offer(init);
        int[] dx = {-1,1,0,0,0};
        int[] dy = {0,0,-1,1,0};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[4] > 10 || box[cur[2]][cur[3]]=='O') continue;

            if(box[cur[0]][cur[1]]=='O') {
                if(cur[2]+dx[cur[5]] == cur[0] && cur[3]+dy[cur[5]] == cur[1]) continue;
                ans = cur[4];
                break;
            }

            for(int d=0;d<4;d++) {
               int[] moved = Arrays.copyOf(cur, 6);
               //둘다 움직이고 같은 자리면 cur위치로 판단
                int nx = moved[0];
                int ny = moved[1];
                while(true) {
                    nx+=dx[d];
                    ny+=dy[d];
                    if(nx<0||nx>N-1||ny<0||ny>M-1||box[nx][ny]=='#') {
                        moved[0] = nx-dx[d];
                        moved[1] = ny-dy[d];
                        break;
                    }
                    else if(box[nx][ny]=='O') {
                        moved[0] = nx;
                        moved[1] = ny;
                        break;
                    }
                }

                nx = moved[2];
                ny = moved[3];
                while(true) {
                    nx+=dx[d];
                    ny+=dy[d];
                    if(nx<0||nx>N-1||ny<0||ny>M-1||box[nx][ny]=='#') {
                        moved[2] = nx-dx[d];
                        moved[3] = ny-dy[d];
                        break;
                    }
                    else if(box[nx][ny]=='O') {
                        moved[2] = nx;
                        moved[3] = ny;
                        break;
                    }
                }

                if(moved[0]==moved[2] && moved[1]==moved[3]) {
                    if(d==0) {
                        if(cur[0] > cur[2]) {
                            moved[0]-=dx[d];
                            moved[1]-=dy[d];
                        }
                        else {
                            moved[2]-=dx[d];
                            moved[3]-=dy[d];
                        }
                    }
                    else if(d==1) {
                        if(cur[0] > cur[2]) {
                            moved[2]-=dx[d];
                            moved[3]-=dy[d];
                        }
                        else {
                            moved[0]-=dx[d];
                            moved[1]-=dy[d];
                        }
                    }
                    else if(d==2) {
                        if(cur[1] > cur[3]) {
                            moved[0]-=dx[d];
                            moved[1]-=dy[d];
                        }
                        else {
                            moved[2]-=dx[d];
                            moved[3]-=dy[d];
                        }
                    }
                    else if(d==3) {
                        if(cur[1] > cur[3]) {
                            moved[2]-=dx[d];
                            moved[3]-=dy[d];
                        }
                        else {
                            moved[0]-=dx[d];
                            moved[1]-=dy[d];
                        }
                    }
                }

                moved[4]++;
                moved[5] = d;
                pq.offer(moved);
            }
        }
        System.out.println(ans);
        br.close();
    }
}
