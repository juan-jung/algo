import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int W, H, answer;
    static int[][] map;

    //좌상 우상 좌 우 좌하  좌우
    //홀수행일경우 -1 0 -1 1 -1 0
    //짝수행일경우 0 +1 -1 1 0 +1
    static int[] dx = {-1, -1, 0, 0, 1, 1};
    static int[][] dy = {{0, 1, -1, 1, 0, 1}, {-1, 0, -1, 1, -1, 0}};

    public static void main(String[] args) throws Exception {
        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[W][H];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < H; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] v = new boolean[W][H];

        for (int i = 0; i < H; i++) {
            if (map[0][i] == 0) {
                if (!v[0][i]) countWall(0, i, v);
            }
            if (map[0][i] == 1) {
                answer += 2;
            }

            if (map[W - 1][i] == 0) {
                if (!v[W - 1][i])
                    countWall(W - 1, i, v);
            } else if (map[W - 1][i] == 1) {
                answer += 2;
            }
        }

        for (int i = 0; i < W; i++) {

            if (map[i][0] == 0) {
                if (!v[i][0])
                    countWall(i, 0, v);
            } else if (map[i][0] == 1) {
                answer += i % 2 == 0 ? 1 : 3;
            }

            if (map[i][H - 1] == 0) {
                if (!v[i][H - 1])
                    countWall(i, H - 1, v);
            } else if (map[i][H - 1] == 1) {
                answer += i % 2 == 0 ? 3 : 1;
            }
        }


//        if(map[0][0]==1) answer--;
//        if(map[W-1][H-1]==1) answer--;
        if(map[0][H-1]==1) answer--;
        if(map[W-1][0]==1) answer--;
        System.out.println(answer);
    }

    static void countWall(int x, int y, boolean[][] v) {
        if (v[x][y]) return;
        v[x][y] = true;
        for (int d = 0; d < 6; d++) {
            int nx = x + dx[d];
            int ny = y + dy[x % 2][d];
            if (nx < 0 || nx > W - 1 || ny < 0 || ny > H - 1 || v[nx][ny]) continue;
            if (map[nx][ny] == 1) answer++;
            else if (map[nx][ny] == 0) countWall(nx, ny, v);
        }
    }
}
