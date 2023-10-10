import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o2,o1));
        
        for(int i=0;i<works.length;i++) pq.offer(works[i]);
        
        for(int i=1;i<=n;i++) {
            int cur = pq.poll();
            cur--;
            if(cur<0) cur=0;
            pq.offer(cur);
        }
        
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            answer+=cur*cur;
        }
        return answer;
    }
}