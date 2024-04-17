import java.io.*;
import java.util.*;

public class Main {
    /*
    시간제한 2초

    자신보다 낮은 대나무 칸으로 이동
    이동할 수 있는 칸이 있고, 방문한적이 있다면 해당칸+=방문칸+1
    이동할 수 있는 칸이 있고, 방문한적이 없다면 해당칸+=1

    14 9 12 10
    1 11 5 4
    7 15 2 13
    6 3 16 8

    1 0 1 0
    0 0 0 0
    0 0 0 0
    0 0 0 0

    t t t f
    t f f f
    f f f f
    f f f f

    n : 1-500
    대나무양 : 백만이하 정수
     */
    static int ans;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<n;j++) map[i][j]=Integer.parseInt(st.nextToken());
        }

        ans = 0;
        getMaxPath(n,map);

        System.out.println(ans+1);
        br.close();
    }

    static void dfs(int x, int y, int n, int[][] map, int[][] count, boolean[][] v) {
        v[x][y] = true;

        for(int d=0;d<4;d++) {
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx<0||nx>n-1||ny<0||ny>n-1||map[nx][ny] >= map[x][y]) continue;
            if(!v[nx][ny]) dfs(nx,ny,n,map,count,v);
            count[x][y] = Math.max(count[x][y], count[nx][ny]+1);
        }

        ans = Math.max(ans, count[x][y]);
    }

    static void getMaxPath(int n, int[][] map) {
        boolean[][] v = new boolean[n][n];
        int[][] count = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(v[i][j]) continue;
                dfs(i,j,n,map,count,v);
            }
        }
    }
}
