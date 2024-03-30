import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        int[] rain = new int[W];
        for(int i=0;i<W;i++) rain[i] = Integer.parseInt(st.nextToken());

        int ans = 0;

        for(int i=1;i<=H;i++) {
            int cnt = 0;
            boolean wall = false;
            for(int j=0;j<W;j++) {
                if(rain[j] >= i) wall = true;
                if(!wall) continue;;
                if(rain[j] < i) cnt++;
                else {
                    ans += cnt;
                    cnt = 0;
                }
            }
        }

        System.out.println(ans);
        br.close();
    }
}
