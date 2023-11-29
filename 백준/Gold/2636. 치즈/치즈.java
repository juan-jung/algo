import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int count,time,R,C;
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<C;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		time = 0;
		int answer = 0;
		while(true) {
			count = 0;
			v = new boolean[R][C];
			melt(map,0,0);
			if(count == 0) break;
			else answer = count;
			time++;
		}
		
		System.out.println(time + "\n" + answer);
		br.close();
	}
	
	static void melt(int[][] map, int x, int y) {
		if(x<0||x>R-1||y<0||y>C-1||v[x][y]) return;
		v[x][y] = true;
		if(map[x][y] == 1) {
			count++;
			map[x][y] = 0;
			return;
		}
		melt(map,x+1,y);
		melt(map,x-1,y);
		melt(map,x,y+1);
		melt(map,x,y-1);
	}
}

/*
출력 : 모두 녹는데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여있는 칸의 개수

처음엔 0,0에서 시작해서 0이면 go 1이면 stop


*/