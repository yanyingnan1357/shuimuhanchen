package algorithm.string;

/**
 * 对于一个给定的字符序列S，请你把其循环左移n位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”
 *
 * 思路：局部翻转+整体翻转字符串
 */
public class LeftRotateString {
    public static String leftRotateString(String str, int n) {

        if(str == null || n<=0 || n>=str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        reversal(chars,0,n-1);
        reversal(chars,n,chars.length-1);
        reversal(chars,0,chars.length-1);
//        return Arrays.toString(chars); //输出为:"[X, Y, Z, d, e, f, a, b, c]"
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
        System.out.println(leftRotateString("abcXYZdef", 3));
    }
}
