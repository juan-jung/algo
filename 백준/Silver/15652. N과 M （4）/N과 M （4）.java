import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int[] selected;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		selected = new int[M];
		sb = new StringBuilder();
		comb(0,1);
		System.out.println(sb);
		sc.close();
	}
	static void comb(int cnt, int start) {
		if(cnt == M) {
			for(int a : selected) {
				sb.append(a+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=start;i<=N;i++) {
			selected[cnt] = i;
			comb(cnt+1,i);
		}
	}
}
