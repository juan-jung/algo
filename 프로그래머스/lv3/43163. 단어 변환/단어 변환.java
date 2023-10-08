import java.util.*;

class Solution {
    
    class Vertex {
        String word;
        int cnt;
        
        public Vertex(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1.cnt, o2.cnt));
        boolean[] v = new boolean[words.length];
        Vertex vertex = new Vertex(begin, 0);
        pq.offer(vertex);
        
        while(!pq.isEmpty()) {
            Vertex cur = pq.poll();
            
            if(cur.word.equals(target)) {
                return cur.cnt;
            }
            
            for(int i=0;i<words.length;i++) {
                if(v[i] || !isPossible(cur.word, words[i])) continue;
                v[i] = true;
                pq.offer(new Vertex(words[i], cur.cnt+1));
            }
        }
        
       
        return answer;
    }
    
    public boolean isPossible(String cur, String convert) {
        int diff = 0;
        int len = cur.length();
        for(int i=0;i<len;i++) {
            if(cur.charAt(i) != convert.charAt(i)) diff++;
            if(diff > 1) return false;
        }
        return true;
    }
}