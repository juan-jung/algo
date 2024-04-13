

import java.io.*;
import java.util.*;

public class Main {
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String[] lines = new String[n];
    for(int i=0; i<n; i++) {
      lines[i]=br.readLine();
    }

    int groups = 0;
    boolean[][] v = new boolean[n][n];
    ArrayList<Integer> houstCount = new ArrayList<>();
    for(int x=0; x<lines.length; x++) {
      for(int y=0; y<lines[x].length(); y++) {
        if(lines[x].charAt(y) == '1' && !v[x][y]) {
          groups++;
          int cnt = dfs(x, y, lines, v, n);
          houstCount.add(cnt);
        }
      }
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append(groups + "\n");
    Collections.sort(houstCount);
    for(int a : houstCount) sb.append(a + "\n");
    System.out.println(sb);
    br.close();
  }

  static int dfs(int x, int y, String[] lines, boolean[][] v, int N) {
    if(x < 0 || x >= N || y < 0 || y >= N || v[x][y] || lines[x].charAt(y) == '0') return 0;
    v[x][y] = true;
    int count = 1; // 현재 위치에서 1인 지점의 개수
    for(int d = 0; d < 4; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];
      count += dfs(nx, ny, lines, v, N); // 재귀 호출 후 결과를 현재 count에 더함
    }
    return count;
  }


}
