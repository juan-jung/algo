import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int[][] map = new int[150][150];
        for(int i=0;i<rectangle.length;i++) {
            for(int j=rectangle[i][0]*2;j<=rectangle[i][2]*2;j++) {
                for(int k=rectangle[i][1]*2;k<=rectangle[i][3]*2;k++) {
                    map[j][k] = 1;
                }
            }
        }
        
        map[characterX*2][characterY*2] = 2;
        map[itemX*2][itemY*2] = 3;
        
        boolean[][] v = new boolean[150][150];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2],o2[2]));//x y d
        pq.offer(new int[]{characterX*2, characterY*2, 0});
        v[characterX*2][characterY*2] = true;
        int[] dx = {-1,1,0,0,-1,-1,1,1};
        int[] dy = {0,0,-1,1,-1,1,-1,1};
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(map[cur[0]][cur[1]] == 3) {
                answer =  cur[2]/2;
                break;
            }
            for(int d=0;d<4;d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(map[nx][ny]==0||v[nx][ny]) continue;
                for(int d2=0;d2<8;d2++) {
                    int nnx = nx + dx[d2];
                    int nny = ny + dy[d2];
                    if(map[nnx][nny] == 0) {
                        pq.offer(new int[]{nx,ny,cur[2]+1});
                        v[nx][ny] = true;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}