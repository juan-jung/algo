import java.util.*;
class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    public int solution(int[][] land, int height) {
        int answer = 0;
        int N = land.length;
        int[][] map = new int[N][N];
        int idx = 1;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j]!=0) continue;
                color(i, j, map, land, height, idx, N);
                idx++;
            }
        }
       
        
        //1부터 idx-1까지의 그래프그리기
        Map<Integer, Integer>[] g1 = new HashMap[idx];
        for(int i=0;i<idx;i++) g1[i] = new HashMap<Integer, Integer>();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                for(int d=0;d<4;d++) {
                    int nx = i +dx[d];
                    int ny = j + dy[d];
                    if(nx<0||nx>N-1||ny<0||ny>N-1||map[i][j]==map[nx][ny]) continue;
                    int prev = g1[map[i][j]].getOrDefault(map[nx][ny],Integer.MAX_VALUE);
                    int diff = Math.abs(land[i][j] - land[nx][ny]);
                    if(prev > diff) {
                        g1[map[i][j]].put(map[nx][ny],diff);
                        g1[map[nx][ny]].put(map[i][j], diff);
                    }
                    
                    // if(g[map[i][j]][map[nx][ny]]==0 || diff < g[map[i][j]][map[nx][ny]]) {
                    //     g[map[i][j]][map[nx][ny]] = diff;
                    //     g[map[nx][ny]][map[i][j]] = diff;
                    // }
                }
            }
        }
        
     
        int result = 0;
        int cnt = 0;
        boolean[] v = new boolean[idx];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        int[] dist = new int[idx];
        for(int i=0;i<idx;i++) dist[i] = Integer.MAX_VALUE;
        dist[1] = 0;
        pq.offer(new int[]{1,0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(v[cur[0]]) continue;
            
            result += cur[1];
            v[cur[0]] = true;
            if(idx-1 == ++cnt) break;
            
            // for(int i=1;i<idx;i++) {
            //     if(g[cur[0]][i]==0) continue;
            //     if(dist[i] > g[cur[0]][i]) {
            //         dist[i] = g[cur[0]][i];
            //         pq.offer(new int[]{i,g[cur[0]][i]});
            //     }
            // }
            
            for(int target : g1[cur[0]].keySet()) {
                if(dist[target] > g1[cur[0]].get(target)) {
                    dist[target] = g1[cur[0]].get(target);
                    pq.offer(new int[]{target, dist[target]});
                }
            }
        }
        return result;
    }
    void color(int i, int j, int[][] map, int[][] land, int height, int idx, int N) {
        map[i][j] = idx;
        for(int d=0;d<4;d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            //범위를 벗어나거나 0이 아니거나 height를 초과하면 continue
            if(nx<0||nx>N-1||ny<0||ny>N-1||map[nx][ny]!=0||(Math.abs(land[i][j]-land[nx][ny]) > height)) continue;
            color(nx,ny,map,land,height,idx,N);
        }
    }
}

/*
사다리 없이 이동할 수 있는 구역 색칠

모든 구간을 최소신장트리로 엮는다
//맞닿아 있는 칸끼리 계산


*/