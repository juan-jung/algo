import java.util.*;
class Solution {
    static int[]  answer;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        int[] discount = new int[emoticons.length];
        findMax(0,emoticons,discount,users);
        return answer;
    }
    
    void findMax(int cnt, int[] emoticons, int[] discount, int[][] users) {
        if(cnt == emoticons.length) {
            int plus_cnt = 0;
            int profit = 0;
            for(int i=0;i<users.length;i++) {
                int user_paid = 0;
                for(int j=0;j<emoticons.length;j++) {
                    if(discount[j] >= users[i][0])   {
                        user_paid  += emoticons[j] * (100-discount[j])/100;
                    }
                }
                if(user_paid >= users[i][1] ) {
                    plus_cnt++;
                }
                else profit +=  user_paid;
            }
            
            if(answer[0] < plus_cnt) {
                answer[0] = plus_cnt;
                answer[1] = profit;
            }
            else if(answer[0]==plus_cnt && answer[1] < profit) answer[1] = profit;
            return;
        }
        
        for(int i=10;i<=40;i+=10) {
            discount[cnt] = i;
            findMax(cnt+1,emoticons,discount,users);
        }
    }
}

/*
4^7 = 2^14 = 약 2만

사용자 * 이모티콘 = 100 * 7 = 700

-->> 천4백만

*/