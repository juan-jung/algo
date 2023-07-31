

import java.util.Scanner;

public class Main {
	static int min;
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		min=Integer.MAX_VALUE;
		makeone(n,0);
		System.out.println(min);
	}
	static void makeone(int n, int cnt) {
		if(cnt > min) return;
		if(n==1) {
			min=Math.min(min, cnt);
			return;
		}
		if(n%3==0) makeone(n/3,cnt+1);
		if(n%2==0) makeone(n/2,cnt+1);
		makeone(n-1,cnt+1);
	}
}
