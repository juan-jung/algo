

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx= {-1,0,0,1};
	static int[] dy= {0,-1,1,0};
	static int[] fish;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N]; 
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					fish=new int[] {i,j,2,0,0};//x,y,크기,잡은수,시간 
				}
			}
		}
		//System.out.println("초기 물고기 좌표/크기/잡은수/시간 "+Arrays.toString(fish));
		//for(int a[]:map) System.out.println(Arrays.toString(a));
		//System.out.println();
		
		boolean flag=true;
		while(flag) {
			ArrayList<int[]> al=new ArrayList<>();
			flag = bfs(fish,al);
			if(flag) {
				Collections.sort(al,(o1,o2)-> o1[0]==o2[0]?Integer.compare(o1[1],o2[1]):Integer.compare(o1[0], o2[0]));
				fish=al.get(0);
				
			}
			//System.out.println(al);
		}
		System.out.println(fish[4]);
	}
	static boolean bfs(int[] fish, ArrayList<int[]> al) {
		ArrayDeque<int[]> q=new ArrayDeque<>();
		boolean[][] v=new boolean[N][N];
		v[fish[0]][fish[1]]=true;
		map[fish[0]][fish[1]]=0;
		q.offer(fish); //x,y,크기,잡은수,시간(거리)
		int min=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			if(cur[4]>=min) break;
			for(int d=0;d<4;d++) {
				int nx=cur[0]+dx[d];
				int ny=cur[1]+dy[d];
				if(nx<0||nx>N-1||ny<0||ny>N-1||v[nx][ny]||map[nx][ny]>cur[2]) continue;
				if(map[nx][ny]==0||map[nx][ny]==cur[2]) {
					v[nx][ny]=true;
					q.offer(new int[] {nx,ny,cur[2],cur[3],cur[4]+1});
				}
				else {
					min=cur[4]+1;
					int size=cur[2];
					int caught=cur[3]+1;
					if(caught==cur[2]) {
						size++;
						caught=0;
					}
					al.add(new int[] {nx,ny,size,caught,min});
				}
			}
		}
		if(al.size()!=0) return true;
		return false;
	}
}
