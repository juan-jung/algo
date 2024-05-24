import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1,-1,0,1,1,1,0,-1}, dy = {0,1,1,1,0,-1,-1,-1};
    static class FireBall {
        int m;
        int s;
        int d;
        int turn;
        FireBall next;

        public FireBall(int m, int s, int d, int turn) {
            this.m = m;
            this.s = s;
            this.d = d;
            this.turn = turn;
        }

        void move(int x, int y, int N, FireBallGroup[][] map, int turn) {
            map[x][y].head = this.next;
            if(map[x][y].head==null) map[x][y].tail=null;

             int nx = (x  + dx[this.d] * this.s)%N; // 4  -2->3 , 6->1
            int ny = (y + dy[this.d] * this.s)%N;
            if(nx<0) nx += N;
            else if(nx>N-1) nx%=N;
            if(ny<0) ny += N;
            else if(ny>N-1) ny%=N;

            this.turn = turn;
            this.next = null;
            if(map[nx][ny].head==null) {
                map[nx][ny].head = this;
                map[nx][ny].tail = this;
            }
            else {
                map[nx][ny].tail.next = this;
                map[nx][ny].tail = this;
            }
        }

        void combine(int x, int y, FireBallGroup[][] map, int N) {
            int fireBallCnt = 0;
            int odd_cnt = 0;
            int mSum = 0;
            int sSum = 0;
            FireBall cur = this;
            while(cur!=null) {
                fireBallCnt++;
                if(cur.d%2==1) odd_cnt++;
                mSum+=cur.m;
                sSum+=cur.s;
                cur = cur.next;
            }
            map[x][y].head = null;
            map[x][y].tail = null;
            mSum/=5;
            if(mSum == 0) return;
            sSum/=fireBallCnt;
            int i = odd_cnt==fireBallCnt || odd_cnt==0 ? 0 : 1;
            FireBall head = new FireBall(mSum,sSum,i,this.turn);
            map[x][y].head = head;
            for(i+=2;i<8;i+=2) {
                head.next = new FireBall(mSum,sSum,i,this.turn);
                head = head.next;
                if(i==7 || i==6) map[x][y].tail = head;
            }

        }
    }
    static class FireBallGroup {
        FireBall head;
        FireBall tail;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        FireBallGroup[][] map = new FireBallGroup[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                map[i][j] = new FireBallGroup();
            }
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            FireBall fireBall = new FireBall(m,s,d,0);
            map[r][c].head = fireBall;
            map[r][c].tail = fireBall;
        }

        for(int k=1;k<=K;k++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    FireBall cur = map[i][j].head;
                    while(cur!=null && cur.turn<k) {
                        cur.move(i,j,N,map,k);
                        cur = map[i][j].head;
                    }
                }
            }

            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    FireBall cur = map[i][j].head;
                    if(cur!=null && cur.next!=null) {
                        cur.combine(i,j,map,N);
                    }
                }
            }
        }

        int ans = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                FireBall ball = map[i][j].head;
                while(ball!=null) {
//                    System.out.println(i + " " + j + " " + ball.m);
                    ans += ball.m;
                    ball = ball.next;
                }
            }
        }
        System.out.println(ans);
        br.close();
    }
}
/*
//50 * 50 * 1000 = 2,500,000 // 한 연산 당 최대허용 약 40회
 */