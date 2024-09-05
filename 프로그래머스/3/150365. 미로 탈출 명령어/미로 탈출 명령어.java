import java.util.*;

class Solution {
    int[] dx = {1,0,0,-1}, dy = {0,-1,1,0};
    char[] dirSymbol = {'d','l','r','u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        int dist = getDist(x,y,r,c);
        if(k < dist || (k-dist)%2==1) return "impossible";
        
        while(k>0) {
            dist = getDist(x,y,r,c);
            int dir = 0;
            if(k>dist) {
                for(int d=0;d<4;d++) {
                    int nx = x+dx[d];
                    int ny = y+dy[d];
                    if(nx<1||nx>n||ny<1||ny>m) continue;
                    dir =  d;
                    break;
                }
            }
            else {
                int minDist = Integer.MAX_VALUE;
                for(int d=0;d<4;d++) {
                    int nx = x+dx[d];
                    int ny = y+dy[d];
                    if(nx<1||nx>n||ny<1||ny>m) continue;
                    int tempDist = getDist(nx,ny,r,c);
                    if(tempDist < minDist) {
                        minDist = tempDist;
                        dir = d;
                    }
                }
                
            }
            answer += dirSymbol[dir];
            x += dx[dir];
            y += dy[dir];
            k--;
        }
        
        return answer;
    }
    
    int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}

/*
x,y ->  r,c 거리 k
사전순 dlru

탈출못하는 경우
- k가 최단거리 보다 작을 경우
- k-최단거리가 홀수 일경우

갈수있는만큼(이동하려는 칸에서 종점까지의 최단거리 고려) dlru순으로 
k-이동거리 = 현재위치에서 종점까지의 최단거리 일때부터 최단거리가 줄어드는 방향으로 이동

*/