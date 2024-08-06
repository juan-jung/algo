class Solution {
    static int answer;
    public int solution(int n) {
        answer = 0;
        
        int[] row = new int[n];
        dfs(0,n,row);
        return answer;
    }
    
    void dfs(int cnt, int n, int[] row) {
        if(cnt == n) {
            answer++;
            return;
        }
        
        end : for(int i=0;i<n;i++) {
            
            for(int j=cnt-1;j>=0;j--) {
                if(row[j]==i || (Math.abs(cnt-j)==Math.abs(i-row[j]))) continue end;
            }
            
            row[cnt] = i;
            dfs(cnt+1,n,row);
        }
    }
}
