import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if(N==number) return 1;
        
        int answer = 0;
        
        Set<Integer>[] dp = new HashSet[9];
        for(int i=0;i<9;i++) {
            dp[i] = new HashSet<Integer>();
        }
        
        int temp = N;
        dp[1].add(temp);
        temp*=10;
        
        for(int i=2;i<9;i++) {
            //연산자없는거 넣어주고
            temp+=N;
            dp[i].add(temp);
            temp*=10;
            
            //사칙연산
            for(int j=1;j<i;j++) {
                for(int a : dp[i-j]) {
                    for(int b : dp[j]) {
                        //덧셈
                        dp[i].add(a+b);
                        
                        //뺄셈
                        dp[i].add(a-b);
                        // dp[i].add(b-a);
                        //나눗셈
                        if(b!=0) dp[i].add(a/b);
                        // if(a!=0) dp[i].add(b/a);
                        //곱셈
                        dp[i].add(a*b);
                        // dp[i].add(b*a);
                    }
                }
            }
            
            if(dp[i].contains(number)) return i;
        }
        
        // for(int i=1;i<9;i++) {
        //     System.out.println(dp[i]);
        // }
        return -1;
    }
}