import java.util.*;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;
        
        int[] newLand = new int[land.length*land.length];
        int idx = 0;
        long left = 0;
        for(int i=0;i<land.length;i++) {
            for(int j=0;j<land.length;j++) {
                newLand[idx++] = land[i][j];
                left += land[i][j];
            }
        }
        
        Arrays.sort(newLand);
        
        long passed = 0;
        for(int i=0;i<newLand.length;i++) {
            left -= newLand[i];
            answer = Math.min(answer,((long)i*(long)newLand[i] - passed) * (long)P + (left - ((long)newLand.length-(long)i-1L)*(long)newLand[i])*(long)Q);
            passed+=newLand[i];
        }
        
        return answer;
    }
}