import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[3][N];
		dp[0][0] = 1;
		dp[1][0] = 1;
		dp[2][0] = 1;
		for(int i=1;i<N;i++) {
			dp[0][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1])%9901;
			dp[1][i] = (dp[0][i-1] + dp[2][i-1])%9901;
			dp[2][i] = (dp[0][i-1] + dp[1][i-1])%9901;
		}
		System.out.println((dp[0][N-1] + dp[1][N-1] + dp[2][N-1])%9901);
	}
}
