class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        findNum(0,0,numbers, target);
        return answer;
    }
    
    void findNum(int cnt, int cal, int[] number, int target) {
        if(cnt==number.length) {
            if(cal==target) answer++;
            return;
        }
        findNum(cnt+1,cal+number[cnt],number,target);
        findNum(cnt+1,cal-number[cnt],number,target);
    }
}