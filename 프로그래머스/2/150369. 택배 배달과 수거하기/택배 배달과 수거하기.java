class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int truckD = 0;
        int truckP = 0;
        for(int i=n-1;i>=0;i--) {
            int cnt = 0;
            truckD -= deliveries[i];
            truckP -= pickups[i];
            
            while(truckD < 0 || truckP < 0) {
                truckD += cap;
                truckP += cap;
                cnt++;
            }
            
            answer += cnt * (i+1) *2;
        }
        
        return answer;
    }
}


/*
1 0 3 1 2
0 3 0 4 0

10200
02010 --> 10





1 0 2 0 1 0 2
0 2 0 1 0 2 0

cap 2

7 +  5 + 3 

2 2 0 0
0 0 2 2 

4 + 

*/