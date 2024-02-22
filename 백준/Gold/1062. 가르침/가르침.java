import java.io.*;
import java.util.*;

public class Main {
	static int N,K,ans;
	static boolean[] taught;
	static String[] words;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(K < 5) System.out.println(0);
		else {
			taught = new boolean[26];
			taught['a'-97] = true;
			taught['n'-97] = true;
			taught['t'-97] = true;
			taught['i'-97] = true;
			taught['c'-97] = true;
			
			words = new String[N];
			for(int i=0;i<N;i++) {
				String word = br.readLine();
				String temp = "";
				for(int j=4;j<word.length()-4;j++) {
					if(taught[word.charAt(j)-97]) continue;
					temp+=word.charAt(j);
				}
				words[i] = temp;
			}
			
			comb(5,0);
			System.out.println(ans);
		}
		br.close();
	}
	
	static void comb(int cnt, int start) {
		if(cnt==K) {
			int count = 0;
			end:for(String word : words) {
				for(int i=0;i<word.length();i++) {
					if(!taught[word.charAt(i)-97]) continue end;
				}
				count++;
			}
			ans = Math.max(ans, count);
			return;
		}
		for(int i=start;i<26;i++) {
			if(taught[i]) continue;
			taught[i] = true;
			comb(cnt+1,i+1);
			taught[i] = false;
		}
	}
}

