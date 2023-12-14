import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int pCnt = 0;
		int mCnt = 0;
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
			if(arr[i] > 0) pCnt++;
			else if(arr[i] < 0) mCnt++;
		}
		Arrays.sort(arr);
		long ans = 0;
		for(int i=N-1;i>N-pCnt;i-=2) {
			if(arr[i]==1||arr[i-1]==1) ans+=arr[i]+arr[i-1];
			else ans+=arr[i]*arr[i-1];
		}
		if(pCnt%2==1) ans+=arr[N-pCnt];
		
		for(int i=0;i<mCnt-1;i+=2) ans+=arr[i]*arr[i+1];

		if(mCnt%2==1) {
			ans+=arr[mCnt-1];
			if(mCnt!=N && arr[mCnt]==0) ans-=arr[mCnt-1];
		}
		System.out.println(ans);
	}
	
}