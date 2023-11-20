import java.util.*;
class Solution {
    class G {
        int sum;
        PriorityQueue<int[]> pq;
        G(int sum) {
            this.sum = sum;
            this.pq = new PriorityQueue<int[]>((o1,o2)-> {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o2[1] - o1[1];
            });
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, G> map = new HashMap<>();
        for(int i=0;i<genres.length;i++) {
            G g = map.get(genres[i]);
            if(g==null) g = new G(0);
            g.pq.offer(new int[]{i,plays[i]});
            g.sum+=plays[i];
            map.put(genres[i], g);
        }
       
        List<String> keys = new ArrayList<>(map.keySet());
        keys.sort((o1,o2) -> Integer.compare(map.get(o2).sum, map.get(o1).sum));
        
        answer = new int[keys.size()*2];
        int idx =0;
        for(String s : keys) {
            if(!map.get(s).pq.isEmpty()) answer[idx++] = map.get(s).pq.poll()[0];
            if(!map.get(s).pq.isEmpty()) answer[idx++] = map.get(s).pq.poll()[0];
        }
        return Arrays.copyOf(answer, idx);
    }
}