

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<int[]> count = new ArrayList<>();
		for(int i=0;i<26;i++) count.add(new int[] {i,0});
		String[] words = new String[N];
		for(int i=0;i<N;i++) {
			words[i] = br.readLine();
			for(int j=0;j<words[i].length();j++) {
				char c = words[i].charAt(j);
				count.get(c-'A')[1] += Math.pow(10,words[i].length() - j - 1); 
			}
		}
		
		Collections.sort(count, (o1,o2)->Integer.compare(o2[1],o1[1]));
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int v = 9;
		for(int[] a : count) {
			if(a[1] == 0) break;
			map.put(a[0], v--);
		}
		
		int ans = 0;
		for(int i=0;i<N;i++) {
			String sum = "";
			for(int j=0;j<words[i].length();j++) {
				sum += map.get(words[i].charAt(j)-'A');
			}
			ans += Integer.parseInt(sum);
		}
		
		
		System.out.println(ans);
		br.close();
	}
}
