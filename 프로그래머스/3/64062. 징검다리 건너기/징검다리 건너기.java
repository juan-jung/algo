class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        answer = getCrossNum(stones, k);
        return answer;
    }
    
    public int getCrossNum(int[] stones, int k) {
        int start = 1;
        int end = 200000000;
        
        while(start <= end) {
            int mid = (start + end)/2;
            
            if(canCross(stones, k, mid)) {
                start = mid + 1;
            }
            else end = mid-1;
        }
        
        return end;
    }
    
    public boolean canCross(int[] stones, int k, int people) {
        int jump = 0;
        
        for(int stone : stones) {
            if(stone < people) {
                jump++;
                if(jump==k) return false;
            }
            else jump = 0;
        }
        
        return true;
    }
}