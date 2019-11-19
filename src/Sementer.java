import java.util.Vector;

public class Sementer {
    private Trie trie;

    Sementer(Trie trie){
        this.trie = trie;
    }

    private Vector<String> FMM(String str){ //正向最大匹配
        int len = str.length();
        int begin = 0;
        int end = begin;

        Vector<String> ans = new Vector<>();

        while(end < len){
            end = trie.search(str);
            if(end <= 0)
                return null;
            else if(end == len){
                ans.add(str);
                break;
            }
            else{
                ans.add(str.substring(begin,end));
                str = str.substring(end,len);
                len = str.length();
                begin = 0;
            }
        }
        return ans;
    }

    private Vector<String> BMM(String str){ //逆向最大匹配
        int len = str.length();
        int begin = 0;
        int end = len;

        Vector<String> ans = new Vector<>();
        while(begin < end){
            for( ; begin < end ; begin++ ){
                if(trie.search(str) == end) {
                    ans.add(0, str.substring(begin, end));
                    str = str.substring(0, begin);
                    break;
                }
            }
        }


        return ans;
    }

    public Vector<String> segment(String str){  //双向最大匹配
        return null;
    }
}
