import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] cnt = new int[H+1];
        for(int i=0;i<N;i++) {
            int ob = Integer.parseInt(br.readLine());
            if(i%2==0) {
                cnt[1]++;
                cnt[ob+1]--;
            }
            else {
                cnt[H-ob+1]++;
            }
        }

        for(int i=1;i<=H;i++) {
            cnt[i] += cnt[i-1];
        }

        int min = Integer.MAX_VALUE;
        int min_cnt = 0;
        for(int i=1;i<=H;i++) {
            if(cnt[i]==min) min_cnt++;
            else if(cnt[i] < min) {
                min = cnt[i];
                min_cnt = 1;
            }
        }

        System.out.println(min + " " + min_cnt);
        br.close();
    }
}
