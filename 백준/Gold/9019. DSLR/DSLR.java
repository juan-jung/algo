

import java.io.*;
import java.util.*;

public class Main {
	static class Register {
		int num;
		String op;
		
		Register(int num, String op) {
			this.num = num;
			this.op = op;
		}
		
		int D() {
			return num * 2 % 10000;
		}
		
		int S() {
			return num == 0 ? 9999 : num-1;
		}
		
		int L() {
			//1234 12341
			return (num * 10 + num /1000)%10000;
		}
		
		int R() {
			return num % 10 * 1000 + num /10;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			boolean[] v = new boolean[10000];
			
			PriorityQueue<Register> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1.op.length(), o2.op.length()));
			pq.offer(new Register(A, ""));
			v[A] = true;
			while(!pq.isEmpty()) {
				Register cur = pq.poll();
				
				if(cur.num == B) {
					System.out.println(cur.op);
					break;
				}
				
				if(!v[cur.D()]) {
					pq.offer(new Register(cur.D(), cur.op + "D"));
					v[cur.D()] = true;
				}
				if(!v[cur.S()]) {
					pq.offer(new Register(cur.S(), cur.op + "S"));
					v[cur.S()] = true;
				}
				if(!v[cur.L()]) {
					pq.offer(new Register(cur.L(), cur.op + "L"));
					v[cur.L()] = true;
				}
				if(!v[cur.R()]) {
					pq.offer(new Register(cur.R(), cur.op + "R"));
					v[cur.R()] = true;
				}
				
			}
		}
		br.close();
	}
	
	
}

/*
레지스터 0000 - 9999 저장가능

D : 곱하기2 / 9999초과일경우 10000으로 나눈 나머지
S : 빼기1 n이 0 이면 9999 저장
L : d1, d2, d3, d4 를 왼쪽으로 회전 , 즉  d2,d3,d4,d1
R : 오른쪽회전 

시간제한 6초 / 메모리제한 256MB

*/
