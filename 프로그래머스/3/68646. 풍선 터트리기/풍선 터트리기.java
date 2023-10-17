import java.util.*;

class Solution {
    
    public int solution(int[] a) {
        // int answer = 2;
        HashSet<Integer> set = new HashSet<>();
        int len = a.length;
        
        // int[] left = new int[len];
        // int[] right = new int[len];
        int lmin = Integer.MAX_VALUE;
        int rmin = Integer.MAX_VALUE;
        
        for(int i=0;i<len;i++) {
            // left[i] = lmin;
            if(lmin > a[i]) lmin = a[i];
            
            // right[len-i-1] = rmin;
            if(rmin > a[len-i-1]) rmin = a[len-i-1];
            set.add(lmin);
            set.add(rmin);
        }
        
        // for(int i=1;i<len-1;i++) {
        //     if(a[i] < left[i] || a[i] < right[i]) answer++;
        // }
        
        return set.size();
    }
}