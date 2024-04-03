import java.io.*;
import java.util.*;

public class Main {
    /**
     * 시간제한 : 1초, 메모리제한 256MB
     *
     * 문제
     * n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다.
     * 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력하여라.
     * 항상 시작점에서 도착점으로의 경로가 존재한다.
     *
     * 입력
     * 첫째 줄에 도시의 개수 n(1≤n≤1,000)이 주어지고 둘째 줄에는 버스의 개수 m(1≤m≤100,000)이 주어진다.
     * 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
     * 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
     * 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
     * 그리고 m+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
     *
     * 출력
     * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
     * 둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 출발 도시와 도착 도시도 포함한다.
     * 셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //도시 개수
        int m = Integer.parseInt(br.readLine()); //버스 개수
        List<int[]>[] g = new ArrayList[n];
        int[] dist = new int[n];
        final int INF = Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            g[i] = new ArrayList<int[]>();
            dist[i] = INF;
        }
        StringTokenizer st = null;
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            g[from].add(new int[]{to, c});
        }
        st = new StringTokenizer(br.readLine()," ");
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;

        boolean[] v = new boolean[n];
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[1], o2[1])));
        pq.offer(new int[]{start, 0});
        int[] path = new int[n];
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int city = cur[0];

            if(v[city]) continue;
            if(city == end) break;
            v[city] = true;

            for(int[] target : g[city]) {
                if(v[target[0]] || dist[target[0]] < dist[city] + target[1]) continue;;
                dist[target[0]] = dist[city] + target[1];
                pq.offer(new int[]{target[0], dist[target[0]]});
                path[target[0]] = city;
            }

        }

        ArrayList<Integer> pathList = new ArrayList<>();
        int idx = end;
        while(idx!=start) {
           pathList.add(idx+1);
           idx = path[idx];
        }
        pathList.add(start+1);
        Collections.reverse(pathList);

        StringBuilder ans = new StringBuilder();
        ans.append(dist[end]+"\n");
        ans.append(pathList.size() + "\n");
        for(int city : pathList) ans.append(city + " ");

        System.out.println(ans);
        br.close();
    }
}
/*
예제 입력 1
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5

예제 출력 1
4
3
1 3 5


3
3
1 2 10
1 3 1
3 2 1
1 2
 */
