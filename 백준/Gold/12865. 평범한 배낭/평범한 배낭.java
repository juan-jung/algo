

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int[] weights=new int[N];
		int[] values=new int[N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			weights[i]=Integer.parseInt(st.nextToken());
			values[i]=Integer.parseInt(st.nextToken());
		}
		int[][] D=new int[N+1][K+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				if(weights[i-1] > j) {
					D[i][j]=D[i-1][j];
				}
				else {
					D[i][j]=Math.max(D[i-1][j], values[i-1]+D[i-1][j-weights[i-1]]);
				}
			}
		}
		System.out.println(D[N][K]);
	}
}
