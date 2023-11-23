import java.util.*;
class Solution {
    class Map {
        int turn;
        boolean[][] redV;
        boolean[][] blueV;
        int redX;
        int redY;
        int blueX;
        int blueY;
        
        Map(int turn, boolean[][] redV, boolean[][] blueV, int redX, int redY, int blueX, int blueY) {
            this.turn = turn;
            this.redV = copyArr(redV);
            this.blueV = copyArr(blueV);
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
        }
        
        Map(Map map) {
            this.turn = map.turn;
            this.redV = copyArr(map.redV);
            this.blueV = copyArr(map.blueV);
            this.redX = map.redX;
            this.redY = map.redY;
            this.blueX = map.blueX;
            this.blueY = map.blueY;
        }
    }
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    public int solution(int[][] maze) {
        int answer = 0;
        int redX=0, redY=0, blueX=0, blueY=0, redTargetX=0, redTargetY=0, blueTargetX=0, blueTargetY=0;
        for(int i=0;i<maze.length;i++) {
            for(int j=0;j<maze[i].length;j++) {
                if(maze[i][j] == 1) {
                    redX = i;
                    redY = j;
                }
                else if(maze[i][j] == 2) {
                    blueX = i;
                    blueY = j;
                }
                else if(maze[i][j] == 3) {
                    redTargetX = i;
                    redTargetY = j;
                }
                else if(maze[i][j] == 4) {
                    blueTargetX = i;
                    blueTargetY = j;
                }
            }
        }
        
        boolean[][] redV = new boolean[maze.length][maze[0].length];
        redV[redX][redY] = true;
        boolean[][] blueV = new boolean[maze.length][maze[0].length];
        blueV[blueX][blueY] = true;
        Map map = new Map(0,redV,blueV,redX,redY,blueX,blueY);
        PriorityQueue<Map> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1.turn, o2.turn));
        pq.offer(map);
        while(!pq.isEmpty()) {
            Map cur = pq.poll();
            
            //**debug start**
//             System.out.println(cur.turn + " red: " + cur.redX + "," + cur.redY + ", blue : "+cur.blueX + "," + cur.blueY);
            
//             System.out.println("redV");    
//             for(boolean[] a : cur.redV) {
//                 System.out.println(Arrays.toString(a));    
//             }
//             System.out.println("blueV");
//              for(boolean[] a : cur.blueV) {
//                 System.out.println(Arrays.toString(a));    
//             }
//             System.out.println("-------------------------");    
            // if(cur.turn == 10) return 0;
            //**debug end**
            
            if(cur.redX==redTargetX&&cur.redY==redTargetY&&cur.blueX==blueTargetX&&cur.blueY==blueTargetY) {
                answer = cur.turn;
                break;
            }
            
            end:for(int d=0;d<4;d++) {
                for(int d2=0;d2<4;d2++) {
                    int redNX = cur.redX + dx[d];
                    int redNY = cur.redY + dy[d];
                    if(redNX<0||redNX>maze.length-1||redNY<0||redNY>maze[0].length-1||maze[redNX][redNY]==5) continue;
                    else if(cur.redX==redTargetX&&cur.redY==redTargetY) {
                        redNX = redTargetX;
                        redNY = redTargetY;
                    }
                    else if(!(redNX==redTargetX&&redNY==redTargetY)&&cur.redV[redNX][redNY]) continue;

                    int blueNX = cur.blueX + dx[d2];
                    int blueNY = cur.blueY + dy[d2];
                    if(blueNX<0||blueNX>maze.length-1||blueNY<0||blueNY>maze[0].length-1||maze[blueNX][blueNY]==5) continue;
                    else if(cur.blueX==blueTargetX&&cur.blueY==blueTargetY) {
                        blueNX = blueTargetX;
                        blueNY = blueTargetY;
                    }
                    else if((blueNX!=blueTargetX&&blueNY!=blueTargetY)&&cur.blueV[blueNX][blueNY]) continue;

                    if(redNX==blueNX && redNY==blueNY) continue;
                    if((cur.redX==blueNX&&cur.redY==blueNY) && (cur.blueX==redNX&&cur.blueY==redNY)) continue;

                    Map next = new Map(cur);
                    next.turn++;
                    next.redV[redNX][redNY] = true;
                    next.blueV[blueNX][blueNY] = true;
                    next.redX = redNX;
                    next.redY = redNY;
                    next.blueX = blueNX;
                    next.blueY = blueNY;
                    pq.offer(next);
                }
            }
        }
        return answer;
    }
    
    int[][] copyArr(int[][] map) {
        int[][] temp = new int[map.length][map[0].length];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }
    
    boolean[][] copyArr(boolean[][] map) {
        boolean[][] temp = new boolean[map.length][map[0].length];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }
}