import java.util.*;

class Solution {
    static int answer;
    public int solution(int n) {
        answer = 0;

        // int[] d = new int[n+1];
        // d[1] = 1;
        // for(int i=2;i<=n;i++) {
        //     d[i]=1;
        //     int cnt = 1;
        //     for(int j=i-1;j>=1;j--) {
        //         d[i] += d[cnt++]*j;
        //     }
        // }

        dfs(0,0,n*2);
        
        return answer;
    }
    
    public void dfs(int start, int cnt, int n) {
        if(cnt == n) {
            // System.out.println(start);
            if(start==0) answer++;
            return;
        }
        dfs(start + 1, cnt+1, n);
        if(start > 0) dfs(start-1, cnt+1, n);
    }
}
//2^10 
//1024 2048 5000 10000 20000
//1 : () --> 1
//2 : ()() / (()) 1*1번째 --> 1+1=2
//3 : ()()() / ()() 2*1번째 / () 1*2번째 --> 1+2+2 = 5
//4 : ()()()() / ()()() 3*1번째 / ()() 2*2번째 / () 1*3번째 -> 1+3+4+15 = 13
//5 : ()()()()() / ()()()() 4*1번째 / ()()() 3*2번째 / ()() 2*3번째 / ()1 *4번째 
// -> 1+4+6+10+23=44
