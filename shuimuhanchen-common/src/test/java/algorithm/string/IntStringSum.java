package algorithm.string;

/**
 * 大数相加
 */
public class IntStringSum {

    private static String solve(String s, String t) {

        StringBuilder result = new StringBuilder();
        int i = s.length() - 1, j = t.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i < 0 ? 0 : s.charAt(i--) - '0';
            int y = j < 0 ? 0 : t.charAt(j--) - '0';
            int sum = x + y + carry;
            result.append(sum % 10);//添加到字符串尾部
            carry = sum / 10;
        }

        return result.reverse().toString();//对字符串反转
    }

    public static void main(String[] args) {
        System.out.println(solve("123", "45"));
    }
}
