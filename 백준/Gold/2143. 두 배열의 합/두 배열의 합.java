import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[]  A =  new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++) A[i] = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[] B  = new int[m];
        st = new StringTokenizer(br.readLine(),  " ");
        for(int i=0;i<m;i++) B[i]  = Integer.parseInt(st.nextToken());

       //1,000,000 * 1,000 = 1,000,000,000

        //A,B 배열에서 각각 나올수 있는 수를 다 구한다. 2*1,000*1,000 = 2,000,000
        Map<Integer, Integer>  aMap   = new HashMap<>();
        Map<Integer, Integer>  bMap = new HashMap<>();
        getSub(n,A,aMap);
        getSub(m,B,bMap);

        //A,B의 경우의 수를 더한 값이 T가 되는 경우의 수를 찾기 / brute force -> 4,000,000,000,000
        //System.out.println(Math.log(2000000)/Math.log(2)); ===  21
        long ans =   0;
        for(int key : aMap.keySet()) {
            int toBeBKey = T -  key;
            int bValue   = bMap.getOrDefault(toBeBKey,0);
            ans += (long)aMap.get(key) * (long)bValue;
        }

        System.out.println(ans);

    }
    
    static void getSub(int len, int[] arr, Map<Integer, Integer> map) {
        for(int i=0;i<len;i++)  {
            int  sum = 0;
            for(int  j=i;j<len;j++)  {
                sum  += arr[j];
                int value  = map.getOrDefault(sum,0);
                map.put(sum, value+1);
            }
        }
    }
}
