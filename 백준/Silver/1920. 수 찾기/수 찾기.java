import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<N;i++) A[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(A);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) sb.append((Arrays.binarySearch(A, Integer.parseInt(st.nextToken())) >= 0 ? 1 : 0) + "\n");

		System.out.println(sb);
		br.close();
	}
}

/*
log100,000 = 16.xxx 
10 1024
11 2050
12 4100
13 8200
14 16400
15 32800
16 65000
17 12..

1,600,000 + 1,600,000 = 3,200,000

*/
