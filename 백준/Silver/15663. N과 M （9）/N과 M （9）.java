import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		Map<String, Boolean> map = new HashMap<>();
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		boolean[] v = new boolean[N];
		int[] selected = new int[M];
		
		perm(0,arr,v,selected,M,N,map);
		br.close();
	}
	static void perm(int cnt, int[] arr, boolean[] v, int[] selected, int M, int N, Map<String, Boolean> map) {
		if(cnt == M) {
			String one = "";
			for(int i=0;i<M;i++) one+=selected[i]+" ";
			if(map.getOrDefault(one, true)) {
				System.out.println(one);
				map.put(one, false);
			}
			return;
		}
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			selected[cnt] = arr[i];
			perm(cnt+1,arr,v,selected,M,N, map);
			v[i] = false;
		}
	}
}
