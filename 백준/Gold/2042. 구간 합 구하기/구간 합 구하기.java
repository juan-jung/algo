

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken()); // 수의 개수
		int M=Integer.parseInt(st.nextToken()); //수의 변경이 일어나는 횟수
		int K=Integer.parseInt(st.nextToken()); //구간합을 구하는 횟수
		long[] arr=new long[N+1];
		long[] tree=new long[N+1];
		for(int i=1;i<=N;i++) {
			arr[i]=Long.parseLong(br.readLine());
			update(tree,i,arr[i]);
		}
		
		for(int i=0;i<M+K;i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			long c=Long.parseLong(st.nextToken());
			if(a==1) {
				//update b번째 수를 c로
				long dif=c-arr[b];
				arr[b]=c;
				update(tree,b,dif);
			}
			else if(a==2) {
				//sum b번쨰 수부터 c번째 수까지 구간합 출력
				System.out.println(sum(tree,b,(int)c));
			}
		}
		br.close();
	}
	static void update(long[] tree, int i, long dif) {
		while(i <= N) {
			tree[i] +=dif;
			i+=(i&-i);
		}
	}
	static long sum(long[] tree, int i) {
		long ans=0;
		while(i>0) {
			ans+=tree[i];
			i-=(i&-i);
		}
		return ans;
	}
	static long sum(long[] tree,int i, int j) {
		return sum(tree,j)-sum(tree,i-1);
	}
}
