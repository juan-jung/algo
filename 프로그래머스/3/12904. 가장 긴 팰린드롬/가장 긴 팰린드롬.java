class Solution
{
    public int solution(String s)
    {
        int ans = 1;

        for(int i=1;i<s.length();i++) {
            int cnt1 = 1; // 홀수 길이
            int cnt2= 0; // 짝수 길이
            
            int s1 = i-1;
            int e1 = i+1;
            if(i!=s.length()-1) while(s1>=0&&e1<s.length()&&s.charAt(s1--)==s.charAt(e1++)) cnt1+=2;
            ans = Math.max(ans, cnt1);
            
            int s2 = i-1;
            int e2= i;
            while(s2>=0&&e2<s.length()&&s.charAt(s2--)==s.charAt(e2++)) cnt2+=2;
            ans = Math.max(ans, cnt2);
        }
        return ans;
    }
}