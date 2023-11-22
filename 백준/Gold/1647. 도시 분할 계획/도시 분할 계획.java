import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<int[]>[] g = new ArrayList[N];
		int[] dist = new int[N];
		for(int i=0;i<N;i++) {
			g[i] = new ArrayList<int[]>();
			dist[i] = Integer.MAX_VALUE;
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int price = Integer.parseInt(st.nextToken());
			g[start].add(new int[] {end, price});
			g[end].add(new int[] {start, price});
		}
		
		boolean[] v = new boolean[N];
		dist[0] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {0,0});
		int cnt = 0;
		int result = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(v[cur[0]]) continue;
			v[cur[0]] = true;
			result += cur[1];
			if(N-1 == cnt++) break;
			
			for(int[] target : g[cur[0]]) {
				if(v[target[0]]) continue;
				if(dist[target[0]] > target[1]) {
					dist[target[0]] = target[1];
					pq.offer(new int[] {target[0], target[1]});
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) max = Math.max(max, dist[i]);
		System.out.println(result-max);
	}
}
