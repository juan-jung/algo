

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		HashMap<String, Boolean> map = new HashMap<>();
		for(int i=0;i<N;i++) map.put(br.readLine(), true);
//		PriorityQueue<String> pq=new PriorityQueue<>((o1,o2)->{
//			int idx =0;
//			while(idx < o1.length() && idx < o2.length()) {
//				if(o1.charAt(idx) < o2.charAt(idx)) return -1;
//				else if(o1.charAt(idx) > o2.charAt(idx)) return 1; 
//			}
//			return o1.length() < o2.length() ? -1 : 1;
//		});
		ArrayList<String> al = new ArrayList<>();
		for(int i=0;i<M;i++) {
			String look = br.readLine();
			if(map.get(look) != null) al.add(look);
		}
		System.out.println(al.size());
		Collections.sort(al);
		for(String s : al) System.out.println(s);
	}
}
