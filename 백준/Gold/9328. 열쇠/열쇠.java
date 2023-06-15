
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			int h=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			char[][] building=new char[h][w];
			ArrayDeque<int[]> q=new ArrayDeque<>();
			boolean[][] visited = new boolean[h][w];
			boolean[] keys=new boolean[26];
			ArrayList<int[]> gates = new ArrayList<>();
			int docs = 0;
			for(int i=0;i<h;i++) {
				String s=br.readLine();
				for(int j=0;j<w;j++) {
					building[i][j]=s.charAt(j);
					if((i==0 || i==h-1 || j==0 || j==w-1) && building[i][j]!='*') {
						if(building[i][j]=='.') {
							q.offer(new int[] {i,j});
							visited[i][j]=true;							
						}
						else if(building[i][j] >= 'a' && building[i][j]<='z') {
							keys[building[i][j]-'a'] = true;
							q.offer(new int[] {i,j});
							visited[i][j]=true;	
						}
						else if(building[i][j] >= 'A' && building[i][j] <= 'Z') {
							if(keys[building[i][j]-'A']) {
								q.offer(new int[] {i,j});
								visited[i][j]=true;
							}
							//열쇠없으면 대기
							else {
								gates.add(new int[] {i,j});
							}
						}
						else if(building[i][j]=='$'){
							q.offer(new int[] {i,j});
							visited[i][j]=true;
						}
					}
				}
			}
//			for(char[] ca: building) {
//				for(char c: ca) System.out.print(c+" ");
//				System.out.println();
//			}
			String key = br.readLine();
			if(!key.equals("0")) {
				for(int i=0;i<key.length();i++) {
					keys[key.charAt(i)-'a'] = true;
				}
			}
			
			int[] dx= {-1,1,0,0};
			int[] dy= {0,0,-1,1};
			
			
			for(int[] a: gates) {
				if(keys[building[a[0]][a[1]]-'A']) {
					q.offer(new int[] {a[0],a[1]});
					visited[a[0]][a[1]]=true;
				}
			}
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
//				System.out.println("x: "+cur[0]+" y: "+cur[1]);
				//.:빈공간  *:벽  $:훔쳐야하는 문서  대문자:문  소문자:열쇠 
				
				//문서일 떄처리
				if(building[cur[0]][cur[1]] == '$') docs++;
				for(int d=0;d<4;d++) {
					int nx=cur[0]+dx[d];
					int ny=cur[1]+dy[d];
					if(nx<0||nx>h-1||ny<0||ny>w-1||visited[nx][ny]||building[nx][ny]=='*') continue;
					
					//문일 때
					if(building[nx][ny] >= 'A' && building[nx][ny] <= 'Z') {
						//열쇠 있으면 들어가고
						if(keys[building[nx][ny]-'A']) {
							q.offer(new int[] {nx,ny});
							visited[nx][ny]=true;
						}
						//열쇠없으면 대기
						else {
							gates.add(new int[] {nx,ny});
						}
					}
					//열쇠 일때
					else if(building[nx][ny]>='a' && building[nx][ny] <= 'z') {
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=true;
						keys[building[nx][ny]-'a'] =true;
						for(int[] a: gates) {
							if(building[a[0]][a[1]]-'A' == building[nx][ny]-'a') {
								q.offer(new int[] {a[0],a[1]});
								visited[a[0]][a[1]]=true;
							}
						}
					}
					//빈공간,문서일때
					else {
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=true; 
					}
				}
			}
			System.out.println(docs);
		}
		br.close();
	}
}
