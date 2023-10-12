import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static class Core {
		int x;
		int y;
		public Core (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "[Core] x : "+this.x+", y: "+this.y;
		}
	}
	
	static int[][] map;
	static ArrayList<Core> cores;
	static int N, min;
	static int[] selected;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1 && i!=0 && i!=N-1 && j!=0 && j!=N-1) {
						cores.add(new Core(i,j));
					}
				}
			}
			
			min = Integer.MAX_VALUE;

			for(int i=cores.size();i>0;i--) {
				selected = new int[i];
				comb(0,0,i);
				if(min!=Integer.MAX_VALUE) break;
			}
			
			System.out.println("#"+tc+" "+min);
		}
	}
	
	public static void comb(int cnt, int start, int pick) {
		if(cnt == pick) {
			calc(0,0);
			return;
		}
		for(int i=start;i<cores.size();i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1, pick);
		}
	}
	
	public static void calc(int cnt, int sum) {
		if(cnt == selected.length) {
			min = Math.min(min, sum);
			return;
		}
		//길로 갈 수 있으면 다음
		//갈 수 없으면 원상복구
		Core cur = cores.get(selected[cnt]);
		for(int d=0;d<4;d++) {
			int x = cur.x;
			int y = cur.y;
			boolean flag = true;
			int count = 0;
			while(flag) {
				x += dx[d];
				y += dy[d];
				if(x==-1||x==N||y==-1||y==N) break;
				if(map[x][y] == 1) flag = false;
				else {
					map[x][y] = 1;
					count++;
				}
			}
			
			if(flag) {
				calc(cnt+1, sum+count);
			}
			
			//되돌리기
			for(int i=0;i<count;i++) {
				x-=dx[d];
				y-=dy[d];
				map[x][y] = 0;
			}
		}
		
	}
}
