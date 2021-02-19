package algorithm.string;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
 */
public class FirstNotRepeatingChar {

    public static int firstNotRepeatPosition(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        HashMap<Character, Integer> lhm = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (lhm.containsKey(str.charAt(i))) {
                int time = lhm.get(str.charAt(i));
                lhm.put(str.charAt(i), ++time);
            } else {
                lhm.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (lhm.get(str.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static char firstNoRepeatChar(String str) {
        if (str == null || str.length() == 0) {
            return ' ';
        }

        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i=0; i<str.length(); i++) {
            char tmp = str.charAt(i);
            if (map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp) + 1);
            } else {
                map.put(tmp, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstNotRepeatPosition("google"));
        System.out.println(firstNoRepeatChar("google"));
    }
}
