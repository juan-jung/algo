

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] dp = new int[100001];
		for(int i=0;i<100001;i++) dp[i] = INF;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {N,0});
		dp[N] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(cur[0]+1<=100000 && cur[1]+1<dp[cur[0]+1]) {
				pq.offer(new int[] {cur[0]+1, cur[1]+1});
				dp[cur[0]+1] = cur[1]+1;
			}
			if(cur[0]-1>=0 && cur[1]+1<dp[cur[0]-1]) {
				pq.offer(new int[] {cur[0]-1, cur[1]+1});
				dp[cur[0]-1] = cur[1]+1;
			}
			if(cur[0]*2<=100000 && cur[1]<dp[cur[0]*2]) {
				pq.offer(new int[] {cur[0]*2, cur[1]});
				dp[cur[0]*2] = cur[1];
			}
		}
		System.out.println(dp[K]);
		sc.close();
	}
}
