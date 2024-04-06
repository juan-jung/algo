import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] box;
    static int[][][] distance; // [row][col][direction] -> 최대 이동 가능 거리
    static final int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        box = new char[N][M];
        distance = new int[N][M][4]; // 최대 이동 거리 배열 초기화

        int[] init = new int[6]; // rx, ry, bx, by, cnt, d
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                box[i][j] = row.charAt(j);
                if (box[i][j] == 'R') {
                    init[0] = i;
                    init[1] = j;
                    box[i][j] = '.';
                } else if (box[i][j] == 'B') {
                    init[2] = i;
                    init[3] = j;
                    box[i][j] = '.';
                }
            }
        }

        calculateDistance(); // 각 칸에서 각 방향으로의 최대 이동 거리 계산

        int ans = bfs(init); // BFS 실행
        System.out.println(ans);
        br.close();
    }

    static void calculateDistance() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == '#' || box[i][j] == 'O') continue; // 벽이거나 구멍이면 건너뛰기
                for (int d = 0; d < 4; d++) {
                    int nx = i, ny = j;
                    while (true) {
                        nx += dx[d];
                        ny += dy[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M || box[nx][ny] == '#') break; // 벽을 만나거나 범위를 벗어나면 중지
                        distance[i][j][d]++;
                        if (box[nx][ny] == 'O') break; // 구멍에 도착하면 중지
                    }
                }
            }
        }
    }

    static int bfs(int[] init) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[4], o2[4]));
        boolean[][][][] visited = new boolean[N][M][N][M]; // [rx][ry][bx][by] 방문 여부
        pq.offer(init);

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[4] > 10) continue; // 10번 초과 이동시 실패
            if (box[cur[2]][cur[3]] == 'O') continue; // 파란 구슬이 구멍에 빠지면 실패
            if (box[cur[0]][cur[1]] == 'O') return cur[4]; // 빨간 구슬이 구멍에 들어가면 성공

            for (int d = 0; d < 4; d++) {
                int rx = cur[0], ry = cur[1], bx = cur[2], by = cur[3];
                int rMove = distance[rx][ry][d], bMove = distance[bx][by][d]; // 각 구슬이 이동할 거리

                // 구슬 이동
                int nrx = rx + dx[d] * rMove, nry = ry + dy[d] * rMove;
                int nbx = bx + dx[d] * bMove, nby = by + dy[d] * bMove;

                // 이동 후 두 구슬이 같은 위치에 있고 구멍에 빠지지 않은 경우
                if (nrx == nbx && nry == nby && box[nrx][nry] != 'O') {
                    // 이동 거리가 더 긴 구슬을 한 칸 뒤로
                    if (rMove > bMove) {
                        nrx -= dx[d];
                        nry -= dy[d];
                    } else {
                        nbx -= dx[d];
                        nby -= dy[d];
                    }
                }

                if (visited[nrx][nry][nbx][nby]) continue; // 이미 방문한 상태면 건너뛰기
                visited[nrx][nry][nbx][nby] = true;
                pq.offer(new int[]{nrx, nry, nbx, nby, cur[4] + 1});
            }
        }
        return -1; // 실패
    }
}
