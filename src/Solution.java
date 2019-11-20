import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args){
        String str;
        Segmenter sementer;
        sementer = new Segmenter(DirReader.readByLine());
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("请输入句子（回车确定,不能出现非汉字,-exit退出）：");
            str = sc.nextLine();
            if(str.length() < 1)
                continue;
            Pattern p = Pattern.compile("[^\\u4e00-\\u9fa5]"); //匹配非汉字
            if (p.matcher(str).find()){
                if(str.equals("-exit"))
                    break;
                else
                    System.out.println("输入中不能包括非汉字！");
            }else{
                Vector<String> ans = sementer.segment(str);
                StringBuilder stringBuilder = new StringBuilder();
                for(String s:ans){
                    stringBuilder.append(s);
                    stringBuilder.append(" ");
                }
                System.out.println(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
            }
        }
    }
}
