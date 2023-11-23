import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        boolean[][] v = new boolean[land.length][land[0].length];
        int flag = 2;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<land.length;i++) {
            for(int j=0;j<land[i].length;j++) {
                if(land[i][j] != 1) continue;
                int cnt = 1;
                v[i][j] = true;
                ArrayDeque<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i,j});
                land[i][j] = flag;
                while(!q.isEmpty()) {
                    int[] cur = q.poll();
                    for(int d=0;d<4;d++) {
                        int nx = cur[0] + dx[d];
                        int ny = cur[1] + dy[d];
                        if(nx<0||nx>land.length-1||ny<0||ny>land[0].length-1||v[nx][ny]||land[nx][ny]!=1) continue;
                        v[nx][ny] = true;
                        land[nx][ny] = flag;
                        cnt++;
                        q.offer(new int[]{nx,ny});
                    }
                }
                map.put(flag, cnt);
                flag++;
            }
        }
        
        for(int i=0;i<land[0].length;i++) {
            Set<Integer> set = new HashSet<>();
            for(int j=0;j<land.length;j++) {
                if(land[j][i] > 1) set.add(land[j][i]);
            }
            int sum = 0;
            for(Integer a : set) sum += map.get(a);
            answer = Math.max(answer, sum);
        }
        return answer;
    }
}