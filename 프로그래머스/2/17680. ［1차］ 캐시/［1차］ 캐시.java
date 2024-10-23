import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        
        
        for(int i=0;i<cities.length;i++) {
            String city = cities[i].toLowerCase();
            if(map.containsKey(city)) {
                answer += 1;
                map.put(city,i);
            }
            else {
                answer += 5;
                if(cacheSize==0) continue;
                if(map.size()==cacheSize) {
                    String minKey = "";
                    int min = Integer.MAX_VALUE;
                    for(String key : map.keySet()) {
                        if(min > map.get(key)) {
                            min = map.get(key);
                            minKey = key;
                        }
                    }
                    map.remove(minKey);
                }
                map.put(city, i);
            }
        }
        return answer;
    }
}