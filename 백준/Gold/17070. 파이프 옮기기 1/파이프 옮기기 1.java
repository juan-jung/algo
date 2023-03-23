

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N,cnt;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		int[][] home=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) home[i][j]=Integer.parseInt(st.nextToken());
		}
		
		//for(int a[]:home) System.out.println(Arrays.toString(a));
		//
		dfs(0,0,0,1,home);
		System.out.println(cnt);
	}
	static void dfs(int x1, int y1, int x2, int y2,int[][] home) {
		//실패조건
		if(x1<0||x1>N-1||y1<0||y1>N-1||x2<0||x2>N-1||y2<0||y2>N-1) {
			//System.out.println("실패");
			return;
			
		}
		//성공조건
		if((x1==N-1&&y1==N-1)||(x2==N-1&&y2==N-1)) {
			cnt++;
			return;
		}
		//가로 2가지
		if(x1==x2) {
			//System.out.println(1);
			if(y2+1<N&&home[x1][y2+1]!=1) {
				dfs(x1,y2,x1,y2+1,home);
				if(x1+1<N&&home[x1+1][y2]!=1&&home[x1+1][y2+1]!=1) dfs(x2,y2,x1+1,y2+1,home);
			}
		}
		//세로 2가지
		if(y1==y2) {
			//System.out.println(2);
			if(x2+1<N&&home[x2+1][y1]!=1) {
				dfs(x2,y2,x2+1,y2,home);
				if(y1+1<N&&home[x2][y2+1]!=1&&home[x2+1][y2+1]!=1) dfs(x2,y2,x2+1,y2+1,home);
			}
		}
		//대각선 3가지
		if(x1!=x2&&y1!=y2) {
			//System.out.println(3);
			if(y2+1<N&&home[x2][y2+1]!=1) dfs(x2,y2,x2,y2+1,home);
			if(x2+1<N&&home[x2+1][y2]!=1) dfs(x2,y2,x2+1,y2,home);
			if(x2+1<N&&y2+1<N&&home[x2+1][y2]!=1&&home[x2][y2+1]!=1&&home[x2+1][y2+1]!=1) dfs(x2,y2,x2+1,y2+1,home);
		}
	}
}
