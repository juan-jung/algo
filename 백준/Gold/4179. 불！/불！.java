import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			if(o1[2]==o2[2]) return Integer.compare(o1[3], o2[3]);
			return Integer.compare(o1[2], o2[2]);
		}); //x y cnt P(1)orF(0)
		boolean[][] v = new boolean[R][C];
		for(int i=0;i<R;i++) {
			String row = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'J') {
					pq.offer(new int[] {i,j,1,1});
					v[i][j]= true;
				}
				else if(map[i][j] == 'F') {
					pq.offer(new int[] {i,j,1,0});
				}
			}
		}
        
		int ans = -1;
		int[]  dx = {-1,1,0,0}, dy = {0,0,-1,1};
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
//			System.out.println((cur[3]==0 ? "불" : "지훈") + " x : " + cur[0] + ", y : " + cur[1] + ", cnt : " + cur[2]);
			
			if(cur[3]==1 && (cur[0]==0||cur[0]==R-1||cur[1]==0||cur[1]==C-1)) {
				ans = cur[2];
				break;
			}
			
			for(int d=0;d<4;d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if(nx<0||nx>R-1||ny<0||ny>C-1||map[nx][ny]=='#'|map[nx][ny]=='F') continue;
				if(cur[3]==1) {
					if(v[nx][ny]) continue;
					v[nx][ny] = true;
				}
				else {
					map[nx][ny] = 'F';
				}
				pq.offer(new int[] {nx, ny, cur[2]+1, cur[3]});
			}
		}
		
//		for(char[] a: map) System.out.println(Arrays.toString(a));
		System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
		br.close();
	}
	
}
