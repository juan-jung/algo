class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int end = 1; //전파가 안닿기 시작하는 곳
        for(int station : stations) {
            int start = Math.max(station - w,0);      
            int no_5g_cnt = Math.max(start - end,0);
            answer += no_5g_cnt == 0 ? 0 : (no_5g_cnt-1)/(2*w+1) +1;
            end = station + w + 1;
        }
        
        if(stations[stations.length-1] + w < n) {
            answer += (n-stations[stations.length-1] - w -1)/(2*w+1) +1;
        }

        return answer;
    }
}