import java.io.*;
import java.util.*;

public class Main {
	static int M,N;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] map, dp;
	static boolean[][] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = Integer.MIN_VALUE;
			}
		}
	
		System.out.println(dfs(0,0));
		
		br.close();
	}
	
	static int dfs(int x, int y) {
		if(x==M-1 && y==N-1) return 1;
		
		if(dp[x][y] != Integer.MIN_VALUE) return dp[x][y];
		
		dp[x][y] = 0;
	
		for(int d=0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx<0||nx>M-1||ny<0||ny>N-1||map[x][y] <= map[nx][ny]) continue;
			dp[x][y] += dfs(nx,ny);			
		}
		
		return dp[x][y];
	}
	
}

