import java.util.*;
class Solution {
    public int solution(String numbers) {
        int answer = 0;
        
        boolean[] isPrime = new boolean[10000000];  //false 소수 true 소수x
        
        isPrime[0] = true;
        isPrime[1] = true;
    
        for(int i=2;i<Math.sqrt(10000000);i++) {
            if(!isPrime[i]) {
                for(int j=i*i;j<10000000;j+=i) {
                    isPrime[j] = true;
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i=1;i<=numbers.length();i++) {
            boolean[] v = new boolean[numbers.length()];
            perm(0,v,0,set,i,numbers,isPrime);
        }
        
        answer = set.size();
        
        return answer;
    }
    
    void perm(int cnt, boolean[] v, int number, Set<Integer> set, int limit, String numbers, boolean[] isPrime) {
        if(cnt==limit) {
            if(!isPrime[number]) {
                set.add(number);
            }
            return;
        }
        
        for(int i=0;i<numbers.length();i++) {
            if(v[i]) continue;
            v[i]=true;
            perm(cnt+1,v,number + (numbers.charAt(i)-'0') * (int)Math.pow(10,(limit-cnt-1)), set, limit, numbers, isPrime);
            v[i]=false;
        }
    }
}

/*
10,

*/
