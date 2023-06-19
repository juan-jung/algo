

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		PriorityQueue<String> pq=new PriorityQueue<>((o1,o2)->{
			if(o1.length() == o2.length()) {
				for(int i=0;i<o1.length();i++) {
					if(o1.charAt(i) > o2.charAt(i)) return 1;
					else if(o1.charAt(i) < o2.charAt(i)) return -1;
				}
			}
			return o1.length()-o2.length();});				
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<N;i++) {
			String word  = br.readLine();
			if(map.get(word) != null) continue;
			pq.offer(word);
			map.put(word, 1);
		}
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
}
