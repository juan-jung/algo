import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char[] T=br.readLine().toCharArray();
		char[] P=br.readLine().toCharArray();
		int tLenght=T.length, pLength=P.length;
		int[] pi=new int[pLength];
		for(int i=1,j=0;i<pLength;i++) {
			while(j>0 && P[i]!=P[j]) j=pi[j-1];
			if(P[i]==P[j]) pi[i]=++j;
			else pi[i]=0;
		}
		
		int cnt=0;
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0,j=0;i<tLenght;i++) {
			while(j>0&&T[i]!=P[j]) j=pi[j-1];
			if(T[i]==P[j]) {
				if(j==pLength-1) {
					cnt++;
					list.add(i-j+1);
					j=pi[j];
				}else {
					j++;
				}
			}
		}
		System.out.println(cnt);
		for(int a:list) System.out.print(a+" ");
	}
}
