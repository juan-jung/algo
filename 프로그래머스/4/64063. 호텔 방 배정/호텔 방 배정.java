import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        Map<Long, Long> map = new HashMap<>();
        
        for(int i=0;i<room_number.length;i++) {
            Long room = room_number[i];
            ArrayList<Long> passes = new ArrayList<>();
            passes.add(room);
            while(true) {
                Long next = map.get(room);
                if(next==null) {
                    answer[i] = room;
                    for(long pass : passes) map.put(pass, room+1);
                    break;
                }
                else {
                    passes.add(next);
                    room = next;    
                }
            }
        }
        
        
        return answer;
    }
}
// 1,000,000,000,000