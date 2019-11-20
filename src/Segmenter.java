import java.util.Vector;

public class Segmenter {
    private Trie trie;

    Segmenter(Trie trie){
        this.trie = trie;
    }

    private Vector<String> FMM(String str){ //正向最大匹配
        int len = str.length();
        //int begin = 0;
        int end = 0;

        Vector<String> ans = new Vector<>();

        while(end < len){
            end = trie.search(str);
            if(end == len){
                ans.add(str);
                break;
            } else {
                if(end == 0) {
                    System.out.println("警告：词典中没有词\"" + str.charAt(0) + "\"！");
                    end ++;
                }
                ans.add(str.substring(0,end));
                str = str.substring(end,len);
                len = str.length();
                end = 0;
            }
        }
        return ans;
    }

    private Vector<String> BMM(String str){ //逆向最大匹配
        int end = str.length();
        int begin = 0;
        //int end = len;

        Vector<String> ans = new Vector<>();

        while(true){
            for( ; begin < end ; begin++ ){
                String s = str.substring(begin,end);
                if(trie.search(s) == s.length()) {
                    ans.add(0, s);
                    if(begin == 0)
                        return ans;
                    str = str.substring(0, begin);
                    begin = 0;
                    end = str.length();
                    break;
                }
            }
            if(begin == end){
                //有一个警告即可
                //System.out.println("警告：词典中没有词\"" + str.charAt(end - 1) + "\"！");
                ans.add(0, str.substring(end - 1));
                if(end - 1 == 0)
                    return ans;
                str = str.substring(0, end - 1);
                begin = 0;
                end --;
            }
        }
    }

    public Vector<String> segment(String str){  //双向最大匹配

        Vector<String> ansByFMM = FMM(str);
        Vector<String> ansByBMM = BMM(str);

        if(ansByBMM.size() <= ansByFMM.size())//不同则返回短的那个
            return ansByBMM;
        else
            return ansByFMM;

    }
}
