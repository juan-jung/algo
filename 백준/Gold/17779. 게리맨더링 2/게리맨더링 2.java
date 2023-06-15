

import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr2;
	static int n;
	static void fill(int x, int y, int num) {
		if( x<0 || y<0 || x>n-1 || y>n-1 || arr2[x][y] != 0 ) return;
		else {
			//System.out.println(x+" "+y+" "+num);
			arr2[x][y] = num;
			fill(x-1,y,num);
			fill(x+1,y,num);
			fill(x,y-1,num);
			fill(x,y+1,num);
		}
	}
	
	public static void main(String[] args) throws IOException {

		//System.setIn(new FileInputStream("src/samsung/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/*
		arr
		[1, 2, 3, 4, 1, 6]
		[7, 8, 9, 1, 4, 2]
		[2, 3, 4, 1, 1, 3]
		[6, 6, 6, 6, 9, 4]
		[9, 1, 9, 1, 9, 5]
		[1, 1, 1, 1, 9, 9]*/
		
		//조건:  y-d1>=0  x+d1+d2<n && y+d2<n
		/*경계선
		1. (x, y), (x+1, y-1), ..., (x+d1, y-d1) -> 상단꼭지점 좌하 -> 좌측꼭지점으로 --> x+d1<n-1  y-d1 >= 0
		2. (x, y), (x+1, y+1), ..., (x+d2, y+d2) -> 상단꼭지점 우하 -> 우측꼭지점으로 --> x+d2<n-1  y+d2<n
		3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2) -> 좌측꼭지점 우하 -> 하측꼭지점으로 --> x+d1+d2 <n  y-d1+d2>0  y-d1+d2<n-1
		4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1) -> 우측꼭지점 좌하   -> 하측꼭지점으로 
		
		
		조건 정리 : d1 for문 -> d1<=y  x+d1<n-1
				 d2 for문 -> d2<n-y x+d1+d2<n  x+d2<n-1 y+d2<n d1+d2>y y-d1+d2<n-1	
		*/
		
//		for(int a[] : arr) System.out.println(Arrays.toString(a));
		
		
		int min_diff = Integer.MAX_VALUE;
		
		for(int x=0;x<n-2;x++) {
			for(int y=1;y<n-1 ;y++) {
				for(int d1=1;d1<=y;d1++) {
					for(int d2=1;x+d1+d2<n && y+d2<n;d2++) {
//						System.out.printf("x: %d, y: %d, d1: %d, d2: %d, cnt: %d",x,y,d1,d2,++cnt);
//						System.out.println();
						arr2 = new int[n][n];
						//1,4 경계선
						for(int i=0;i<=d1;i++) {
							//System.out.printf("x: %d, y: %d, d1: %d, d2: %d",x,y,d1,d2);
							//System.out.println();
							arr2[x+i][y-i] = 5;
							arr2[x+d2+i][y+d2-i] = 5;
						}
						
						//2,3 경계선
						for(int i=0;i<=d2;i++) {
							arr2[x+i][y+i] = 5;
							arr2[x+d1+i][y-d1+i] =5;
						}
						
						//기준점 위로 1
						for(int i=1;i<n-2;i++) {
							if(x-i < 0) break;
							arr2[x-i][y] = 1;
						}
						//우측꼭지점 우측으로 2
						for(int i=1;y+i<n;i++) {
							if(y+i+d2 >n-1) break;
							arr2[x+d2][y+d2+i] = 2;
						}
						//좌측꼭지점 좌측으로 3
						for(int i=1;i<n-2;i++) {
							if(y-d1-i<0) break;
							arr2[x+d1][y-d1-i] = 3;
						}
						//하단꼭지점 하단으로 4
						for(int i=1;i<n-2;i++) {
							if(x+d1+d2+i>n-1) break;
							arr2[x+d1+d2+i][y-d1+d2] = 4;
						}
						
						//각 영역 채우기
						fill(0,0,1);
						fill(0,n-1,2);
						fill(n-1,0,3);
						fill(n-1,n-1,4);
						
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						int[] cnt = new int[6];
						
						for(int i=0;i<n;i++) {
							for(int j=0;j<n;j++) {
								cnt[arr2[i][j]] += arr[i][j];
							}
						}
						cnt[5] += cnt[0];
						
						for(int i=1;i<6;i++) {
							if(max <= cnt[i]) max = cnt[i];
							if(min >= cnt[i]) min = cnt[i];
						}
						
						if(max-min <= min_diff) {
							min_diff = max-min;
							//System.out.println(max+ " "+min);
							//System.out.println(Arrays.toString(cnt));
						}
						
						
						
						
						
						//for(int[] a: arr2) System.out.println(Arrays.toString(a));
						//System.out.println();
						
						
						
						
					}
				}
			}
		}
		
		System.out.println(min_diff);
		
		br.close();
	
	}
}
