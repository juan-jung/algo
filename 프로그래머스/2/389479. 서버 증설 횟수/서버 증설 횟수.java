import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        ArrayDeque<int[]> addedServers = new ArrayDeque<>();
        int addedServerCnt = 0;
        
        for(int i=0;i<players.length;i++) {
            while(!addedServers.isEmpty() && addedServers.peek()[0] <= i) {
                addedServerCnt -= addedServers.poll()[1];
            }
            
            int serverNeedsCnt = (players[i]-(m-1))/m;
            if(players[i]>=m && (players[i]-(m-1))%m!=0) serverNeedsCnt+=1;
            
            int tobeAddedServerCnt = serverNeedsCnt - addedServerCnt;
            if(tobeAddedServerCnt > 0) {
                answer += tobeAddedServerCnt;
                addedServers.offer(new int[]{i+k,tobeAddedServerCnt});
                addedServerCnt = serverNeedsCnt;
            }
        }
        return answer;
    }
}