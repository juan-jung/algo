

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] days = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 356};
		int[][] flowers = new int[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int sMonth = Integer.parseInt(st.nextToken());
			int sDay = Integer.parseInt(st.nextToken());
			int eMonth = Integer.parseInt(st.nextToken());
			int eDay = Integer.parseInt(st.nextToken());
			
			flowers[i][0] = days[sMonth] + sDay;
			flowers[i][1] = days[eMonth] + eDay;
		}
		
		Arrays.sort(flowers, (o1,o2) -> Integer.compare(o1[0], o2[0]));
		
		int count = 0; //꽃의 개수
		int startDate = days[3] + 1;
		int endDate = days[11]+30;
		int idx = 0;
		while(true) {
			boolean flag = false;
			int temp = startDate;
			for(int i=idx;i<N;i++) {
				if(flowers[i][0] > startDate) {
					idx = i;
					break;
				}
				if(temp < flowers[i][1]) {
					temp = flowers[i][1];
					flag = true;
				}
			}
			if(!flag) { 
				count =0;
				break;
			}
			else {
				count++;
				startDate = temp;
				if(startDate > endDate) break;
			}
		}
		System.out.println(count);
	}
}


