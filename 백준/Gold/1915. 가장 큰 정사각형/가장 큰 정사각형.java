import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			String line = br.readLine();
			for(int j=1;j<=M;j++) map[i][j] = line.charAt(j-1) == '1' ? 1 : 0;
		}
		
		int ans = 0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(map[i][j]==1) {
					map[i][j] = min(map[i-1][j],map[i][j-1],map[i-1][j-1]) + 1;
					ans = Math.max(ans, map[i][j]);					
				}
			}
		}
		System.out.println((int)Math.pow(ans, 2));
		br.close();
	}
	
	static int min(int a, int b, int c) {
		int min = a > b ? b : a;
		return min > c ? c : min;
	}
}