

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
		 R : 배열에 있는 수의 순서를 뒤집는 함수
		 D : 첫번 째 수를 버리는 함수 , 빈 배열일 경우 에러
	 */
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		end:for(int tc=1;tc<=T;tc++) {
			String method = br.readLine();
			int N=Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수
			StringTokenizer st=new StringTokenizer(br.readLine(), "[,]");
			ArrayDeque<Integer> q=new ArrayDeque<>();
			for(int i=0;i<N;i++) q.offer(Integer.parseInt(st.nextToken()));
			int flag = 0;
			for(int i=0;i<method.length();i++) {
				char target = method.charAt(i);
				if(target == 'R') {
					flag++;
				}else {
					if(q.isEmpty()) {
						sb.append("error\n");
						continue end;
					}else if(flag%2==0) {
						q.pollFirst();
					}else {
						q.pollLast();
					}
				}
			}
			if(flag%2==0) {
				sb.append("[");
				while(!q.isEmpty()) {
					int cur = q.poll();
					sb.append(cur);
					if(!q.isEmpty()) sb.append(",");
				}
				sb.append("]\n");
			}
			else {
				sb.append("[");
				while(!q.isEmpty()) {
					int cur = q.pollLast();
					sb.append(cur);
					if(!q.isEmpty()) sb.append(",");
				}
				sb.append("]\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
}
