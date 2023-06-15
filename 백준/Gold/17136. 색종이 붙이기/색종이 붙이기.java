

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 색종이 종류 5가지 1x1 2x2 3x3 4x4 5x5 -> 각 다섯장씩 보유
	 
	 종이(10*10) 각칸에 0또는 1
	 종이의 1을 가지고 있는 색종이로 덮는다. 
	 - 색종이끼리 겹쳐선 안된다
	 - 색종이가 종이의 경계를 넘으면 안된다
	 - 0이 적힌 칸을 덮으면 안된다.
	 
	 출력: 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수 
	 / 불가능한 경우 -1출력
	 */
	static int min;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int[][] paper=new int[10][10];
		for(int i=0;i<10;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<10;j++) {
				paper[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//for(int a[]:paper) System.out.println(Arrays.toString(a));
		int[] remain= {5,5,5,5,5};
		min=Integer.MAX_VALUE;
		cover(paper, new boolean[10][10],0,0,remain,0);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
	static void cover(int[][] paper, boolean[][] v,int x, int y, int[] remain,int used) {
		if(used >= min) return;
		
		//System.out.println(x+" "+y);
		//System.out.println(Arrays.toString(remain));
		//for(boolean[] b:v) System.out.println(Arrays.toString(b));
		//System.out.println();
		
		if(x==9 && y==10) {
			//System.out.println("도착!!!!!");
			boolean flag=true;
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(paper[i][j]==1) {
						if(!v[i][j]) {
							flag=false;
							break;
						}
					}
				}
				if(!flag) break;
			}
			if(flag) min=Math.min(min, used);
			return;
		}
		//xy가 범위를 넘겻을때  paper[x][y]==0일 때 넘김
		if(y==10) {
			x++;
			y=0;
		}
		if(paper[x][y]==0 || v[x][y]) cover(paper,v,x,y+1,remain,used);
		else {
			for(int n=0;n<=4;n++) { //5 4 3 2 1
				if(remain[n]==0) continue;
				boolean[][] temp=new boolean[10][10];
				boolean flag=true;
				end: for(int i=x;i<=x+n;i++) {
					for(int j=y;j<=y+n;j++) {
						//0이 있거나 범위를 벗어나면 안됨.
						if(i>9 || j>9|| paper[i][j]==0 || v[i][j]) {
							flag=false;
							break end;
						}
						temp[i][j]=true;
						v[i][j]=true;
					}
				}
				if(flag) {
					remain[n]--;
					cover(paper, v, x, y+1, remain, used+1);
					remain[n]++;
				}
				boolean flag2=true;
				for(int i=x;i<=x+n;i++) {
					for(int j=y;j<=y+n;j++) {
						if(i>9 || j>9 || !temp[i][j]) {
							flag2=false;
							break;
						}
						if(temp[i][j]) v[i][j]=false;
					}
					if(!flag2) break;
				}
				if(!flag) break;
			}
		}
	}
}
