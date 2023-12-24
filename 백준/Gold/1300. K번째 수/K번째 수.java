

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		 Scanner sc = new Scanner(System.in);
		 long N = sc.nextLong();
		 int K = sc.nextInt();
		 
		 long l = 1; // 1 
		 long h = N*N; // 9
		 
		 while(l<h) {
			 long mid = (l+h)/2; // 5 
			 
			 long cnt = 0;
			 for(int i=1;i<=N;i++) {
				 cnt += Math.min(mid/i, N);
			 }
			 if(K <= cnt) h = mid;
			 else l = mid+1;
		 }
		 System.out.println(l);
		 sc.close();
	}
}


/*
완전탐색
1<=N<=10^5

배열 생성 
100,000 * 100,000 = 백억 --> 시간초과

---

N = 4

A배열
  1 2 3 4
1 1 2 3 4
2 2 4 6 8
3 3 6 9 12
4 4 8 1216

B배열
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
1 2 2 3 3 4 4 4 6 6  8  8  9  12 12 16

B[k] = x 의미
x보다 작거나 같은 원소가 최소 k개 존재

x값을 구하는 방법
A배열은 구구단 형식
1행에서 x보다 작거나 같은 값의 개수는 x/1
2행에서 x보다 작거나 같은 값의 개수는 x/2

이분탐색으로 x값을 조정해나가며 계산 결과가 같은 값을 찾는다
시간복잡도 계산
이분탐색 NlogN*N
100,000 * log 백억
1024 1024
약 40
--> 4,000,000 시간복잡도 ok

*/