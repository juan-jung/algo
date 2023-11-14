import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        // gems = new String[]{"A", "B", "C", "B", "F", "D", "A", "F", "B", "D", "B"};
        Map<String, Integer> map = new HashMap<>();
        int length = new HashSet<>(Arrays.asList(gems)).size();
        int start = 0;
        int min = Integer.MAX_VALUE;
        int minS = -1;
        int minE = -1;
        for(int end=0;end<gems.length;end++) {
            int count = map.getOrDefault(gems[end], 0);
            map.put(gems[end], count+1);
            while(start < end && map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start])-1);
                start++;
            }

            if(map.size()==length) {
                int cnt = end - start + 1;
                // if(cnt == length) return new int[]{start+1, end+1};
                if(min > cnt) {
                    min = cnt;
                    minS = start;
                    minE = end;
                }
                int start_cnt = map.get(gems[start]) - 1;
                if(start_cnt == 0) map.remove(gems[start]);
                else map.put(gems[start], start_cnt);
                start++;
            }
        }
        
        return new int[]{minS+1, minE+1};
    }
}