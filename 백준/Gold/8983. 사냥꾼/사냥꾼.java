import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int M = Integer.parseInt(st.nextToken()); // 사대 수
		int N = Integer.parseInt(st.nextToken()); // 동물 수
		int L = Integer.parseInt(st.nextToken()); // 사정거리
		
		st = new StringTokenizer(br.readLine()," ");
		int[] points = new int[M];
		for(int i=0;i<M;i++) points[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(points);
		
		int ans = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(y>L) continue;
			int min = x-L+y;
			int max = x+L-y;
			int hi = M-1;
			int lo = 0;
			while(hi >= lo) {
				int mid = (hi+lo)/2;
				if(points[mid]>=min && points[mid]<=max) {
					ans++;
					break;
				}
				else if(points[mid] < min) {
					lo = mid+1;
				}
				else if(points[mid] > max) {
					hi = mid-1;
				}
			}
		}
		System.out.println(ans);
		br.close();
	}
}
/*
시간제한 1초 메모리 128MB

사대의 수 M (1<=M<=100,000)
동물의 수 N (1<=N<=100,000)
사정거리 L (1<=L<=1,000,000,000)
모든 좌표 값은 1,000,000,000 10억이하

완전탐색
100,000 * 100,000 = 100,000,000,000 천억

log100,000 = 16.xx
*/
