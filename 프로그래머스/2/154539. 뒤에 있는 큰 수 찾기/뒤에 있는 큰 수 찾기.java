import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        ArrayDeque<int[]> s = new ArrayDeque<>();
        
        for(int i=0;i<numbers.length;i++) {
           if(!s.isEmpty()) {
                while(!s.isEmpty() &&  s.peek()[1] < numbers[i]) {
                    int[] num  =  s.pop();
                    answer[num[0]]  =  numbers[i];
                }
            }
            s.push(new int[]{i,numbers[i]});
        }
        
        while(!s.isEmpty()) {
            int[] num  = s.pop();
            answer[num[0]] = -1;
        }
        return answer;
    }
}