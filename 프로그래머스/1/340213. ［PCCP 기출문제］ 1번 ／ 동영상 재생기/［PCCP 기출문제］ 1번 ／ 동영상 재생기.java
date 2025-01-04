class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int video_len_sec = timeToSec(video_len);
        int pos_sec = timeToSec(pos);
        int op_start_sec = timeToSec(op_start);
        int op_end_sec = timeToSec(op_end);
        if(pos_sec >= op_start_sec && pos_sec <= op_end_sec) pos_sec = op_end_sec;
        
        for(String command : commands) {
            
            if(command.charAt(0)=='p') {
                pos_sec -= 10;
            }
            else if(command.charAt(0)=='n') {
                pos_sec += 10;
            }
            
            if(pos_sec < 0) pos_sec = 0;
            else if(pos_sec > video_len_sec) pos_sec = video_len_sec;
            
            if(pos_sec >= op_start_sec && pos_sec <= op_end_sec) pos_sec = op_end_sec;
        }
           
        return secToTime(pos_sec);
    }
    
    public String secToTime(int sec) {
        int min = sec/60;
        sec = sec%60;
        return (min > 10 ? min : "0" + min) + ":" + (sec > 10 ? sec : "0"+sec);
    }
    
    public int timeToSec(String time) {
        return Integer.parseInt(time.substring(0,2))*60 + Integer.parseInt(time.substring(3,5));
    }
}