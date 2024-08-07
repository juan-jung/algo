import java.util.*;
import java.io.*;

public class Main {
    static boolean flag;
    static int cnt1,cnt2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] g = new ArrayList[N];
        for(int i=0;i<N;i++) g[i] = new ArrayList<Integer>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;
            g[n1].add(n2);
            g[n2].add(n1);
        }

        int[] v = new int[N];
        v[0] = 1;
        flag = true;
        cnt1 = 1;
        cnt2 = 0;
        dfs(0,v,g);

        if(flag) System.out.println(cnt1*cnt2*2);
        else System.out.println(0);
    }

    static void dfs(int node, int[] v, ArrayList<Integer>[] g) {
        if(!flag) return;
        for(int next : g[node]) {
            if(v[next]==0) {
                if(v[node]==1) {
                    v[next] = 2;
                    cnt2++;
                }
                else if(v[node]==2) {
                    v[next] = 1;
                    cnt1++;
                }
                dfs(next, v, g);
            } else {
                if(v[node]==v[next]) {
                    flag = false;
                    return;
                }
            }
        }
    }
}
