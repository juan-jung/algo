import java.io.*;
import java.util.*;

public class Main {
	static int N,M,D,max;
	static int[][] map;
	static int[] archer = new int[3];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		comb(0,0);
		System.out.println(max);
	}
	static void comb(int cnt, int start) {
		if(cnt==3) {
			int copy[][] = new int[N][M];
			for(int i=0;i<N;i++) copy[i] = Arrays.copyOf(map[i], M); 
			
			int kill =0;
			while(true) {
				boolean flag = true;
				//1. 궁수가 공격, 몇 마리 죽였는지 체크
				int[][] attack = {{-1,-1},{-1,-1},{-1,-1}};
				for(int i=0;i<3;i++) {
					int min = Integer.MAX_VALUE;
					for(int k=0;k<M;k++) {
						for(int j=0;j<N;j++) {
							if(copy[j][k]==1) {
								int dist =Math.abs(N-j)+Math.abs(archer[i]-k); 
								if(dist<=D&&dist<min){
									min = dist;
									attack[i][0]=j;
									attack[i][1]=k;
								}								
							}
						}
					}
				}
				for(int i=0;i<3;i++) {
					if(attack[i][0]==-1)continue;
					if(copy[attack[i][0]][attack[i][1]]==1) {
						copy[attack[i][0]][attack[i][1]]=0;
						kill++;
					}
				}
				//2. 남은 적 확인 
				for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) if(copy[i][j]==1) flag=false;
				}
				//2-1. 없다면 종료
				if(flag) break;
				//2-2. 있다면 한칸 씩 아래로 이동한 후 다시 궁수 사격
				for(int i=N-1;i>=0;i--) {
					for(int j=M-1;j>=0;j--) {
						if(copy[i][j]==1) {
							int ni=i+1;
							if(ni==N) {
								copy[i][j]=0;
								continue;
							}
							copy[i][j]=0;
							copy[ni][j]=1;
						}
					}
				}	
			}
			max = Math.max(max, kill);
			return;
		}
		for(int i=start;i<M;i++) {
			archer[cnt]=i;
			comb(cnt+1,i+1);
		}
	}
}
