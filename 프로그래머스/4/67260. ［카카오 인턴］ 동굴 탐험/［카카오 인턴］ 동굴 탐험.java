import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        
        int cnt = 0;
        
        ArrayList<Integer>[] g = new ArrayList[n];
        for(int i=0;i<n;i++) g[i] = new ArrayList<Integer>();
        for(int i=0;i<path.length;i++) {
            int n1 = path[i][0];
            int n2 = path[i][1];
            g[n1].add(n2);
            g[n2].add(n1);
        }
        
        Map<Integer, Integer> orderMap = new HashMap<>();
        Set<Integer> locked = new HashSet<>();
        for(int i=0;i<order.length;i++) {
            orderMap.put(order[i][0],order[i][1]);
            locked.add(order[i][1]);
        }
        if(locked.contains(0)) return false;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[n];
        q.offer(0);
    
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            if(v[cur]) continue;
            
            if(orderMap.get(cur)!=null) {
                for(int next : g[orderMap.get(cur)]) {
                    if(v[next]) {
                        q.offer(orderMap.get(cur));
                        break;
                    }
                }
                
                locked.remove(orderMap.get(cur));
                orderMap.remove(cur);
            }
            
            v[cur] = true;
            cnt++;
            
            for(int next : g[cur]) {
                if(locked.contains(next) || v[next]) continue;
                q.offer(next);
            }
        }
        
        if(cnt!=n) answer = false;
        
        return answer;
    }
}