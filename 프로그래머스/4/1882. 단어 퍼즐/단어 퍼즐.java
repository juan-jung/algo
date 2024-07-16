import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        Set<String> strs_set = new HashSet<>();
        Collections.addAll(strs_set, strs);
        
        int tLen = t.length();
        int[] dp =  new int[tLen+1];
        final int INF =  Integer.MAX_VALUE;
        Arrays.fill(dp,INF);
        dp[tLen] = 0;
        
        for(int i=tLen-1;i>=0;i--) {
            for(int j=0;j<5 && i+j<tLen;j++) {
                if(dp[i+j+1]!=INF  &&  strs_set.contains(t.substring(i,i+j+1))) {
                    dp[i] =  Math.min(dp[i], dp[i+j+1]+1);
                }
            }
        }
       
        return dp[0]==INF ? -1 : dp[0];
    }
}