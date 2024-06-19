import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());//격자 크기
        int M = Integer.parseInt(st.nextToken());//색상의 개수

        int[][] map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        while(true) {
            int max_color = 0;
            int[] max_standard = new int[2];
            int max_size = 0;
            int max_rainbow_cnt = 0;

            //블록 그룹 찾기
            boolean[][] v = new boolean[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(map[i][j]<=0|| v[i][j]) continue;

                    int color = map[i][j];
                    int[] standard = {i,j};
                    int size = 0;
                    int rainbow_cnt = 0;

                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{i,j});
                    v[i][j] = true;
                    boolean[][] rain_v = new boolean[N][N];
                    while(!q.isEmpty()) {
                        int[] cur = q.poll();

                        size++;

                        for(int d=0;d<4;d++) {
                            int nx = cur[0]+dx[d];
                            int ny = cur[1]+dy[d];
                            if(nx<0||nx>N-1||ny<0||ny>N-1||v[nx][ny]||rain_v[nx][ny]||map[nx][ny]<0) continue;
                            if(map[nx][ny]>0&&map[nx][ny]!=color) continue;
                            q.offer(new int[]{nx,ny});
                            if(map[nx][ny]==0) {
                                rain_v[nx][ny] = true;
                                rainbow_cnt++;
                            }
                            else v[nx][ny] = true;
                        }
                    }

                    if(size < 2) continue;

                    if(check(max_size,max_rainbow_cnt,max_standard,size,rainbow_cnt,standard)) {
                        max_color = color;
                        max_standard = new int[]{i,j};
                        max_size = size;
                        max_rainbow_cnt = rainbow_cnt;
                    }
                }
            }

            if(max_size == 0) break;

            //제거
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(max_standard);
            boolean[][] del_v = new boolean[N][N];
            del_v[max_standard[0]][max_standard[1]] = true;
            while(!q.isEmpty()) {
                int[] cur = q.poll();

                map[cur[0]][cur[1]] = -2;

                for(int d=0;d<4;d++) {
                    int nx = cur[0]+dx[d];
                    int ny = cur[1]+dy[d];
                    if(nx<0||nx>N-1||ny<0||ny>N-1||del_v[nx][ny]||map[nx][ny]<0) continue;
                    if(map[nx][ny]>0&&map[nx][ny]!=max_color) continue;
                    q.offer(new int[]{nx,ny});
                    del_v[nx][ny] = true;
                }
            }

            answer += Math.pow(max_size, 2);

            //중력
            map = gravity(map,N);

            //반시계 90도
            map = rotate90(map,N);

            //중력
            map = gravity(map,N);

        }
        System.out.println(answer);
        br.close();
    }

    static int[][] rotate90(int[][] map, int N) {
        int[][] temp = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                temp[N-j-1][i] = map[i][j];
            }
        }
        return temp;
    }

    static int[][] gravity(int[][] map, int N) {
        for(int j=0;j<N;j++) {
            for(int i=N-1;i>=0;i--) {
                if(map[i][j]>=0) {
                    for(int s=i+1;s<N;s++) {
                        if(map[s][j]!=-2) break;
                        else {
                            int temp = map[s-1][j];
                            map[s][j]=temp;
                            map[s-1][j] = -2;
                        }
                    }
                }
            }
        }

        return map;
    }

    static boolean check(int max_size, int max_rainbow_cnt,int[] max_standard,int size, int rainbow_cnt, int[] standard) {
        if(size > max_size) return true;
        else if(size == max_size) {
            if(rainbow_cnt > max_rainbow_cnt) return true;
            else if(rainbow_cnt == max_rainbow_cnt) {
                if(standard[0] > max_standard[0]) return true;
                else if(standard[0]==max_standard[0]) {
                    if(standard[1] > max_standard[1]) return true;
                    return false;
                }
            }
        }
        return false;
    }
}
