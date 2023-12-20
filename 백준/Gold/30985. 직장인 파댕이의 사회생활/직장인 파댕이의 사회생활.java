

import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int room;
		long time;
		
		Node (int room, long time) {
			this.room = room;
			this.time = time;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<int[]>[] g = new ArrayList[N];
		long[][] time = new long[N][2]; //0:0toN, 1:Nto0
		long[] elevator = new long[N];
		
		for(int i=0;i<N;i++) {
			g[i] = new ArrayList<int[]>();
			time[i][0] = Long.MAX_VALUE;
			time[i][1] = Long.MAX_VALUE;
		}

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken())-1;
			int n2 = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			g[n1].add(new int[] {n2, t});
			g[n2].add(new int[] {n1, t});
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<N;i++) {
			int e = Integer.parseInt(st.nextToken());
			elevator[i] = e;
		}
		
		dik(0,0,g,time);
		dik(N-1,1,g,time);
		
		long min = Long.MAX_VALUE;
		for(int i=0;i<N;i++) {
			if(elevator[i] == -1 || time[i][0] == Long.MAX_VALUE || time[i][1] == Long.MAX_VALUE) continue;
			min = Math.min(min, time[i][0] + time[i][1] + (K-1)*elevator[i]);
		}
		
		System.out.println(min == Long.MAX_VALUE ? -1 : min);

		br.close();
		

	}
	
	static void dik(int start, int floor, List<int[]>[] g, long[][] time) {
		time[start][floor] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Long.compare(o1.time, o2.time));
		pq.offer(new Node(start,0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(int[] target : g[cur.room]) {
				if(time[target[0]][floor] < target[1] + cur.time) continue;
				time[target[0]][floor] = target[1] + cur.time;
				pq.offer(new Node(target[0], time[target[0]][floor]));
			}
		}
	}
}
