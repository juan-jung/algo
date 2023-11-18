import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = -1;
        Arrays.sort(B);
        Arrays.sort(A);
        int win = 0;
        int A_idx = 0;
        int B_idx = 0;
        while(A_idx < A.length && B_idx < B.length) {
            if(A[A_idx] >= B[B_idx]) B_idx++;
            else {
                win++;
                A_idx++;
                B_idx++;
            }
        }
        System.out.println(win);
        return win;
    }
}