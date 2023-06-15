
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
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		char[][] map=new char[N][M];
		for(int i=0;i<N;i++) map[i]=br.readLine().toCharArray();
		//for(char[] ca:map) System.out.println(Arrays.toString(ca));
		boolean[][][] v=new boolean[2][N][M];
		ArrayDeque<int[]> q=new ArrayDeque<>();
		v[0][0][0]=true;
		q.offer(new int[] {0,0,1,0});//x,y,이동칸,벽여부
		int[] dx= {-1,1,0,0};
		int[] dy= {0,0,-1,1};
		int min=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			int x=cur[0],y=cur[1],cnt=cur[2],wall=cur[3];
			if(cnt>min) continue;
			if(x==N-1&&y==M-1) {
				min=Math.min(min, cnt);
				continue;
			}
			for(int d=0;d<4;d++) {
				int nx=x+dx[d];
				int ny=y+dy[d];
				if(nx<0||nx>N-1||ny<0||ny>M-1||v[0][nx][ny]) continue;
				if(map[nx][ny]=='1') {
					if(wall==1||v[1][nx][ny]) continue;
					v[1][nx][ny]=true;
					q.offer(new int[] {nx,ny,cnt+1,1});
				}else {
					if(wall==0) {
						v[0][nx][ny]=true;
						q.offer(new int[] {nx,ny,cnt+1,0});
					}
					else {
						if(v[1][nx][ny])continue;
						v[1][nx][ny]=true;
						q.offer(new int[] {nx,ny,cnt+1,1});
					}
				}
			}				
		}
		System.out.println(min==Integer.MAX_VALUE?-1:min);
		br.close();
	}
}
