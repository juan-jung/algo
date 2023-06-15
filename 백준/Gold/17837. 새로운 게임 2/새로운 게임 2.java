import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N,K,max_horse;
	static int[] dx= {0,0,-1,1};
	static int[] dy= {1,-1,0,0};
	static int[] rd= {1,0,3,2};
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		int[][] map=new int[N][N];
		ArrayDeque<Integer>[][] hmap=new ArrayDeque[N][N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				hmap[i][j]=new ArrayDeque<>();
			}
		}
		int[][] horses=new int[K][4];
		for(int i=0;i<K;i++) {
			st=new StringTokenizer(br.readLine()," ");
			horses[i][0]=Integer.parseInt(st.nextToken())-1;
			horses[i][1]=Integer.parseInt(st.nextToken())-1;
			horses[i][2]=Integer.parseInt(st.nextToken())-1;//우좌상하
			hmap[horses[i][0]][horses[i][1]].offer(i);
		}
		int turn=0;
		end : while(turn <= 1000) {
			turn++;
			for(int i=0;i<K;i++) {
				int nx=horses[i][0]+dx[horses[i][2]], ny=horses[i][1]+dy[horses[i][2]];
				if(nx<0||nx>N-1||ny<0||ny>N-1||map[nx][ny]==2) {
					if(blue(i,horses,map,hmap)) break end;
				}
				else if(map[nx][ny]==1) {
					if(red(i,horses,map,hmap)) break end;
				}
				else if(map[nx][ny]==0) {
					if(white(i,horses,map,hmap)) break end;
				}
			}
		}
		System.out.println(turn>1000?-1:turn);
        br.close();
	}
	static boolean blue(int idx, int[][] horses, int[][] map, ArrayDeque<Integer>[][] hmap) {
		//이동방향을 바꾼다.
		horses[idx][2]=rd[horses[idx][2]];
		int nx=horses[idx][0]+dx[horses[idx][2]];
		int ny=horses[idx][1]+dy[horses[idx][2]];
		//파랑 혹은 범위를 벗어나면 정지
		if(nx<0||nx>N-1||ny<0||ny>N-1||map[nx][ny]==2) return false;
		//빨강 하양이면 이동
		else if(map[nx][ny]==1) {
			if(red(idx,horses,map,hmap)) return true;
		}
		else if(map[nx][ny]==0) {
			if(white(idx,horses,map,hmap)) return true;
		}
		return false;
	}
	static boolean red(int idx, int[][] horses, int[][] map, ArrayDeque<Integer>[][] hmap) {
		int x=horses[idx][0], y=horses[idx][1];
		int nx=horses[idx][0]+dx[horses[idx][2]];
		int ny=horses[idx][1]+dy[horses[idx][2]];
		ArrayDeque<Integer> temp=new ArrayDeque<>();
		while(hmap[x][y].peek()!=idx) {
			temp.offer(hmap[x][y].poll());
		}
		while(!hmap[x][y].isEmpty()) {
			hmap[nx][ny].offer(hmap[x][y].pollLast());
			int cur=hmap[nx][ny].peekLast();
			horses[cur][0]=nx;
			horses[cur][1]=ny;			
		}
		if(hmap[nx][ny].size()>=4) return true;
		while(!temp.isEmpty()) {
			hmap[x][y].offer(temp.poll());
		}
		return false;
	}
	static boolean white(int idx, int[][] horses, int[][] map, ArrayDeque<Integer>[][] hmap) {
		int x=horses[idx][0], y=horses[idx][1];
		int nx=horses[idx][0]+dx[horses[idx][2]];
		int ny=horses[idx][1]+dy[horses[idx][2]];
		ArrayDeque<Integer> temp=new ArrayDeque<>();
		while(hmap[x][y].peek()!=idx) {
			temp.offer(hmap[x][y].poll());
		}
		while(!hmap[x][y].isEmpty()) {
			hmap[nx][ny].offer(hmap[x][y].poll());
			int cur=hmap[nx][ny].peekLast();
			horses[cur][0]=nx;
			horses[cur][1]=ny;	
		}
		if(hmap[nx][ny].size()>=4) return true;
		while(!temp.isEmpty()) {
			hmap[x][y].offer(temp.poll());
		}
		return false;
	}
}
