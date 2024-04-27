import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1,o2)->Integer.compare(o1[0],o2[0]));
        
        int cur_time = jobs[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        pq.offer(new int[]{jobs[0][0], jobs[0][1]});
        for(int i=1;i<jobs.length;i++) {
            if(jobs[i][0] <= cur_time) {
                pq.offer(new int[]{jobs[i][0], jobs[i][1]});
            }
            else {
                if(pq.isEmpty()) {
                    cur_time = jobs[i][0];
                    pq.offer(new int[]{jobs[i][0],jobs[i][1]});
                }
                else {
                    int[] cur = pq.poll();
                    cur_time += cur[1];
                    answer += cur_time - cur[0];
                    i--;
                }
            }  
        }    
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            cur_time += cur[1];
            answer += cur_time - cur[0];
        }
        
        return answer/jobs.length;
    }
}