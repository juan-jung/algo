import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        ArrayList<Character> nums = new ArrayList<>();
        
        int num = 0;
        while(nums.size() < m * t) {
            String convertedNum = convertToN(n,num++);
            for(int i=0;i<convertedNum.length();i++) {
                nums.add(convertedNum.charAt(i));
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<t;i++) {
            answer.append(nums.get((p-1) + i*m));
        }
        
        return answer.toString();
    }
    
    String convertToN(int n, int num) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            if(num < n) {
                if(num >= 10) sb.insert(0,(char)('A' + num - 10));
                else sb.insert(0,num);
                break;    
            }
            
            if(num%n >= 10) sb.insert(0,(char)('A' + num%n - 10));
            else sb.insert(0,num%n);
            
            num /= n;
        }
        
        return sb.toString();
    }
}