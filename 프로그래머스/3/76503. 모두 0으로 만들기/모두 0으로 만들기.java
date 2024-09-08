import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        ArrayList<Integer>[] g = new ArrayList[a.length];  //간선 그래프
        
        long[] A = new long[a.length];
        
        
        //가중치 총합 0이 아니면 불가능
        long sum = 0;
        for(int i=0;i<a.length;i++) {
            sum += a[i];
            g[i] = new ArrayList<Integer>();
            A[i] = a[i];
        }
        
        if(sum!=0) return -1;
        
        
        //간선 그래프 만들기  
        int[] leafs = new int[a.length]; //1이면 리프노드
        for(int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
            leafs[edge[0]]++;
            leafs[edge[1]]++;
        }
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=0;i<leafs.length;i++) {
            if(leafs[i]==1) q.offer(i);
        }
        boolean[] v = new boolean[a.length];
        while(!q.isEmpty()) {
            int cur = q.poll();
                 
            if(leafs[cur] == 1) {
                leafs[cur]--;
                v[cur]=true;
                for(int next  : g[cur]) {
                    if(v[next]) continue;
                    leafs[next]--;
                    A[next] += A[cur];
                    answer += Math.abs(A[cur]);
                    q.offer(next);
                    break;
                }
                
            }
        }
        
        return answer;
    }
}