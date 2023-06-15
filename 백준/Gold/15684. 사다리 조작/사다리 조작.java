
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M,H,min;
	static int[] dx= {0,0};
	static int[] dy= {-1,1};
	//2 0 3
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken()); //세로선 개수
		M=Integer.parseInt(st.nextToken()); // 가로선 개수
		H=Integer.parseInt(st.nextToken());//가로선을 놓을 수 있는 위치
		                                        // 0  1   2  3  4  5
		boolean[][] ladder=new boolean[H][N-1]; //   0  1  2  3  4
		for(int i=0;i<M;i++) {                    
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken())-1; //가로위치
			int b=Integer.parseInt(st.nextToken())-1;  
 			ladder[a][b]=true;
		}

		//for(boolean[] a:ladder) System.out.println(Arrays.toString(a));
		//System.out.println();
		boolean[][] extended_ladder=new boolean[H][N+N-1];//짝수는 사다리 02468 홀수는연결구간 0123 -> 1357 
		for(int i=0;i<N+N-1;i++) {
			for(int j=0;j<H;j++) {
				if(i%2==0) {
					extended_ladder[j][i]=true;
				}
			}
		}
		for(int i=0;i<H;i++) {
			for(int j=0;j<N-1;j++) {
				extended_ladder[i][j*2+1]=ladder[i][j];
			}
		}
		
		
		boolean flag=true;
		for(int i=0;i<(N+N-1);i++) {
			if(i%2==1) continue;
			int col=i;
			for(int j=0;j<H;j++) {
				if(col-1>=0 && extended_ladder[j][col-1]) col-=2;
				else if(col+1<(N+N-1)&& extended_ladder[j][col+1]) col+=2;
			}
			if(col!=i) {
				flag=false;
				break;
			}
		}
		if(flag) System.out.println(0);
		else {
			int max_select=H*(N-1)-M;
			min=Integer.MAX_VALUE;
			for(int i=1;i<4;i++) {
				if(min!=Integer.MAX_VALUE) break;
				comb(0,0,i,max_select+M,ladder);
			}
			System.out.println(min>3||min==Integer.MAX_VALUE? -1:min);
		}		
		br.close();
	}
	static void comb(int cnt, int start, int R, int max_select,boolean[][] ladder) {
		if(min!=Integer.MAX_VALUE) return;
		if(cnt==R) {
			boolean[][] extended_ladder=new boolean[H][N+N-1];//짝수는 사다리 02468 홀수는연결구간 0123 -> 1357 
			for(int i=0;i<N+N-1;i++) {
				for(int j=0;j<H;j++) {
					if(i%2==0) {
						extended_ladder[j][i]=true;
					}
				}
			}
			for(int i=0;i<H;i++) {
				for(int j=0;j<N-1;j++) {
					extended_ladder[i][j*2+1]=ladder[i][j];
				}
			}
			//for(boolean[] a:ladder) System.out.println(Arrays.toString(a));
			//System.out.println();
			//자기 자신에게 가는지 확인
			boolean flag=true;
			for(int i=0;i<(N+N-1);i++) {
				if(i%2==1) continue;
				int col=i;
				for(int j=0;j<H;j++) {
					if(col-1>=0 && extended_ladder[j][col-1]) col-=2;
					else if(col+1<(N+N-1)&& extended_ladder[j][col+1]) col+=2;
				}
				if(col!=i) {
					flag=false;
					break;
				}
			}
			if(flag) {
				//최소값 갱신
				min=Math.min(min, R);
			}
			return;
		}
		end:for(int i=start;i<max_select;i++) {
			int x=i/(N-1);
			int y=i%(N-1);
			if(ladder[x][y]) continue;
			for(int d=0;d<2;d++) {
				int nx=x+dx[d];
				int ny=y+dy[d];
				if(ny<0||ny>(N-2)) continue;
				if(ladder[nx][ny]) continue end;
			}
			ladder[x][y]=true;
			comb(cnt+1,i+1,R,max_select,ladder);
			ladder[x][y]=false;
		}
	}
}
