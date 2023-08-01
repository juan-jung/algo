

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Title> titleList;
	static StringBuilder sb;
	static class Title {
		int powerLimit;
		String title;
		public Title(String title,int powerLimit) {
			this.powerLimit = powerLimit;
			this.title = title;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		titleList = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			titleList.add(new Title(st.nextToken(), Integer.parseInt(st.nextToken())));
		}

		for(int i=0;i<M;i++) {
			int power = Integer.parseInt(br.readLine());
			findTitle(power, 0, N-1);
		}
		System.out.println(sb);
	}
	static void findTitle(int power, int start, int end) {
		if(start == end) {
			sb.append(titleList.get(start).title+"\n");
			return;
		}
		int mid = (start + end) /2;
		if(titleList.get(mid).powerLimit >= power) {
			findTitle(power, start, mid);
		}
		else {
			findTitle(power,mid+1,end);
		}
	}
}
