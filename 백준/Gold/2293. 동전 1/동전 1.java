import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dp = new int[k+1];
		dp[0] = 1;
		for(int i=0;i<n;i++) {
			int value = Integer.parseInt(br.readLine());
			for(int j=value;j<k+1;j++) {
				dp[j] += dp[j-value];
			}
		}
		
		System.out.println(dp[k]);
		br.close();
	}
}
