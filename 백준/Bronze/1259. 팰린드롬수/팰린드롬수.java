

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		end:while(true) {
			String number = br.readLine();
			if(number.equals("0")) break;
			if(number.length()%2 == 0) {
				int left_idx=0;
				int right_idx = number.length()-1;
				while(left_idx < right_idx) {
					if(number.charAt(left_idx) != number.charAt(right_idx)) {
						sb.append("no\n");
						continue end;
					}
					left_idx++;
					right_idx--;
				}
				sb.append("yes\n");
			}
			else {
				int left_idx=0;
				int right_idx = number.length()-1;
				while(left_idx != right_idx) {
					if(number.charAt(left_idx) != number.charAt(right_idx)) {
						sb.append("no\n");
						continue end;
					}
					right_idx--;
					left_idx++;
				}
				sb.append("yes\n");
			}
		}
		System.out.println(sb);
	}
}
