import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int[][] times = new int[lines.length][2];
        for(int i=0;i<lines.length;i++) {
            times[i] = getTime(lines[i]);
        }

        int max = 1;
        for(int i=0;i<times.length;i++) {
            int cnt = 0;
            for(int j=i;j<times.length;j++) {
                if(times[i][1] + 999 >= times[j][0]) cnt++;
            }
            max = Math.max(max,cnt);
        }
        return max;
    }

    public int[] getTime(String line) {
        int hour = Integer.parseInt(line.substring(11,13)) * 60 * 60;
        int min = Integer.parseInt(line.substring(14,16)) * 60;
        int sec = Integer.parseInt(line.substring(17,19));
        int ms = Integer.parseInt(line.substring(20,23));
        int endTime = (hour+min+sec) * 1000 + ms;
        int startTime = endTime - (int)(Double.parseDouble(line.substring(24,line.length()-1)) * 1000) + 1;
        return new int[]{startTime, endTime};
    }
}



