import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        Set<String> strs_set = new HashSet<>();
        Collections.addAll(strs_set, strs);
        
        // Initialize the dp array where dp[i] represents the minimum number of substrings needed to form t[0:i]
        int n = t.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // No substrings needed to form an empty string

        // Dynamic programming to fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (strs_set.contains(t.substring(j, i))) {
                    if (dp[j] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        // The answer is in dp[n] if it's not Integer.MAX_VALUE, else it's not possible to form t
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}