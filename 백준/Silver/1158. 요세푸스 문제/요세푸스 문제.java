import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		
		ArrayList<Integer> al = new ArrayList<>();
		for(int i=1;i<=n;i++) al.add(i);
		int n2 = n;
		int cnt=0;
		int index = k-1; //2
		
		System.out.print("<");
		while(al.size()!=0) {
			if(cnt>0 && cnt!=n2) System.out.print(", ");
			System.out.print(al.get(index));
			al.remove(index);
			index += (k-1);
			if(index > --n-1 && al.size()!=0) index%=(n);
			cnt++;
			
		}   
		System.out.println(">");
		//7 6 5 4 3 2 1
		//
		//1 2 3 4 5 6 7
		/*          
		  1 2 4 5 6 7
		 */
	}
}