class Solution {
        
    boolean flag;
    public int[] solution(long[] numbers) {
        
        // numbers = new long[]{7L};
        int[] answer = new int[numbers.length];
        
        for(int i=0;i<numbers.length;i++) {
            //1. 이진수변환 
            String binary = toBinary(numbers[i]);
            // System.out.println(binary);
            
            //2. 포화 이진트리로 만들기
            String tree = toTree(binary);
            // System.out.println(tree);
            
            //3. 루트노드 부터 자식으로 순회, 자식이 있는데 더미 일 경우 return 0;
            flag = true;
            check(0,tree.length()-1, tree);
            // System.out.println(flag);
            
            //4. 3번 조건에서 안걸렸다면 return 1
            answer[i] = flag ? 1 : 0;
        }
        
        return answer;
    }
// 012 3 456 7 8910 11 121314
    void check(int start, int end, String tree) {
        int parent = (start + end) / 2; //1
        if(parent%2==0) return;
        int left = (start+parent-1)/2; // 1 -1 = 0
        int right = (end+parent+1)/2; // 1 + 1 = 2
        if(tree.charAt(parent)=='1') {
            check(start,parent-1, tree);
            check(parent+1,end, tree);
        }
        else if(tree.charAt(parent)=='0') {
            if(tree.charAt(left) == '0' && tree.charAt(right) == '0') {
                check(start,parent-1, tree);
                check(parent+1,end, tree);
            }
            else {
                flag = false;
                return;
            }
        }
    }
    
    String toTree(String binary) {
        int length = binary.length();
        int start = 1;
        int idx = 1;
        while(start < length) {
            start = start + (int)Math.pow(2,idx++);    
        }
        
        for(int i=0;i<start-length;i++) {
            binary = "0" + binary;
        }
        return binary;
    }
    
    String toBinary(long number) {
        String s = "";
        while(number!=1) {
            if(number%2==0) s = "0" + s;
            else s = "1" + s;
            number/=2;
        }
        return 1+s;
    }
}

/*
1
1+2=3
1+2+4=7
1+2+4+8=15
1+2+4+8+16=31
1+2+4+8+16+32=63

1 - > 1
2,3 -> 3
4,5,6,7 -> 7
8,9,10,11,12,13,14,15->15
*/

    