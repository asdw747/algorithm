package mars.leetcode.y2020q3;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class LetterCombinations {

    /*
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    示例：
    输入："23"
    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public static void main(String [] args) {
//        System.out.println("abc".substring(2,3));
        letterCombinations("23");
    }


    public static List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        internal(result, new StringBuilder(), digits);

        return result;
    }

    public static void internal(List<String> result, StringBuilder combinationIn, String digits) {
        if (combinationIn.length() >= digits.length()) {
            result.add(combinationIn.toString());
            return;
        }

        String input = digits.substring(combinationIn.length(), combinationIn.length()+1);
        int inputNumber = Integer.parseInt(input);

        Map<Integer, List<Character>> phone = new HashMap<>();
        phone.put(2, Arrays.asList('a','b','c'));
        phone.put(3, Arrays.asList('d','e','f'));
        phone.put(4, Arrays.asList('g','h','i'));
        phone.put(5, Arrays.asList('j','k','l'));
        phone.put(6, Arrays.asList('m','n','o'));
        phone.put(7, Arrays.asList('p','q','r','s'));
        phone.put(8, Arrays.asList('t','u','v'));
        phone.put(9, Arrays.asList('w','x','y','z'));


        for (char letter : phone.get(inputNumber)) {
            StringBuilder combination = combinationIn.append(letter);
            internal(result, combination, digits);
            combination.deleteCharAt(combination.length()-1);
        }
    }


}
