package algorithm.string;

/**
 * “student. a am I” -> “I am a student.”
 * 思路：局部翻转+整体翻转字符串
 */
public class ReverseSentence {
    public static String reverseSentence(String str) {

        if(str == null || str.trim().length()==0) {
            return str;
        }
        char[] chars = str.toCharArray();
        for (int i=0,j=0; j<chars.length; j++) {

            if(' ' == chars[j]) {
                reversal(chars, i, j-1);
                i = j+1;
            }
        }
        reversal(chars, 0, chars.length-1);
        return String.valueOf(chars);
    }

    private static void reversal(char[] chars, int start, int end) {
        char tmp;
        int i = start;
        int j = end;
        while (i < j) {
            tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args){
        System.out.println(reverseSentence("student. a am I"));
    }
}
