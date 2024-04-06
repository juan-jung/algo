import java.io.*;
import  java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] box = new char[N][M];
        int[] init = new int[5]; // rx, ry, bx, by, cnt
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
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int[][][] dist = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == '#' || box[i][j] == 'O') continue; // 벽이거나 구멍이면 건너뛰기
                for (int d = 0; d < 4; d++) {
                    int nx = i, ny = j;
                    while (true) {
                        nx += dx[d];
                        ny += dy[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M || box[nx][ny] == '#') break; // 벽을 만나거나 범위를 벗어나면 중지
                        dist[i][j][d]++;
                        if (box[nx][ny] == 'O') break; // 구멍에 도착하면 중지
                    }
                }
            }
        }

        int ans = -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[4],o2[4])));
        pq.offer(init);
        boolean[][][][] v = new boolean[N][M][N][M];
        v[init[0]][init[1]][init[2]][init[3]] = true;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[4] > 10 || box[cur[2]][cur[3]]=='O') continue;

            if(box[cur[0]][cur[1]]=='O') {
                ans = cur[4];
                break;
            }

            for(int d=0;d<4;d++) {
                int[] moved = new int[5];
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
                pq.offer(moved);
            }
        }
        System.out.println(ans);
        br.close();
    }
}
