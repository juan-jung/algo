import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i=0;i<s.length;i++) {
            if(isNum(s[i])) stack.push(1);
            else if(s[i] == '(') {
                stack.pop();
                stack.push(s[i-1]-'0');
                stack.push(-1);
            }
            else {
                int cnt = 0;
                while(true) {
                    int num = stack.pop();
                    if(num==-1) break;
                    cnt+=num;
                }
                stack.push(stack.pop()*cnt);
            }
        }
        int ans = 0;
        while(!stack.isEmpty()) {
            ans += stack.pop();
        }
        System.out.println(ans);
        br.close();

    }

    static boolean isNum(char c) {
        if(c=='(' || c==')') return false;
        return true;
    }
}
