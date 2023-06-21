
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine()); //색종이 수
		int[][] paper = new int[100][100];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j=x;j<x+10;j++) {
				for(int k=y;k<y+10;k++) paper[j][k]=1;
			}
		}
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		int cnt =0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(paper[i][j]==1) {
					for(int d=0;d<4;d++) {
						int nx = i+dx[d];
						int ny = j+dy[d];
						if(nx<0||nx>99||ny<0||ny>99) cnt++;
						else if(paper[nx][ny]==0) {
							cnt++;
						}
					}
				}
			}
		}
//		for(int a[]: paper) System.out.println(Arrays.toString(a));
		System.out.println(cnt);
		
	}
}
