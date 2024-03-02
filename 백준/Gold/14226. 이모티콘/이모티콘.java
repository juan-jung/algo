import java.io.*;
import java.util.*;
/**
 * 시간제한 1초, 메모리제한 512MB
 * 연산1 복사
 * - 화면 -> 클립보드 복사(이전 내용 덮어쓰여짐)
 * 연산2 붙여넣기
 * - 클립보드 -> 화면 붙여넣기 (클립보드가 비어있다면 불가, 일부만 삭제 및 붙여넣기 불가)
 * 연산3 삭제
 * 화면에 있는 이모티콘 한 개 삭제
 * 
 * 모든 연산 1초 소요
 * 
 * 초기 이모티콘 1개 -> S개를 만드는데 걸리는 최소 소요시간 (2 <= S <= 1000)
 * @author crumb
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2], o2[2])); //int[] 0 : 화면, 1 : 클립보드, 2 : 소요시간
		pq.offer(new int[] {1,0,0});
		boolean[][] v = new boolean[S*2+1][S*2+1];
		v[1][0] = true;
		int ans = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
            if(cur[0]>S*2||cur[1]>S*2||cur[0]+cur[1]>S*2) continue;
            
			if(cur[0]==S) {
				ans = cur[2];
				break;
			}
						
			//copy
			if(cur[0]!=0&&!v[cur[0]][cur[0]]) {
				pq.offer(new int[] {cur[0], cur[0], cur[2]+1});
				v[cur[0]][cur[0]] = true;
			}
			
			//paste 붙여넣기해도 클립보드 유지(예제 3 참고) 
			if(cur[1]!=0&&!v[cur[0]+cur[1]][cur[1]]) {
				pq.offer(new int[] {cur[0]+cur[1],cur[1],cur[2]+1});
				v[cur[0]+cur[1]][cur[1]] = true;
			}
			
			//delete
			if(cur[0]!=0&&!v[cur[0]-1][cur[1]]) {
				pq.offer(new int[] {cur[0]-1,cur[1],cur[2]+1});
				v[cur[0]-1][cur[1]] = true;
			}
			
		}
		
		System.out.println(ans);
		sc.close();
	}
}

