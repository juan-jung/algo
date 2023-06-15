

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int[][] map=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		boolean[][]v=new boolean[N][M];
		int color_idx=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(v[i][j] || map[i][j]==0) continue;
				color(i,j,color_idx,v,map);
				color_idx++;
			}
		}
		ArrayDeque<int[]> q=new ArrayDeque<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0) {
					q.offer(new int[] {i,j,map[i][j],0,0}); //x,y, start, dist, direct
					q.offer(new int[] {i,j,map[i][j],0,1}); //x,y, start, dist, direct
					q.offer(new int[] {i,j,map[i][j],0,2}); //x,y, start, dist, direct
					q.offer(new int[] {i,j,map[i][j],0,3}); //x,y, start, dist, direct
				}
			}
		}
		int[][] g=new int[color_idx][color_idx];
		int[] min_Edge=new int[color_idx];
		for(int i=0;i<color_idx;i++) {
			for(int j=0;j<color_idx;j++) g[i][j]=Integer.MAX_VALUE;
			min_Edge[i]=Integer.MAX_VALUE;
		}
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			int x=cur[0],y=cur[1],start=cur[2],dist=cur[3],direct=cur[4];
			int nx=x+dx[direct];
			int ny=y+dy[direct];
			if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
			if(map[nx][ny]==start) continue;
			if(map[nx][ny]!=0 && map[nx][ny]!=start) {
				if(dist >= 2) {
					//그래프 갱신
					g[start][map[nx][ny]] = Math.min(g[start][map[nx][ny]], dist);
				}
				continue;
			}
			q.offer(new int[] {nx,ny,start,dist+1,direct});
		}
		//for(int a[]:map) System.out.println(Arrays.toString(a));
		//for(int a[]:g) System.out.println(Arrays.toString(a));
		boolean[] visited=new boolean[color_idx];
		int result=0,cnt=0;
		min_Edge[0]=0;
		PriorityQueue<int[]> pq=new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {1,0});
		while(!pq.isEmpty()) {
			int[] cur=pq.poll();
			int minVertex=cur[0];
			int min=cur[1];
			if(visited[minVertex]) continue;

			visited[minVertex]=true;
			result+=min;
			if(cnt++==color_idx-2) break;
			for(int j=1;j<color_idx;j++) {
				if(!visited[j]&&g[minVertex][j]!=Integer.MAX_VALUE && min_Edge[j]>g[minVertex][j]) {
					min_Edge[j]=g[minVertex][j];
					pq.offer(new int[] {j,g[minVertex][j]});
				}
			}
		}
		System.out.println(cnt==color_idx-1?result:-1);
	}
	
	static void color(int x, int y, int color_idx, boolean[][] v, int[][] map) {
		if(x<0||x>N-1||y<0||y>M-1||map[x][y]==0||v[x][y]) return;
		v[x][y]=true;
		map[x][y]=color_idx;
		for(int d=0;d<4;d++) {
			color(x+dx[d],y+dy[d],color_idx,v,map);
		}
	}
}
