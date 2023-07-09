import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(today, ".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int sum = year * 12 * 28 + month * 28 + day;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String term : terms) {
            st=new StringTokenizer(term, " ");
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        int index = 1;
        for(String privacie : privacies) {
            st=new StringTokenizer(privacie, " ");
            String Date = st.nextToken();
            String term = st.nextToken();
            
            int duration = map.get(term);
            
            st = new StringTokenizer(Date, ".");
            int syear = Integer.parseInt(st.nextToken());
            int smonth = Integer.parseInt(st.nextToken());
            int sday = Integer.parseInt(st.nextToken());
            int sum2 = syear * 12 * 28 + smonth * 28 + sday + duration * 28;
            
            if(sum >= sum2 ) answer.add(index);
            index ++;
        }
        System.out.println(answer);
        return answer.stream().mapToInt(i->i).toArray();
    }
}