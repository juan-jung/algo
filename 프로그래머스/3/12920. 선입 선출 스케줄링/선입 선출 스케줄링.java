import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;  // 초기 작업 수가 코어 수 이하일 때 바로 return
        }
        
        n -= cores.length;  // 처음에 모든 코어가 작업을 한 번씩 처리
        
        int left = 1;
        int right = 10000 * n;
        int time = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int completedJobs = 0;

            for (int core : cores) {
                completedJobs += mid / core;
            }
            
            if (completedJobs >= n) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        int completedBeforeTime = 0;
        for (int core : cores) {
            completedBeforeTime += (time - 1) / core;
        }
        
        int remainingJobs = n - completedBeforeTime;
        
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                remainingJobs--;
                if (remainingJobs == 0) {
                    return i + 1;  // 코어 번호는 1부터 시작하므로 i + 1을 반환
                }
            }
        }
        
        return -1;  // 예외 처리 (논리적으로 도달하지 않는 곳)
    }
}
