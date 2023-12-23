import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<int[]>[] g = new ArrayList[V];
		int[] dist = new int[V];
		final int INF = Integer.MAX_VALUE;
		for(int i=0;i<V;i++) {
			g[i] = new ArrayList<int[]>();
			dist[i] = INF;
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken())-1;
			int n2 = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			g[n1].add(new int[] {n2,t});
			g[n2].add(new int[] {n1,t});
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		q.offer(new int[] {0,0});
		dist[0] = 0;
		int cnt=0,result=0;
		boolean[] v = new boolean[V];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(v[cur[0]]) continue;
			
			v[cur[0]] = true;
			result+=cur[1];
			
			if(cnt++ == V-1) break;
			
			for(int[] target : g[cur[0]]) {
				if(v[target[0]]||dist[target[0]] < target[1]) continue;
				dist[target[0]] = target[1];
				q.offer(new int[]{target[0], dist[target[0]]});
			}
		}
		System.out.println(result);

		br.close();
	}
}
