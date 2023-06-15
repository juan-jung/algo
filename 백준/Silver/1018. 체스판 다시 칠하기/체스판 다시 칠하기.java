import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		char[][] board = new char[N][M];
		for(int i=0;i<N;i++) {
			String row = br.readLine();
			for(int j=0;j<M;j++) {
				board[i][j] = row.charAt(j);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=0;i<=N-8;i++) {
			for(int j=0;j<=M-8;j++) {
//				System.out.println(i+" "+j);
				int origin_cnt =0;
				int reverse_cnt =0;
				for(int x=i;x<i+8;x++) {
					for(int y=j;y<j+8;y++) {

						if((x+y)%2==0) {
							if(board[i][j] != board[x][y]) origin_cnt++;
							else reverse_cnt++;
						}
						else {
							if(board[i][j] == board[x][y]) origin_cnt++;
							else reverse_cnt++;
						}
					}
				}
//				System.out.println("origin: "+origin_cnt+", reverse: "+reverse_cnt);
				int cnt = Math.min(origin_cnt, reverse_cnt);
				min = Math.min(min, cnt);
			}
		}
		System.out.println(min);
	}
}
