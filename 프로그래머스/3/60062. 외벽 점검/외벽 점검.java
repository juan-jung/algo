import java.util.*;
class Solution {
    static boolean flag;
    static int answer;
    public int solution(int n, int[] weak, int[] dist) {
        answer = 0;
        flag = false;
        for(int i=1;i<=dist.length;i++) {
            if(flag) break;
            int[] selected = new int[i];
            boolean[] v = new boolean[dist.length];
            perm(0,v,selected,i,dist,weak,n);
        }
        return flag ? answer : -1;
    }
    
    void check(int[] selected, int[] weak, int n, int limit) {
        for(int i=0;i<weak.length;i++) {
            int weak_idx = i;
            int selected_idx = 0;
            int chk_cnt = 0;
            int start = weak[weak_idx];
            int end = start + selected[selected_idx];
            while(true) {    
                if((weak[weak_idx] >= start && weak[weak_idx] <= end) || (end > n-1 && weak[weak_idx] <= end%n)) {
                    chk_cnt++;
                    if(chk_cnt>=weak.length) {
                        answer = limit;
                        flag = true;
                        return;
                    }
                    weak_idx = (weak_idx+1)%weak.length;
                }
                else {
                    selected_idx++;
                    if(selected_idx == selected.length) break;
                    start = weak[weak_idx];
                    end = start + selected[selected_idx];
                }
            }
            
        }
    }
    
    void perm(int cnt, boolean[] v, int[] selected, int limit, int[] dist, int[] weak, int n) {
        if(flag) return;
        if(cnt==limit) {
            check(selected,weak,n,limit);
            return;
        }
        for(int i=0;i<limit;i++) {
            if(v[i]) continue;
            v[i] = true;
            selected[cnt] = dist[dist.length-1-i];
            perm(cnt+1,v,selected,limit,dist,weak,n);
            v[i] = false;
        }
    }
}