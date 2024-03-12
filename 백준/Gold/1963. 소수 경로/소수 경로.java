
import java.io.*;
import java.util.*;

/**
 * 시간제한 : 2초, 메모리 : 256MB
 * 
 * 4자리 소수 두개를 입력받아 최소 변환 단계 출력
 * ex) 1033 1733 3733 3739 3779 8779 8179
 * 
 * 입력  : 첫 줄에 test case의 수 T가 주어진다. 다음 T줄에 걸쳐 각 줄에 1쌍씩 네 자리 소수가 주어진다.
 * 출력 : 각 test case에 대해 두 소수 사이의 변환에 필요한 최소 회수를 출력한다. 불가능한 경우 Impossible을 출력한다.
 * 
 * 제한사항 
 * 1. 네자리 소수 : 0039와 같은 1000미만의 비밀번호x -> 1000이상 9999이하 소수로만 변환되어야함
 * 
 * @author crumb
 */
public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		boolean[] isPrime = new boolean[10000]; // false : 소수
		checkPrime(isPrime);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int min = minCount(A,B,isPrime);
			sb.append((min == -1 ? "Impossible" : min)+"\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static int minCount(int A, int B, boolean[] isPrime) {
		boolean[] v = new boolean[10000];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {A, 0});
		v[A] = true;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(cur[0] == B) {
				return cur[1];
			}
			
			for(int i=0;i<10;i++) {
				for(int j=1;j<=1000;j*=10) {
					if(i==0 && j==1000) continue;
					int next = (cur[0] - (cur[0]%(j*10))/j*j) + (i*j);
					if(v[next] || isPrime[next]) continue;
					pq.offer(new int[] {next, cur[1]+1});
					v[next] = true;
				}
			}
			
		}
		return -1;
	}
	
	static void checkPrime(boolean[] isPrime) {
		isPrime[0] = isPrime[1] = true; //0과1 소수 제외
		
		//2부터 9999까지 검사
		int end = (int)Math.sqrt(9999);
		for(int i=2;i<end;i++) {
			if(!isPrime[i]) {
				int start = i*i;
				for(int j=start;j<10000;j+=i) {
					isPrime[j] = true;
				}
			}
		}
	}
}

//예제1)
//3
//1033 8179
//1373 8017
//1033 1033
//출력
//6
//7
//0

