class Solution {
    static int count0, count1;
    public int[] solution(int[][] arr) {
        int[] answer = {};
        count0 = 0;
        count1 = 0;
        zip(arr, arr.length, 0, 0);
        return new int[]{count0, count1};
    }
    
    public void zip(int[][] arr, int n, int x, int y) {
        if(n==1) {
            if(arr[x][y] == 0) count0++;
            else count1++;
             // System.out.println("n:" + n + ", count0: "+count0 + ", count1: "+count1);
            return;
        }
        int temp = arr[x][y];
        boolean flag = true;
        end:for(int i=x;i<x+n;i++) {
            for(int j=y; j<y+n; j++) {
                if(temp != arr[i][j]) {
                    flag = false;
                    zip(arr, n/2, x,y);
                    zip(arr, n/2, x+n/2, y+n/2);
                    zip(arr, n/2, x+n/2,y);
                    zip(arr, n/2, x, y+n/2);
                    break end;
                }
            }
        }
        if(flag) {
            if(temp == 0) count0++;
            else count1++;
            // System.out.println("n:" + n + ", count0: "+count0 + ", count1: "+count1);
        }
       
    }
}