

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		sc.close();
		long size = (long) Math.pow(2, n);
		
		cut(r,c,size,0);
	}
	static void cut(int x, int y, long size, long sum) {
		if(size==2) {
			int nx=x%2;
			int ny=y%2;
			if(nx==0 && ny==0) System.out.println(sum);
			else if(nx==0 && ny==1) System.out.println(sum+1);
			else if(nx==1 && ny==0) System.out.println(sum+2);
			else if(nx==1 && ny==1) System.out.println(sum+3);
			return;
		}
		int half = (int)size/2;
		if(x<half && y<half) {
			cut(x,y,half,sum);
		}
		else if(x<half && y>=half) {
			cut(x,y-half,half,sum+size*size/4);
		}
		else if(x>=half && y<half) {
			cut(x-half,y,half,sum+size*size/2);
		}
		else if(x>=half && y>=half) {
			cut(x-half,y-half,half,sum+size*size*3/4);
		}
	}
	/*
	 
	 */
}
