

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		char[][] map=new char[N][N];
		int[][] b=new int[3][2];
		int[][] e=new int[3][2];
		int bIdx=0;
		int eIdx=0;
		for(int i=0;i<N;i++) {
			String s=br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='B') {
					b[bIdx][0] = i;
					b[bIdx++][1] = j;
				}
				if(map[i][j]=='E') {
					e[eIdx][0]=i;
					e[eIdx++][1]=j;
				}
			}
		}
		
		int x=b[1][0];
		int y=b[1][1];
		int colrow =0; //0이면 행(ㅡ), 1이면 열(l)
		if(b[0][1] == b[1][1]) {
			colrow = 1;
		}
		
		int endX=e[1][0];
		int endY=e[1][1];
		int endColrow=0;
		if(e[0][1] == e[1][1]) {
			endColrow = 1;
		}
		
		boolean[][][] visited = new boolean[2][N][N]; //중심점 : 행0 열1 
		ArrayDeque<int[]> q=new ArrayDeque<>();
		q.offer(new int[] {x,y,colrow,0});
		visited[colrow][x][y]=true;
		int min = Integer.MAX_VALUE;
		int dx[]= {-1,1,0,0,-1,-1,1,1};//상하좌우 
		int dy[]= {0,0,-1,1,-1,1,-1,1};
		
		//중심점을 기준으로 행열로 구분
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			//System.out.println("x: "+cur[0]+", y: "+cur[1]+" ,colrow: "+cur[2]+" ,cnt: "+cur[3]);
			if(cur[0]==endX && cur[1]==endY && cur[2]==endColrow) {
				min = cur[3];
				break;
			}
			
			
			if(cur[2]==0) {
				//상 -> 좌상 상 우상
				if(cur[0]-1>=0 && map[cur[0]-1][cur[1]-1]!='1' && map[cur[0]-1][cur[1]]!='1' && map[cur[0]-1][cur[1]+1]!='1' && !visited[cur[2]][cur[0]-1][cur[1]]) {
					q.offer(new int[] {cur[0]-1,cur[1],cur[2],cur[3]+1});
					visited[cur[2]][cur[0]-1][cur[1]]=true;
				}
				//하 -> 좌하 하 우하
				if(cur[0]+1<=N-1 && map[cur[0]+1][cur[1]-1]!='1' && map[cur[0]+1][cur[1]]!='1' && map[cur[0]+1][cur[1]+1]!='1' && !visited[cur[2]][cur[0]+1][cur[1]]) {
					q.offer(new int[] {cur[0]+1,cur[1],cur[2],cur[3]+1});
					visited[cur[2]][cur[0]+1][cur[1]]=true;
				}
				//좌 -> 좌2칸
				if(cur[1]-2 >= 0 && !visited[cur[2]][cur[0]][cur[1]-1] &&  map[cur[0]][cur[1]-2]!='1') {
					q.offer(new int[] {cur[0],cur[1]-1,cur[2],cur[3]+1});
					visited[cur[2]][cur[0]][cur[1]-1]=true;
				}
				//우 -> 우2칸
				if(cur[1]+2 <= N-1 && !visited[cur[2]][cur[0]][cur[1]+1] &&  map[cur[0]][cur[1]+2]!='1') {
					q.offer(new int[] {cur[0],cur[1]+1,cur[2],cur[3]+1});
					visited[cur[2]][cur[0]][cur[1]+1]=true;
				}
			}
			
			if(cur[2]==1) {
				//좌
				if(cur[1]-1>=0 && map[cur[0]-1][cur[1]-1]!='1' && map[cur[0]][cur[1]-1]!='1' && map[cur[0]+1][cur[1]-1]!='1' && !visited[cur[2]][cur[0]][cur[1]-1]) {
					q.offer(new int[] {cur[0],cur[1]-1,cur[2],cur[3]+1});
					visited[cur[2]][cur[0]][cur[1]-1]=true;
				}
				//우
				if(cur[1]+1<=N-1 && map[cur[0]-1][cur[1]+1]!='1' && map[cur[0]][cur[1]+1]!='1' && map[cur[0]+1][cur[1]+1]!='1' && !visited[cur[2]][cur[0]][cur[1]+1]) {
					q.offer(new int[] {cur[0],cur[1]+1,cur[2],cur[3]+1});
					visited[cur[2]][cur[0]][cur[1]+1]=true;
				}
				//상
				if(cur[0]-2 >= 0 && !visited[cur[2]][cur[0]-1][cur[1]] &&  map[cur[0]-2][cur[1]]!='1') {
					q.offer(new int[] {cur[0]-1,cur[1],cur[2],cur[3]+1});
					visited[cur[2]][cur[0]-1][cur[1]]=true;
				}
				//하
				if(cur[0]+2 <= N-1 && !visited[cur[2]][cur[0]+1][cur[1]] &&  map[cur[0]+2][cur[1]]!='1') {
					q.offer(new int[] {cur[0]+1,cur[1],cur[2],cur[3]+1});
					visited[cur[2]][cur[0]+1][cur[1]]=true;
				}
			}
			
			//회전
			int op = 0;
			if(cur[2]==0) op=1;
			if(!visited[op][cur[0]][cur[1]]) {
				boolean flag = true;
				for(int d=0;d<8;d++) {
					int nx=cur[0]+dx[d];
					int ny=cur[1]+dy[d];
					if(nx<0||nx>N-1||ny<0||ny>N-1||map[nx][ny]=='1') {
						flag=false;
						break;
					}
				}
				if(flag) {
					q.offer(new int[] {cur[0],cur[1],op,cur[3]+1});
					visited[op][cur[0]][cur[1]] =true;
				}
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
		br.close();
	}
}
