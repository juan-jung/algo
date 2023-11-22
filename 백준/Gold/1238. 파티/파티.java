import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); //학생(마을) 수 (1<=N<=1,000)
		int M = Integer.parseInt(st.nextToken()); //도로 수(단방향) (1<=M<=10,000)
		int X = Integer.parseInt(st.nextToken())-1; //파티 장소 X
		List<int[]>[] g = new ArrayList[N];
		for(int i=0;i<N;i++) g[i] = new ArrayList<int[]>();
		int[] answer = new int[N]; //왕복시간 저장
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int T = Integer.parseInt(st.nextToken());
			g[start].add(new int[] {end, T});
		}
		
		//1. 학생 별 X까지의 소요 시간
		for(int i=0;i<N;i++) {
			int[] dist = new int[N];
			for(int j=0;j<N;j++) dist[j] = Integer.MAX_VALUE;
			dist[i] = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
			pq.offer(new int[] {i,0});
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				for(int[] target : g[cur[0]]) {
					if(dist[target[0]] > cur[1] + target[1]) {
						dist[target[0]] = cur[1] + target[1];
						pq.offer(new int[] {target[0], cur[1] + target[1]});
					}
				}
			}
			answer[i] += dist[X];
		}
		
		//2. X부터 모든 마을까지의 소요시간
		int[] dist = new int[N];
		for(int j=0;j<N;j++) dist[j] = Integer.MAX_VALUE;
		dist[X] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {X,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			for(int[] target : g[cur[0]]) {
				if(dist[target[0]] > cur[1] + target[1]) {
					dist[target[0]] = cur[1] + target[1];
					pq.offer(new int[] {target[0], cur[1] + target[1]});
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			answer[i] += dist[i];
			max = Math.max(max, answer[i]);
		}
		System.out.println(max);
	}
}
