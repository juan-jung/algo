import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        int[][] timeline = new int[n][m];
        Arrays.sort(timetable);
        
        int begin = timeConverter("09:00");
        int bus = 0;
        int limit = 0;
        end:for(int i=0;i<timetable.length;i++) {

            if(bus == n) break;
            
            int crew = timeConverter(timetable[i]);
            if(begin >= crew) {
                timeline[bus][limit++] = crew;
            }
            else {
                while(true) {
                    begin += t;
                    bus++;
                    if(begin >= crew) break;
                    if(bus == n) break end;
                }
                limit = 0;
                timeline[bus][limit++] = crew;
            }
            
            
            if(limit == m) {
                limit = 0;
                bus++;
                begin+=t;
            }
        }
        // for(int[] a : timeline) {
        //     for(int b : a) {
        //         System.out.print(reverseConverter(b)+" ");        
        //     }
        //     System.out.println();
        // }
        
        bus = n-1;
        for(int i=bus;i>=0;i--) {
            if(timeline[i][m-1] == 0) return reverseConverter(540 + (i)*t );
            else {
                return reverseConverter((timeline[i][m-1]-1));
            }
        }
        
        return answer;
    }
    
    public int timeConverter(String time) {
        String hour = time.substring(0,2);
        String min = time.substring(3,5);
        return Integer.parseInt(hour) * 60 + Integer.parseInt(min);
    }
    
    public String reverseConverter(int time) {
        
        if(time == 0) return "00:00";
        
        String hour = "";
        String min = "";
        
        if(time/60 < 10) hour+= 0 + "" + time/60;
        else hour+=time/60;
        
        if(time%60 < 10) min+= 0 + "" + time%60;
        else min += time%60;
        
        return hour + ":" + min;
    }
}