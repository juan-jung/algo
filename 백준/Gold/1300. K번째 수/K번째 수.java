import java.io.*;
import java.util.*;

public class Main {
    /*
    제한시간 2초

    10^9 = 1,000,000,000 = 10억

    11 12 13
    21 22 23
    31 32 33

    1 2 3
    2 4 6
    3 6 9

    1 2 2 3 3 4 6 6 9

    4이하 수 개수 = 3+2+1 = 6
    5이하 수 개수 = 3+2+1 = 6
    6이하 수 개수 = 3+3+2 = 8

    B[7] = 6 => B[k] = x => x보다 작거나 같은 수가 최소 k개 존재
    수의 범위는 1이상 n^2이하
    n^2/2 를 시작으로 최소 몇개 인지 이분탐색 -> 이분탐색 : log(n^2) = 40.8, 최소갯수 행마다 ==> 40.8 * n(10^5=100,000) =====> 약 4백만 ok

     */

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();

        int ans = findKst(n,k);
        System.out.println(ans);
        sc.close();
    }

    static int findKst(int n, long k) {
        int start = 1;
        int end = (int)Math.pow(n,2);
        while(start < end) {
            int mid = (start+end)/2;
            long cnt = 0; //최대 n^2
            for(int i=1;i<=n;i++) cnt += Math.min(mid/i, n);
            if(cnt < k) start = mid+1;
            else end = mid;
        }
        return end;
    }
}
