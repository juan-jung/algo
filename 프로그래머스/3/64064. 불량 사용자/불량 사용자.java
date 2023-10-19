import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        ArrayList<Integer>[] g = new ArrayList[banned_id.length];
        
        for(int i=0;i<banned_id.length;i++) {
            g[i] = new ArrayList<Integer>();
            for(int j=0;j<user_id.length;j++) {
                if(check(user_id[j],banned_id[i])) g[i].add(j);
            }
        }

        HashSet<String> set = new HashSet<String>();
        boolean[] v = new boolean[user_id.length];
        
        dfs(0,g,banned_id.length,v,set);
        
        return set.size();
    }

    public void dfs(int cnt, ArrayList<Integer>[] g, int limit, boolean[] v, HashSet<String> set) {
        if(cnt == limit) {
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<v.length;i++) {
                if(v[i]) sb.append("1");
                else sb.append("0");
            }
            set.add(sb.toString());
            return;
        }
        for(int i=0;i<g[cnt].size();i++) {
            if(v[g[cnt].get(i)]) continue;
            v[g[cnt].get(i)] = true;
            dfs(cnt+1, g, limit,v,set);
            v[g[cnt].get(i)] =false;
        }
        
    } 
    
    public boolean check(String user, String banned) {
        if(user.length() != banned.length()) return false;
        for(int i=0;i<user.length();i++) {
            if(banned.charAt(i) == '*') continue;
            else if(banned.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
}