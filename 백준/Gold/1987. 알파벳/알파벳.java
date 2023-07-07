

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int R,C;
	static boolean[][] visited;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int[][] arr;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		visited = new boolean[R][C];
		for(int i=0;i<R;i++) {
			String s = br.readLine();
			for(int j=0;j<C;j++) arr[i][j]=(int)s.charAt(j)-'A';
		}
		//for(int a[]:arr) System.out.println(Arrays.toString(a));
		
		max = Integer.MIN_VALUE;
		visited[0][0] =true;
		dfs(0,0,1,0|1<<arr[0][0]);
		System.out.println(max);
	}
	static void dfs(int x,int y, int dist,int flagAlpha) {
		
		max = Math.max(max, dist);
		for(int d=0;d<4;d++) {
			int nx=x+dx[d];
			int ny=y+dy[d];
	
			if(nx<0||nx>R-1 ||ny<0||ny>C-1 ||visited[nx][ny]||((flagAlpha&1<<arr[nx][ny])!=0)) continue;
			
			
			visited[nx][ny] =true;
			dfs(nx,ny,dist+1,flagAlpha|1<<arr[nx][ny]);
			visited[nx][ny] =false;
		}
	}
}
/*
2 4
CAAB
ADCB
*/
