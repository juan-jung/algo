import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] boxes = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<n;i++) boxes[i] = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		int[] LIS = new int[n];
		for(int i=0;i<n;i++) {
			LIS[i] = 1;
			for(int j=0;j<i;j++) {
				if(boxes[i] > boxes[j]) {
					LIS[i] = Math.max(LIS[i], LIS[j] + 1);
				}
			}
			ans  = Math.max(ans, LIS[i]);
		}
		
		System.out.println(ans);
		br.close();
	}
}
