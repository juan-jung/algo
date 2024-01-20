

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dp = new int[k+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		int[] coins = new int[n];
		for(int i=0;i<n;i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);
		for(int i=0;i<n;i++) {
			for(int j=coins[i];j<k+1;j++) {
				if(dp[j-coins[i]] == Integer.MAX_VALUE) continue;
				dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
			}
		}
		
		
		System.out.println(dp[k] != Integer.MAX_VALUE ? dp[k] : -1);
		br.close();
	}
}
