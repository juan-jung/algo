

import java.io.*;
import java.util.*;

public class Main {
	static int N,ans;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[] queens = new int[N];
		ans = 0;
		findNQueen(0,queens);
		System.out.println(ans);
		sc.close();
	}
	static void findNQueen(int cnt, int[] queens) {
		if(cnt == N) {
			ans++;
			return;
		}
		for(int i=0;i<N;i++) {
			queens[cnt] = i;
			if(isPossible(cnt,queens)) {
				findNQueen(cnt+1, queens);
			}
		}
	}
	
	static boolean isPossible(int cnt, int[] queens) {
		for(int i=0;i<cnt;i++) {
			if(queens[i] == queens[cnt] || cnt-i == Math.abs(queens[i]-queens[cnt])) return false;
		}
		return true;
	}
}
