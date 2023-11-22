import java.io.*;
import java.util.*;

public class Main {
	static int answer;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 지식 개수 (1<=N<=1000)
		int M = Integer.parseInt(st.nextToken()); // 문제 개수 (1<=M<=7)
		int[] d = new int[N+1]; //까먹게 되는 날짜
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=1;i<=N;i++) d[i]=Integer.parseInt(st.nextToken());
		List<Integer>[] a = new ArrayList[M]; //문제당 필요한 지식
		for(int i=0;i<M;i++) {
			a[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for(int j=0;j<k;j++) a[i].add(Integer.parseInt(st.nextToken()));
		}
		boolean[] v = new boolean[M];
		int[] selected = new int[M];
		answer = Integer.MAX_VALUE;
		perm(0,v,selected,M,d,N,a);
		System.out.println(answer);
		br.close();
	}
	
	static void perm(int cnt, boolean[] v, int[] selected, int M, int[] d, int N, List<Integer>[] a) {
		if(cnt == M) {
			int[] memo = new int[N+1];
			int count = 0;
			for(int i=0;i<M;i++) {
				for(int knowledge : a[selected[i]]) {
					if(memo[knowledge] > i) continue;
					count++;
					memo[knowledge] = i + d[knowledge];
				}
				
			}
			answer = Math.min(answer, count);
			return;
		}
		for(int i=0;i<M;i++) {
			if(v[i]) continue;
			v[i] = true;
			selected[cnt] = i;
			perm(cnt+1, v, selected, M, d, N, a);
			v[i] = false;
		}
	}
}
