import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int len = sequence.length;
        int[] seq1 = new int[len];
        int[] seq2 = new int[len];
        int flag = 1;
        int flag2 = -1;
        for(int i=0;i<len;i++) {
            seq1[i] = sequence[i] * flag;
            seq2[i] = sequence[i] * flag2;
            flag *= -1;
            flag2 *= -1;
        }
        
        long[] dp = new long[len];
        long[] dp2 = new long[len];
        dp[0] = seq1[0];
        dp2[0]=seq2[0];
        answer = Math.max(answer, dp[0]);
        answer = Math.max(answer, dp2[0]);
        for(int i=1;i<len;i++) {
            dp[i] = Math.max(dp[i-1]+seq1[i], seq1[i]);
            dp2[i] = Math.max(dp2[i-1]+seq2[i], seq2[i]);
            answer = Math.max(answer, dp[i]);
            answer = Math.max(answer, dp2[i]);
        }
        
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(dp2));
        
        return answer;
    }
}

/*

100,000 * 500,000
= 50,000,000,000
[2, -3, -6, -1, 3, 1, 2, -4]
2 -1 -6 -1 3 4 6 2

[-2, 3, 6, 1, -3, -1, -2, 4]
-2 3 9 10 7 6 4 8
*/

