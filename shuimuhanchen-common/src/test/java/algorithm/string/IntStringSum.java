package algorithm.string;

/**
 * 大数相加
 */
public class IntStringSum {

    private static String IntStringSum(String str1, String str2) {

        StringBuilder res = new StringBuilder();

        int i = str1.length() - 1;
        int j = str2.length() - 1;
        int cnt = 0;

        while (i>=0 || j>=0 || cnt>0) {
            int x = i < 0 ? 0 : str1.charAt(i--) - '0';
            int y = j < 0 ? 0 : str2.charAt(j--) - '0';
            int sum = x + y + cnt;
            res.append(sum % 10);
            cnt = sum / 10;
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(IntStringSum("123", "45"));
    }
}
