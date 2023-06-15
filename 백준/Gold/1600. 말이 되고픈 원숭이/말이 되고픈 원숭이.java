

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int k=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int w=Integer.parseInt(st.nextToken());
		int h=Integer.parseInt(st.nextToken());
		int[][] map=new int[h][w];
		for(int i=0;i<h;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<w;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		//for(int a[]:map)System.out.println(Arrays.toString(a));
		ArrayDeque<int[]> q=new ArrayDeque<>();
		boolean[][][] v=new boolean[h][w][k+1];
		for(int i=0;i<k;i++) v[0][0][i]=true;
		q.offer(new int[] {0,0,0,0});//x,y,이동횟수,limit
		int[] dx= {-1,1,0,0,-2,-2,2,2,1,-1,1,-1}; 
		int[] dy= {0,0,-1,1,-1,1,-1,1,-2,-2,2,2};
		int min=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			int x=cur[0];
			int y=cur[1];
			int cnt=cur[2];
			int limit=cur[3];
			if(cnt > min) continue;
			if(x==h-1&&y==w-1) {
				min=Math.min(min, cnt);
				continue;
			}
			for(int d=0;d<12;d++) {
				int nx=x+dx[d];
				int ny=y+dy[d];
				if(nx<0||nx>h-1||ny<0||ny>w-1||map[nx][ny]==1) continue;
				if(d<4&&!v[nx][ny][limit]) {				
					v[nx][ny][limit]=true;
					if(nx==h-1&&ny==w-1) v[nx][ny][limit]=false;
					q.offer(new int[] {nx,ny,cnt+1,limit});
				}
				else if(d>3&&limit<k&&!v[nx][ny][limit+1]){
					v[nx][ny][limit+1]=true;
					if(nx==h-1&&ny==w-1) v[nx][ny][limit+1]=false;
					q.offer(new int[] {nx,ny,cnt+1,limit+1});
				}
			}
		}
		System.out.println(min==Integer.MAX_VALUE?-1:min);
		
		
		
		br.close();
		
	}
}
