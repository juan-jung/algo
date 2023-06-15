

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		char[][] maze=new char[N][M];
		ArrayDeque<int[]> q=new ArrayDeque<>();
		boolean[][][] v=new boolean[N][M][1<<7];
		for(int i=0;i<N;i++) {
			String s=br.readLine();
			for(int j=0;j<M;j++) {
				maze[i][j]=s.charAt(j);
				if(maze[i][j]=='0') {
					q.offer(new int[] {i,j,0,0}); //x,y,거리,가지고있는열쇠
					v[i][j][0]=true;
				}
			}
		}
		int[] dx= {-1,1,0,0};
		int[] dy= {0,0,-1,1};
		int min=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			//System.out.println("x: "+cur[0]+" y: "+cur[1]+" dist: "+cur[2]+" keys:"+cur[3]);
			if(cur[2] > min) {
				continue;
			}
			if(maze[cur[0]][cur[1]]=='1') {
				min=Math.min(min, cur[2]);
				continue;
			}
			for(int d=0;d<4;d++) {
				int nx=cur[0]+dx[d];
				int ny=cur[1]+dy[d];
				
				if(nx<0||nx>N-1||ny<0||ny>M-1 ||maze[nx][ny]=='#' || v[nx][ny][cur[3]]) continue;
				//빈칸 , 열쇠, 문 , 출구 // a97 A65   65-97=-32
				//문
				if(maze[nx][ny]>='A' && maze[nx][ny]<='F') {
					if((cur[3]&1<<(maze[nx][ny]-'A'+1))!=0) {
						v[nx][ny][cur[3]] =true;
						q.offer(new int[] {nx,ny,cur[2]+1,cur[3]});
					}
				}
				//열쇠 abcdef 
				else if(maze[nx][ny]>='a' && maze[nx][ny]<='f') {
					//이미 있을 때
					if((cur[3]&1<<(maze[nx][ny]-'a'+1))!=0) {
						v[nx][ny][cur[3]] =true;
						q.offer(new int[] {nx,ny,cur[2]+1,cur[3]});
					}
					//없었을때
					else {
						v[nx][ny][cur[3]|1<<(maze[nx][ny]-'a'+1)]=true;
						q.offer(new int[] {nx,ny,cur[2]+1,cur[3]|1<<(maze[nx][ny]-'a'+1)});
					}
				}
				else {
					v[nx][ny][cur[3]] =true;
					q.offer(new int[] {nx,ny,cur[2]+1,cur[3]});
				}
			}
		}
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
}
