import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] choco;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        choco = new char[N][N];
        for(int i=0;i<N;i++) {
            choco[i] = br.readLine().toCharArray();
        }

        List<int[]> list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            end:for(int j=0;j<N;j++) {
                if(choco[i][j]!='#') continue;
                choco[i][j] = '.';
                boolean flag = false;
                boolean[][] v = new boolean[N][N];
                for(int k=0;k<N;k++) {
                    for(int s=0;s<N;s++) {
                        if(choco[k][s]!='#' || v[k][s]) continue;

                        if(!flag) {
                            if(isCycle(k,s,i,j,v)) {
                                choco[i][j] = '#';
                                continue end;
                            }
                            else flag = true;
                        }
                        else {
                            choco[i][j] = '#';
                            continue end;
                        }
                    }
                }
                if(flag) list.add(new int[]{i,j});
                choco[i][j] = '#';
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()+"\n");
        for(int[] a : list) sb.append((a[0]+1) + " " + (a[1]+1)+"\n");
        System.out.println(sb);
        br.close();
    }
    static boolean isCycle(int i, int j, int pi, int pj, boolean[][] v) {
        v[i][j] = true;
        for(int d=0;d<4;d++) {
            int ni = i+dx[d];
            int nj = j+dy[d];
            if(ni<0||ni>N-1||nj<0||nj>N-1||choco[ni][nj]!='#') continue;
            if(!v[ni][nj]) {
                if(isCycle(ni,nj,i,j,v)) return true;
            }
            else if(ni!=pi||nj!=pj) return true;
        }
        return false;
    }
}


