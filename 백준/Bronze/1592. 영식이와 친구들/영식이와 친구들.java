import java.util.*;
public class Main {public static void main(String[] args) {Scanner s = new Scanner(System.in);
      int a = s.nextInt(), b=s.nextInt(),L =s.nextInt(),g=0;
      for(int i=1;i<=L; i++) {if(a%i==0 && L%i==0) g=i;}
         System.out.println((a/g)*(b-1));}}