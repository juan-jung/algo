

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		// 좌표 평면의 x축은 → 방향, y축은 ↓ 방향
		//속성: 시작 점(x,y), 시작 방향(0123 우상좌하), 세대
		//K(K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것
		int N=Integer.parseInt(br.readLine());
		int[][] curve=new int[N][4];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<4;j++) curve[i][j]=Integer.parseInt(st.nextToken());
		}
		int[][] map=new int[101][101];
		
		//시작방향 우
		//0세대 : 우
		//1세대 : 상
		//2세대 : 좌상
		//3세대 : 좌하좌상
		
		//시작 좌
		//0세대 : 좌
		//1세대 : 하
		//2세대 : 우하
		
		//시작 상
		//0세대 : 상
		//1세대 : 좌
		//2세대 : 하좌
		//3세대 : 하우하좌
		
		//시작 하
		//0세대 : 하
		//1세대 : 우
		//2세대 : 상우
		//3세대 : 상좌상우
		//4세대 : 상좌하좌상좌상우
		
		//결론
		//0세대 -> 시작방향
		//1세대 -> 우->상->좌->하->우
		//2세대이상 -> 0세대~n-2세대까지의 반대방향 + n-1세대방향
		int[] dx= {0,-1,0,1};//0123 우상좌하
		int[] dy= {1,0,-1,0};
		for(int i=0;i<N;i++) {
			ArrayList<Integer> al=new ArrayList<>(); //간방향
			ArrayDeque<Integer> q2=new ArrayDeque<>(); //갈방향
			//System.out.println((i+1)+"번째 시작");
			//시작 점(x,y), 시작 방향(0123 우상좌하), 세대
			int y=curve[i][0];
			int x=curve[i][1];
			int direct=curve[i][2];
			int g=curve[i][3];
			map[x][y]=1;
			//System.out.println(x+ " "+y);
			q2.offer(direct);
			for(int j=0;j<=g;j++) {
				if(j==1) {
					int cur_d=al.get(0);
					cur_d+=1;
					if(cur_d==4) cur_d=0;
					q2.offer(cur_d);
				}
				//0세대 
				//1세대 
				//2세대 1
				//3세대 2
				//4세대 4
				//5세대 8
				else if(j>1) {
					for(int k=0;k<Math.pow(2,j-2);k++) {
						int cur_d=al.get(k);
						if(cur_d==0) cur_d=2;
						else if(cur_d==1) cur_d=3;
						else if(cur_d==2) cur_d=0;
						else if(cur_d==3) cur_d=1;
						q2.offer(cur_d);
					}
					for(int k=(int) Math.pow(2,j-2);k<al.size();k++) q2.offer(al.get(k));
					
				}
				while(!q2.isEmpty()) {
					int cur_d=q2.poll();
					x+=dx[cur_d];
					y+=dy[cur_d];
					map[x][y]=1;
					//System.out.println(x+ " "+y);
					al.add(cur_d);
				}
			}
			//for(int a[]:map) System.out.println(Arrays.toString(a));
			//System.out.println(al);
		}
		int cnt=0;
		dx= new int[]{0,1,1};//우,하,우하
		dy= new int[]{1,0,1};
		for(int i=0;i<101;i++) {
			end:for(int j=0;j<101;j++) {
				if(map[i][j]==1) {
					int flag=0;
					for(int d=0;d<3;d++) {
						int ni=i+dx[d];
						int nj=j+dy[d];
						if(ni<0||ni>100||nj<0||nj>100||map[ni][nj]==0) continue end;
						flag++;
					}
					if(flag==3) cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
