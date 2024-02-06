import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] line = new int[n][2];
		StringTokenizer st = null;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			line[i][0] = A;
			line[i][1] = B;
		}
		
		Arrays.sort(line, (o1,o2)->Integer.compare(o1[0], o2[0]));
		
		int max = 1;
		int[] dp = new int[n];
		for(int i=0;i<n;i++) {
			dp[i] = 1;
			for(int j=0;j<i;j++) {
				if(line[i][1] > line[j][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(n-max);
		br.close();
	}
}
