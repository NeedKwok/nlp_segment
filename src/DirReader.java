
import java.io.*;

class DirReader {
    /**
     * 处理文件的读操作
     */
    private static final String dic_ec = "dic_ce.txt" ;

    static Trie readByLine()  {
        File file = new File(dic_ec);
        BufferedReader bfReader = null;
        Trie trie = new Trie();
        try {
            InputStreamReader utf8Reader = new InputStreamReader(new FileInputStream(file));
            bfReader = new BufferedReader(utf8Reader);
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = bfReader.readLine()) != null) {
                String chineseWords = tempString.split(",",2)[0];
                trie.insert(chineseWords);
            }
            bfReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bfReader != null) {
                try {
                    bfReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
        return trie;
    }
}
