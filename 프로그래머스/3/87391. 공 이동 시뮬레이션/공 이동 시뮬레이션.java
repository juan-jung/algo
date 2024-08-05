class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        
        int maxX = x;
        int minX = x;
        int maxY = y;
        int minY = y;

        for(int i=queries.length-1;i>=0;i--) {
            int dir = queries[i][0];
            int dx = queries[i][1];
            //좌
            if(dir==0) {
                 if(minY==m-1 || ((minY+dx > m-1)&&minY!=0)) {
                    return 0;
                }
                minY = minY==0 ? minY : minY + dx;
                maxY = Math.min(maxY+dx,m-1);
            }
            //우
            else if(dir==1) {
                if(maxY==0 || ((maxY-dx < 0)&&maxY!=m-1)) {
                    return 0;
                }
                minY = Math.max(minY-dx,0);
                maxY = maxY==m-1? maxY : maxY - dx;
            }
            //상
            else if(dir==2) {
                if(minX==n-1 || ((minX+dx > n-1)&&minX!=0)) {
                    return 0;
                }
                minX = minX==0 ? minX : minX+dx;
                maxX = Math.min(maxX+dx,n-1);
            }
            //하
            else if(dir==3) {
                if(maxX==0 || ((maxX-dx < 0)&&maxX!=n-1)) {
                    return 0;
                }
                minX = Math.max(minX-dx,0);
                maxX = maxX==n-1? maxX : maxX-dx;
            }
            
            // System.out.println(minX + " " + minY + " " + maxX + " " + maxY);
        }
        
        answer = (long)(maxX-minX+1)  * (long)(maxY-minY+1);
        return answer;
    }
}