import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		boolean[] isPrime = new boolean[10000]; //F:소수 T:소수x
		for(int i=2;i<10000;i++) {
			for(int j=i+1;j<10000;j++) {
				if(j%i==0) isPrime[j] = true;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			boolean[] v = new boolean[10000];
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
			pq.offer(new int[] {A,0});
			v[A] = true;
			int ans = -1;
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				
				if(cur[0] == B) {
					ans = cur[1];
					break;
				}
				
				for(int i=0;i<10;i++) {
					for(int j=1;j<1001;j*=10) {
						if(j==1000 && i==0) continue;
						int next = cur[0] - (cur[0]%(j*10)-cur[0]%j) + j*i;
						if(isPrime[next] || v[next]) continue;
						pq.offer(new int[] {next, cur[1]+1});
						v[next] = true;
					}
				}
			}
			System.out.println(ans == -1 ? "Impossible" : ans);
		}
		
		br.close();
	}
}
