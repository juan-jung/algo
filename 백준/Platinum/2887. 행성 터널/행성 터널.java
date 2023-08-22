

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		ArrayList<int[]>[] planets =new ArrayList[3];
		for(int i=0;i<3;i++) planets[i]=new ArrayList<int[]>();
		
		int[] dist=new int[N];
		
		for(int i=0;i<N;i++) {	
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			planets[0].add(new int[] {i,Integer.parseInt(st.nextToken())});
			planets[1].add(new int[] {i,Integer.parseInt(st.nextToken())});
			planets[2].add(new int[] {i,Integer.parseInt(st.nextToken())});
			dist[i]=Integer.MAX_VALUE;
		}
		
		ArrayList<int[]>[] g=new ArrayList[N];
		for(int i=0;i<N;i++) g[i]=new ArrayList<int[]>();		
		
		for(int j=0;j<3;j++) {
			Collections.sort(planets[j], (o1,o2)->Integer.compare(o1[1], o2[1]));
			for(int i=1;i<N;i++) {
				int first[] = planets[j].get(i-1);
				int second[] = planets[j].get(i);
				
				
				int d= Math.abs(first[1]-second[1]);
				g[first[0]].add(new int[] {second[0],d});
				g[second[0]].add(new int[] {first[0],d});
			}			
		}
		
		boolean[] v=new boolean[N];
		int res=0,cnt=0;
		dist[0]=0;
		PriorityQueue<int[]> pq=new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {0,0});
		while(!pq.isEmpty()) {
			int[] cur=pq.poll();
			int minP=cur[0];
			int min=cur[1];
			
			if(v[minP]) continue;
			v[minP]=true;
			res+=min;
			
			if(cnt++==N-1) break;
			
			for(int a[]: g[minP]) {
				if(!v[a[0]] && dist[a[0]] > a[1]) {
					dist[a[0]]=a[1];
					pq.offer(new int[] {a[0],a[1]});
				}
			}
		}
		//System.out.println(Arrays.toString(dist));
		System.out.println(res);
	}
}
