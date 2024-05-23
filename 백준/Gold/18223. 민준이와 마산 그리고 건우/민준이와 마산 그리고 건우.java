import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken())-1;
        ArrayList<int[]>[] g = new ArrayList[V];
        int[] dist = new int[V];
        int[] dist2 = new int[V];
        final int INF = Integer.MAX_VALUE;
        for(int i=0;i<V;i++) {
            dist[i] = INF;
            dist2[i] = INF;
            g[i] = new ArrayList<int[]>();
        }
        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            g[a].add(new int[]{b,c});
            g[b].add(new int[]{a,c});
        }

        //0->V-1
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        boolean[] v = new boolean[V];
        pq.offer(new int[]{0,0});
        dist[0] = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[0]==V-1) break;

            if(v[cur[0]]) continue;
            v[cur[0]] = true;

            for(int[] target : g[cur[0]]) {
                if(dist[target[0]] > cur[1]+target[1] && !v[target[0]]) {
                    dist[target[0]] = cur[1] + target[1];
                    pq.offer(new int[]{target[0], dist[target[0]]});
                }
            }
        }

        //P -> V-1
        pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        v = new boolean[V];
        pq.offer(new int[]{P,0});
        dist2[P] = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[0]==V-1) break;

            if(v[cur[0]]) continue;
            v[cur[0]] = true;

            for(int[] target : g[cur[0]]) {
                if(dist2[target[0]] > cur[1]+target[1] && !v[target[0]]) {
                    dist2[target[0]] = cur[1] + target[1];
                    pq.offer(new int[]{target[0], dist2[target[0]]});
                }
            }
        }
        
       System.out.println(dist[V-1]==dist[P]+dist2[V-1] && dist2[V-1]!=INF?"SAVE HIM" : "GOOD BYE");
        br.close();
    }
}
