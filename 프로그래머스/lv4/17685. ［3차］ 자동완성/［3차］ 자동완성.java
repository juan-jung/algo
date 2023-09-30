import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Arrays.sort(words);
        // System.out.println(Arrays.toString(words));
        int words_cnt = words.length;
        int[] counts = new int[words_cnt];
        for(int i=0;i<words_cnt-1;i++) {
            String pre = words[i];
            String next = words[i+1];
            
            int len = Math.min(pre.length(), next.length());
            
            int count = 0;
            for(int j=0;j<len;j++) {
                if(pre.charAt(j) != next.charAt(j)) {
                    // count = j+1;
                    break;
                }
                count++;
            }
            
            if(count == pre.length()) counts[i] = Math.max(count, counts[i]);
            else counts[i] = Math.max(count + 1, counts[i]);
            
            counts[i+1] =  count+1;
            
        }
        // int total_cnt = 0;
        for( int count : counts) answer+=count; 
        return answer;
    }
}