import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        int wh_a = scores[0][0];
        int wh_b = scores[0][1];
        int wh_score = wh_a + wh_b;
        
        Arrays.sort(scores, (o1,o2) -> {
            if(o1[0]==o2[0]) return o1[1]-o2[1];
            return o2[0] - o1[0];
        });
        
        int max_b = 0;
        for(int[] score : scores) {
            if(wh_a < score[0] && wh_b < score[1]) return -1;
            if(score[1] >= max_b) {
                max_b = score[1];
                if(score[0] + score[1] > wh_score) answer++;
            }
        }
        
        return answer+1;
    }
}
/*
근무태도 + 동료 평가 ->  인센티브

어떤 한 사원이 다른 사원보다 두 점수가 모두 낮은 경우가  한번이라도 있다면 인센x 

그렇지 않은 사원  -> 두 점수의 합 석차 순 // 동석차일 경우 다음 석차 그만큼 건너뜀

32 32 22 21 14

32 32 21 22 14

*/