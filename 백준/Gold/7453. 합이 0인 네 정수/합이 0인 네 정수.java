import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 시간제한 : 12초 / 메모리 제한 : 1023MB
	 * 크기가 같은 정수 배열 A,B,C,D
	 * A[a] + B[b] + C[c] + D[d] = 0인 (a,b,c,d)쌍의 개수
	 * 배열의 크기 n(1<=n<=4000), 정수 절댓값의 최대는 2^28
	 * 
	 * 배열을 두개씩 묶고, 4000*4000으로 모든 경우의 수 구하기 -> 16,000,000 * 2 = 32,000,000
	 * 정렬 -> 16,000,000 log 16,000,000 = 약 384,000,000
	 * 한쪽은 최댓값, 한쪽은 최소값부터 합을 확인	
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] AB = new int[n*n];
		int[] CD = new int[n*n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				AB[i*n+j] = A[i] + B[j];
				CD[i*n+j] = C[i] + D[j];
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		long ans = 0;
		int AB_idx = 0, CD_idx = CD.length-1;
		while(AB_idx < AB.length && CD_idx >=0) {
			long sum = AB[AB_idx] + CD[CD_idx];
			if(sum == 0) {
				long AB_cnt = 1;
				long CD_cnt = 1;
				for(int i=AB_idx+1;i<AB.length;i++) {
					if(AB[i]==AB[AB_idx]) {
						AB_cnt++;
						AB_idx=i;
					} 
					else {
						break;
					}
				}
				for(int i=CD_idx-1;i>=0;i--) {
					if(CD[i]==CD[CD_idx]) {
						CD_cnt++;
						CD_idx = i;
					}
					else {
						break;
					}
				}
				
				ans += AB_cnt * CD_cnt;
				AB_idx++;
				CD_idx--;
			}
			else if(sum < 0) AB_idx++;
			else CD_idx--;
		}
		
		System.out.println(ans);
		br.close();
	}
	
	
}
