import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[26];
		for(int i=0;i<N;i++) {
			String word = br.readLine();
			for(int j=0;j<word.length();j++) count[word.charAt(j)-'A'] += Math.pow(10, word.length()-j-1); 
		}
		
		Arrays.sort(count);
		
		int ans = 0;
		for(int i=25;i>0;i--) {
			if(count[i]==0) break;
			ans += count[i] * (i-16);
		}
		
		System.out.println(ans);
		br.close();
	}
}
