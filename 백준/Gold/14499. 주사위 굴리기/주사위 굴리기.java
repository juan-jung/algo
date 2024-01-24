
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dice = new int[6]; // 위0 아래1 동2 서3 남4 북5
		int[][] map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		int[] dy = {0,0,-1,1};
		int[] dx = {1,-1,0,0};
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<K;i++) {
			int d = Integer.parseInt(st.nextToken())-1;
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx<0||nx>M-1||ny<0||ny>N-1) continue;
			//동 : 위 ->동, 동->아래, 아래->서, 서->위
			int temp = dice[0];
			if(d==0) {
				dice[0] = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[2];
				dice[2] = temp;
			}
			//서 : 위->서, 서->아래, 아래->동, 동->위
			else if(d==1) {
				dice[0] = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[3];
				dice[3] = temp;
			}
			//북 : 위->북, 북->아래, 아래->남, 남->위
			// 위0 아래1 동2 서3 남4 북5
			else if(d==2) {
				dice[0] = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[5];
				dice[5] = temp;
			}
			//남 : 위->남, 남->아래, 아래->북, 북->위
			else if(d==3) {
				dice[0] = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[4];
				dice[4] = temp;
			}
			if(map[ny][nx]==0) map[ny][nx] = dice[1];
			else {
				dice[1] = map[ny][nx];
				map[ny][nx] = 0;
			}
			x=nx;
			y=ny;
			System.out.println(dice[0]);
		}
	}
}
