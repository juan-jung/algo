

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  
  // public interface HouseComplexMap {  
  //   int line(int i);
  //   // BufferedReader map()
  // }

  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
  


  static int dfs(int x, int y, String[] lines, boolean[][] v, int N, int cnt) {
    if(x<0 || x>N-1 || y<0 || y>N-1  || v[x][y] || lines[x].charAt(y) == '0') return 1;
    v[x][y] = true;
    int ans = 0;
    for(int d=0; d<4; d++) {
      int nx = x+dx[d];
      int ny = y+dy[d];
      ans += dfs(nx, ny, lines, v, N, cnt+1);
    }
    return ans;
  }

  static int bfs(int x, int y, String[] lines, boolean[][] v, int N) {
    int cnt = 0;
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{x,y}); // q.push //[{1,2}], [{1,3}] O | [1,2,3,4,5] X
    while (!q.isEmpty()) {
      int[] cur = q.poll(); // q.pop
      if(v[cur[0]][cur[1]]) continue;
      v[cur[0]][cur[1]] = true;
      cnt ++;
      for(int d=0; d<4; d++) {
        int nx = cur[0]+dx[d];
        int ny = cur[1]+dy[d];
        if(nx<0
        || nx>N-1 
        || ny<0 
        || ny>N-1  
        || v[nx][ny] 
        || lines[nx].charAt(ny) == '0'
        ) continue;
        q.offer(new int[]{nx,ny});
      }
    }
    return cnt;
  }

  public static void main(String[] args) throws Exception {

/* 
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000 
*/
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int n = Integer.parseInt(br.readLine());
    // int[][] map = new int[n][n];
    // for(int i=0; i<n; i++){
    //   String row = br.readLine();
    //   for(int j=0; j<n; j++) {
    //     map[i][j] = row.charAt(i)-'a';
    //     System.out.println((int)row.charAt(i));
    //   }
    // }

    String[] lines = new String[n];
    for(int i=0; i<n; i++) {
      lines[i]=br.readLine();
    }

    boolean[][] v = new boolean[n][n];
    
    int groups = 0;
    ArrayList<Integer> houstCount = new ArrayList<>();

    
    for(int x=0; x<lines.length; x++) {
      for(int y=0; y<lines[x].length(); y++) {
        if(lines[x].charAt(y) == '1' && !v[x][y]) {
          // bfs 
          // groups++;
          // houstCount.add(bfs(x, y, lines, v, n));

          // dfs 
          groups++;
          int cnt = dfs(x, y, lines, v, n, 0);
          houstCount.add(cnt);
        }
      }
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append(groups);
    sb.append("\n");
    Collections.sort(houstCount);
    for(int a : houstCount) {
      sb.append((a-1)/3);
      sb.append("\n");
    }
    
    System.out.println(sb);
    // StringTokenizer st = new StringTokenizer(br.readLine());
    // while(st.hasMoreTokens()) {
    //   bw.write(st.nextToken());
    // }
    // bw.flush();
    // bw.close();
  }

}
