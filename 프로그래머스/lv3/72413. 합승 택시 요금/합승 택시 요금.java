import java.util.*;
class Solution {
    static ArrayList<int[]>[] g;
    static int[] dist;
    static int[] dist1;
    static int[] dist2;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        dist = new int[n+1];
        dist1 = new int[n+1];
        dist2 = new int[n+1];
        g = new ArrayList[n+1];
        for(int i=0;i<n+1;i++) {
            dist[i] = Integer.MAX_VALUE;
            dist1[i] = Integer.MAX_VALUE;
            dist2[i] = Integer.MAX_VALUE;
            g[i] = new ArrayList<>();
        }
        
        for(int i=0;i<fares.length;i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int value = fares[i][2];
            
            g[start].add(new int[]{end, value});
            g[end].add(new int[]{start, value});
        }
        
       
        
        dik(s, dist);
        dik(a, dist1);
        dik(b, dist2);
        for(int i=1;i<n+1;i++) {
            if(dist[i] == Integer.MAX_VALUE || dist1[i]==Integer.MAX_VALUE || dist2[i] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, dist[i] + dist1[i] + dist2[i]);
        }
        System.out.println(Arrays.toString(dist));
         System.out.println(Arrays.toString(dist1));
         System.out.println(Arrays.toString(dist2));

        
        return answer;
    }
    
    static void dik(int first, int[] dists) {
        dists[first] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        pq.offer(new int[]{first, 0});
        
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int minV = cur[0];
            int min = cur[1];
            
            // if(min > dist[minV]) continue;
            for(int[] now : g[minV]) {
                if(dists[now[0]] > min + now[1]) {
                    dists[now[0]] = min + now[1];
                    pq.offer(new int[]{now[0], min + now[1]});
                }
            }
        }
    }
}