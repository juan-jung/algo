import java.util.*;

class Solution {

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        int[][][] map = new int[n+1][n+1][2];
        
        for(int i=0;i<build_frame.length;i++) {
            int x = build_frame[i][0];
            int y = n-build_frame[i][1];
            int cons = build_frame[i][2];
            int op = build_frame[i][3];
            if(cons == 0) {
                if(op == 0) deletePillar(x,y,map,n);
                else if(op == 1) constructPillar(x,y,map,n);
            }
            else if(cons == 1) {
                if(op == 0) deleteBeam(x,y,map,n);
                else if(op == 1) constructBeam(x,y,map,n);
            }
        }
        List<int[]> list = new ArrayList<>();
        
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=n;j++) {
                for(int k=0;k<2;k++) {
                    if(map[i][j][k]==1) {
                        list.add(new int[]{i,n-j,k});
                    }
                }
            }
        }
        
        Collections.sort(list, (o1,o2)-> {
            if(o1[0] == o2[0]) {
                if(o1[1] == o2[1]) return o1[2] - o2[2];
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        
        answer = new int[list.size()][3];
        for(int i=0;i<list.size();i++) {
            int[] cur = list.get(i);
            answer[i][0] = cur[0];
            answer[i][1] = cur[1];
            answer[i][2] = cur[2];
        }
        return answer;
    }
    
    void deletePillar(int x, int y, int[][][] map, int n) {
        boolean safe = false;
        
        //x, y-1 위치의 보 안전한지 확인
        if(y>=0 && map[x][y-1][1] != 0) {
          
            //x+1, y 위치에 기둥이 있는지 확인
            //x-1, y-1 and x+1,y-1 양쪽 보 있는지 확인
            if(!(x<n && map[x+1][y][0]==1) && !(x-1>=0 && y-1>=0 && x<n && map[x-1][y-1][1] != 0 && map[x+1][y-1][1]!=0)) return;
        }
        
        //x-1, y-1 위치의 보 안전한지 확인
        if(x-1>=0&&y-1>=0&&map[x-1][y-1][1]!=0) {
            
            //x-1, y 에 기둥이 있는지 확인
            //x-2,y-1 and x,y-1 보 있는지 확인
            if(!(x-1>=0&&map[x-1][y][0]!=0) && !(x-2>=0&&y-1>=0&&map[x-2][y-1][1]!=0&&map[x][y-1][1]!=0)) return;
        }
        
        //x, y-1 위치의 기둥 안전한지 확인
        if(y-1>=0&&map[x][y-1][0]!=0) {
            
            //x-1, y-1 보 있는지 확인
            //x,y-1 보 있는지 확인
            if(!(x-1>=0&&y-1>=0&&map[x-1][y-1][1]!=0) && !(y-1>=0&&map[x][y-1][1]!=0)) return;
        }
        
        //삭제
        map[x][y][0] = 0;
    }
    
    void constructPillar(int x, int y, int[][][] map, int n) {
        if(y!=n) {
            //x,y+1에 기둥이 있거나 x,y에 보거 있거나 x-1,y에 보가 있어야함
            if(!(map[x][y+1][0]==1) && !(map[x][y][1]==1) && !(x-1>=0&&map[x-1][y][1]==1)) return;
        }
        map[x][y][0] = 1;
    }
    
    void deleteBeam(int x, int y, int[][][] map, int n) {
        //x,y 기둥 안전한지 확인
        if(map[x][y][0] == 1) {
            //x-1,y에 보가 있거나 x,y+1에 기둥이 있어야함
            if(!(x-1>=0&&map[x-1][y][1]==1) && !(y<n&&map[x][y+1][0]==1)) return;
        }
        //x+1,y 기둥 안전한지 확인
        if(x<n&&map[x+1][y][0]==1) {
            //x+1,y+1에 기둥이 있거나 x+1,y에 보가 있으면 됨
            if(!(x<n&&map[x+1][y+1][0]==1) && !(x<n&&map[x+1][y][1]==1)) return;
        }
        //x-1,y 보 안전한지 확인
        if(x-1>=0&&map[x-1][y][1] == 1) {
            //x-1,y+1에 기둥이 있거나 x,y+1에 기둥이 있으면 됨
            if(!(x-1>=0&&y<n&&map[x-1][y+1][0]==1) && !(y<n&&map[x][y+1][0]==1)) return;
        }
        
        //x+1,y 보 안전한지 확인
        if(x<n&&map[x+1][y][1]==1) {
            //x+1,y+1 기둥이 있거나 x+2,y+1 기둥이 있으면 됨
            if(!(x<n&&y<n&&map[x+1][y+1][0]==1) && !(x+1<n&&y<n&&map[x+2][y+1][0]==1)) return;
        }
        
        map[x][y][1] = 0;
    }
    
    void constructBeam(int x, int y, int[][][] map, int n) {
        //x,y+1에 기둥이 있거나 x+1,y+1에 기둥이 있거나 x-1,y와 x+1,y 양쪽에 보가 존재해야함
        if(!(y<n&&map[x][y+1][0]==1) && !(x<n&&y<n&&map[x+1][y+1][0]==1) && !(x-1>=0&&x<n&&map[x-1][y][1]==1&&map[x+1][y][1]==1)) return;
        map[x][y][1] = 1;
    }
}
