import java.util.*;

class Solution {
    
    static class Point {
        int vertex;
        int sheep;
        int wolf;
        ArrayList<Integer> g;
        int[] info;
        
        public Point(int vertex, int sheep, int wolf, ArrayList g, int[] info) {
            this.vertex = vertex;
            this.sheep = sheep;
            this.wolf = wolf;
            this.g = new ArrayList<Integer>(g);
            this.info = Arrays.copyOf(info, info.length);
        }
    }
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        int len = info.length;
        ArrayList<Integer>[] g = new ArrayList[len];
        for(int i=0;i<len;i++) g[i] = new ArrayList<Integer>();
        
        int elen = edges.length;
        for(int i=0;i<elen;i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            g[start].add(end);
            g[end].add(start);
        }        
        
        ArrayDeque<Point> q = new ArrayDeque<>();
        info[0] = -1;
        q.offer(new Point(0, 1, 0, g[0], info));
        int max = 0;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            if(cur.sheep <= cur.wolf) continue;
            
            max = Math.max(max, cur.sheep);
            
            for(int next : cur.g) {
               if(cur.info[next] == -1)  continue;
                
                ArrayList<Integer> tempG = new ArrayList<>(cur.g);
                tempG.removeAll(g[next]);
                tempG.addAll(g[next]);
                int[] tempInfo = Arrays.copyOf(cur.info, cur.info.length);
                tempInfo[next] = -1;
               if(cur.info[next] == 0) {
                   q.offer(new Point(next, cur.sheep+1, cur.wolf, tempG, tempInfo));
               }
                else if(cur.info[next] == 1) {
                    q.offer(new Point(next, cur.sheep, cur.wolf+1, tempG, tempInfo));
                }
            }
            
        }
        System.out.println(max);
        return max;
    }
}