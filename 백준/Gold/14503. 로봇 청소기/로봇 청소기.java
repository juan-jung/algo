

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		//int[] cleaner=new int[4]; //r,c,d(0123 상우하좌),청소한칸
		st=new StringTokenizer(br.readLine()," ");
		int x=Integer.parseInt(st.nextToken());
		int y=Integer.parseInt(st.nextToken());
		int direct=Integer.parseInt(st.nextToken());
		int cleaned=0;
		//cleaner[0]=Integer.parseInt(st.nextToken());
		//cleaner[1]=Integer.parseInt(st.nextToken());
		//cleaner[2]=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		//for(int a[]:map) System.out.println(Arrays.toString(a));
		
		//현재칸 확인 후 청소
		int[] dx= {-1,0,1,0};
		int[] dy= {0,1,0,-1};
		while(true) {
			if(map[x][y]==0) {
				map[x][y]=2;
				cleaned++;
			}
			boolean flag=true; //상우하좌 청소되지않은 빈칸이 있을 경우 false , 없을 경우 true;
			for(int d=0;d<4;d++) {
				int nx=x+dx[d];
				int ny=y+dy[d];
				if(nx<0||nx>N-1||ny<0||ny>M-1||map[nx][ny]==1||map[nx][ny]==2) continue;
				flag=false;
			}
			//청소되지 않은 빈칸이 없는 경우, 방향 유지한채로 한칸 후진, 후진못하면 작동멈춤
			if(flag) {
				if(direct==0) x+=1;
				else if(direct==1) y-=1;
				else if(direct==2) x-=1;
				else if(direct==3) y+=1;
				if(map[x][y]==1) break;
				continue;
			}
			//청소되지 않은 빈칸이 있는 경우, 반시계방향90도회전, 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진.
			else { //상우하좌
				if(direct==0) direct=3;
				else if(direct==1) direct=0;
				else if(direct==2) direct=1;
				else if(direct==3) direct=2;
				if(map[x+dx[direct]][y+dy[direct]]==0) {
					x+=dx[direct];
					y+=dy[direct];
				}
			}
		}
		System.out.println(cleaned);
	}
}
