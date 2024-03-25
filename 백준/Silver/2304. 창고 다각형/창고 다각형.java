import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] pillar = new int[N][2];
        int highest = 0;
        ArrayList<Integer> highests = new ArrayList<>();
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            pillar[i][0] = L;
            pillar[i][1] = H;
            if(H > highest) {
                highest = H;
                highests = new ArrayList<>();
                highests.add(L);
            }
            else if(H == highest) {
                highests.add(L);
            }
        }

        Collections.sort(highests);
        Arrays.sort(pillar, (o1,o2)->Integer.compare(o1[0],o2[0]));

        int area = 0;
        if(highests.size()==1) area += highest;
        else {
            area += highest * (highests.get(highests.size()-1) - highests.get(0) + 1);
        }

        //왼쪽
        int cur_h = 0;
        int cur_x = 0;
        for(int i=0;i<N;i++) {
            if(cur_x == highests.get(0)) break;
            if(pillar[i][1] > cur_h) {
                area += cur_h * (pillar[i][0] - cur_x);
                cur_h = pillar[i][1];
                cur_x = pillar[i][0];
            }
        }

        //오른쪽
        cur_h = 0;
        cur_x = 1000;
        for(int i=N-1;i>=0;i--) {
            if(cur_x == highests.get(highests.size()-1)) break;
            if(pillar[i][1] > cur_h) {
                area += cur_h * (cur_x - pillar[i][0]);
                cur_h = pillar[i][1];
                cur_x = pillar[i][0];
            }
        }

        System.out.println(area);
        br.close();
    }
}
