
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;
    static class TrieNode implements Serializable {
        int num;//有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        Map<Character, TrieNode> sons;//所有的儿子节点
        boolean isEnd;//是不是最后一个节点
        boolean haveSon;//有无孩子
        TrieNode()
        {
            num = 1;
            sons = new HashMap<>();
            isEnd=false;
            haveSon=false;
        }
    }

    Trie(){
        root = new TrieNode();
    }

//    public Trie(TrieNode node){
//        root = node;
//    }

    public TrieNode getRoot(){
        return root;
    }

    void insert(String phrase){
        if(phrase == null||phrase.length() < 1)
            return;
        TrieNode node = root;
        char[] letters = phrase.toCharArray();
        for (char letter : letters) {
            if (!node.sons.containsKey(letter)) {
                node.haveSon = true;
                node.sons.put(letter, new TrieNode()) ;
            } else {
                node.num++;
            }
            node = node.sons.get(letter);
        }
        node.isEnd = true;
    }

    int search(String str){
        TrieNode node = root;
        char[] letters = str.toCharArray();
        int ans = 0;
        for (int num = 0;num < letters.length;num++) {
            if (!node.sons.containsKey(letters[num]))
                break;
            node = node.sons.get(letters[num]);
            if(node.isEnd)
                ans = num + 1;
        }
        return ans;
    }
}
