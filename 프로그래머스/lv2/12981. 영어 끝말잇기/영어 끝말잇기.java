import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        
        String lastWord = words[0];
        HashSet<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i=1;i<words.length;i++) {
            if(set.contains(words[i]) || lastWord.charAt(lastWord.length()-1) != words[i].charAt(0)) {
                // System.out.println(words[i] + " " + i);
                answer = new int[]{(i)%n+1, i/n+1};
                break;
            }
            set.add(words[i]);
            lastWord = words[i];
        }
        

        return answer;
    }
}