
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(m==0 && n==0) break;
			int sum =0;
			ArrayList<int[]>[] g = new ArrayList[m];
//			int[][] g=new int[m][m];
			boolean[] v=new boolean[m];
			int[] minEdge=new int[m];
			for(int i=0;i<m;i++) {
				g[i] = new ArrayList<int[]>();
				minEdge[i] = Integer.MAX_VALUE;
			}
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine()," ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				sum+=value;
				g[start].add(new int[] {end, value});
				g[end].add(new int[] {start, value});
			}
			
			
			
			int result=0,cnt=0;
			PriorityQueue<int[]> pq=new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
			minEdge[0]=0;
			pq.offer(new int[] {0,0});//정점, 가중치
			while(!pq.isEmpty()) {
				int[] cur=pq.poll();
				int minVertex=cur[0];
				int min=cur[1];
				if(v[minVertex]) continue;

				v[minVertex]=true;
				result+=min;
				if(cnt++==-1) break;
				for(int[] a : g[minVertex]) {
					if(!v[a[0]] && minEdge[a[0]] > a[1]) {
						minEdge[a[0]] = a[1];
						pq.offer(new int[] {a[0], a[1]});
					}
				}
			}
			System.out.println(sum-result);
		}
		br.close();
	}		
}
