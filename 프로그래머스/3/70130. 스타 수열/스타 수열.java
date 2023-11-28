import java.util.*;
class Solution {
    public int solution(int[] a) { 
        // a = new int[]{1,3,3,1,2,3};
        int len = a.length;
        if(len < 2) return 0;
        else if(len == 2) {
          if(a[0]!=a[1]) return 2;
          else return 0;
        }
        
        int[][] arr = new int[len][2];
        for(int i=0;i<len;i++) arr[i][1] = -1;
        for(int i=0;i<len;i++) {
            if(i-1>=0 && a[i] != a[i-1] && arr[a[i]][1] != i-1) {
                arr[a[i]][0]++;
                arr[a[i]][1]=i-1;
            }
            else if(i+1 < len && a[i]!=a[i+1]) {
                arr[a[i]][0]++;
                arr[a[i]][1] = i+1;
            }
        }
        // System.out.println(Arrays.deepToString(arr));
        int max = 0;
        for(int i=0;i<len;i++) max=Math.max(max, arr[i][0]);
        return max*2;
    }
}

