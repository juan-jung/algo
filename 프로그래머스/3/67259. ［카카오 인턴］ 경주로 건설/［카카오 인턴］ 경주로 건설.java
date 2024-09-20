import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        
        int[][][] cost = new int[4][board.length][board[0].length];
        for(int i=0;i<4;i++) {
            for(int j=0;j<cost[i].length;j++) Arrays.fill(cost[i][j],Integer.MAX_VALUE);    
            cost[i][0][0] = 0;
        }
    
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2],o2[2])); //x y cost dir
        if(board[0][1]==0) {
            pq.offer(new int[]{0,1,100,2});
            cost[2][0][1] = 100;
        }
        if(board[1][0]==0) {
            pq.offer(new int[]{1,0,100,3});
            cost[3][1][0] = 100;
        }
        
        int[] dx = {-1,0,0,1}, dy = {0,-1,1,0}; //상 좌 우 하
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();            
            
            if(cur[0]==board.length-1 && cur[1]==board[0].length-1) {
                answer = cost[cur[3]][board.length-1][board[0].length-1];
                break;
            }
            
            for(int d=0;d<4;d++) {
                int nx = cur[0]+dx[d];
                int ny = cur[1]+dy[d];
                if(nx<0||nx>=board.length||ny<0||ny>=board[0].length||board[nx][ny]==1) continue;
                int move_cost = 0;
                if(d+cur[3]==3 || d==cur[3]) move_cost = 100;
                else move_cost = 600;
                if(cur[2] + move_cost > cost[d][nx][ny]) continue;
                cost[d][nx][ny] = cur[2]+move_cost;
                pq.offer(new int[]{nx,ny,cost[d][nx][ny],d});           
            }
        }
        
        // for(int[][] a: cost) System.out.println(Arrays.deepToString(a));
        return answer;
    }
}