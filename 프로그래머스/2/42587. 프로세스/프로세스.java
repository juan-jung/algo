import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i=0;i<priorities.length;i++) {
            q.offer(new int[]{priorities[i],i});
        }
        
        Arrays.sort(priorities);
        int max_idx = priorities.length-1;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0]==priorities[max_idx]) {
                cnt++;
                max_idx--;
                if(cur[1]==location) break;
            }
            else {
                q.offer(cur);
            }
        }
        
        return cnt;
    }
}