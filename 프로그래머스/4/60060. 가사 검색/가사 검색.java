import java.util.*;
class Solution {
    class Node {
        Map<Character, Node> child;
        boolean endOfWord;
        Map<Integer,Integer> lenCountMap;
        
       public Node() {
           this.child = new HashMap<>();
           this.endOfWord = false;
           this.lenCountMap =  new HashMap<>();
       }
    }
    
    class Trie {
        Node root;
        
        public Trie() {
            this.root  = new Node();  
            
        }
        
        public void insert(String word){
            Node node = this.root;
            
            for(int i=0;i<word.length();i++) {
                char c = word.charAt(i);
                
                node.child.putIfAbsent(c, new Node());
                node.lenCountMap.put(word.length(), node.lenCountMap.getOrDefault(word.length(),0)+1);
                node = node.child.get(c);
                
            }
            
            node.endOfWord = true;
        }
        
        public int count(String query) {
             return count(0, query, this.root);
        }
        
        private int count(int idx, String query, Node node) {
            int cnt = 0;
            
            if(query.charAt(idx)=='?')  {
                return node.lenCountMap.getOrDefault(query.length(),0);
            }
            
            Node temp = node.child.get(query.charAt(idx));
            if(temp!=null) cnt+=count(idx+1,query, temp);
            
            return cnt;
        }
    }
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        Trie frontTrie = new Trie();
        Trie backTrie = new Trie();
        
        for(String word : words) {
            frontTrie.insert(word);
            backTrie.insert(reverse(word));
        }
        
        for(int i=0;i<queries.length;i++)  {
            int cnt  = 0;
            if(queries[i].charAt(0)=='?')   {
                cnt = backTrie.count(reverse(queries[i]));
            }
            else  {
                 cnt = frontTrie.count(queries[i]);
            }
           
            answer[i] = cnt;
        }
        
        return answer;
    }
    
    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
