package algorithm.string;

/**
 * 用 %20 替换空格 尽量不开辟新的空间
 */
public class ReplaceSpace {
    public static String replaceSpace(StringBuilder str) {
        int count = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)==' ')
                count++;
        }
        int newLen = 2*count + str.length();
        int oldLen = str.length();
        str.setLength(newLen);
        for(int i=oldLen-1, j=newLen-1; i>=0; i--){
            if(str.charAt(i) == ' ') {
                str.setCharAt(j--, '0');
                str.setCharAt(j--, '2');
                str.setCharAt(j--, '%');
            } else {
                str.setCharAt(j--, str.charAt(i));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuilder("你 好 啊")));
    }
}
