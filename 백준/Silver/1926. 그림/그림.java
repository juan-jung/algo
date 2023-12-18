import java.util.*;
import java.io.*;

public class Main {
	static int N,M,cnt,size;
	static boolean[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<M;j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 1) map[i][j] = true;
			}
		}
		cnt = 0;
		size = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]) {
					cnt++;
					size = Math.max(size, count(i,j));
				}
			}
		}
		System.out.println(cnt + "\n" + size);
		br.close();
	}
	
	static int count(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		map[x][y] = false;
		int size = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0;d<4;d++) {
				int nx = cur[0]+dx[d];
				int ny = cur[1]+dy[d];
				if(nx<0||nx>N-1||ny<0||ny>M-1||!map[nx][ny]) continue;
				q.offer(new int[] {nx,ny});
				map[nx][ny] = false;
				size++;
			}
			
		}
		return size;
	}
}
