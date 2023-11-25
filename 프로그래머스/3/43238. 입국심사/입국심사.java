class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        long right = 0;
        for(int i=0;i<times.length;i++) right = Math.max(right, times[i]);
        right *= (long)n;
        while(left <= right) {
            long mid = (left + right) / 2;
            long people = 0;
            for(int i=0;i<times.length;i++) {
                people+=mid/times[i];
            }
            
           if(people < n) left = mid + 1;
           else {
               answer = mid;
               right = mid -1;
           }
        }
        return answer;
    }
}


/*

100,000 + log(10억 * 10억)

10 1024
11 2050
12 4000
13 8000
14 16000
15 32000
16 64000
17 128000
20 1000000
*/