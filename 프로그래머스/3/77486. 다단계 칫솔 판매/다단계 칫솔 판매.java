import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // enroll = new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        // referral = new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        // seller = new String[]{"sam", "sam", "sam", "sam"};
        // amount = new int[]{2, 3, 5, 4};
        int cnt = enroll.length;
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<cnt;i++) map.put(enroll[i],i);
        // int[] income = new int[cnt];
        // for(int i=0;i<seller.length;i++) {
        //     int number = map.get(seller[i]);
        //     income[number] += amount[i] * 100; 
        // }
        int[] answer = new int[cnt];
        // int center = 0;
        for(int i=0;i<seller.length;i++) {
            int money = amount[i] * 100;
            Integer person = map.get(seller[i]);
            while(person != null) {
                answer[person] += money;
                if(money < 10) break;
                money /= 10;
                answer[person]-=money;
                Integer next = map.get(referral[person]);
                person = next;
            }
        }       
        return answer;
    }
}