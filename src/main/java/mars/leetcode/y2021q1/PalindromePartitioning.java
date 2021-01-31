package mars.leetcode.y2021q1;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.util.*;

public class PalindromePartitioning {

    /**
     * https://leetcode-cn.com/problems/palindrome-partitioning/
     */
    @Test
    public void test() {
        List<List<String>> res = partition("aab");
        System.currentTimeMillis();
    }

    public List<List<String>> partition(String s) {
        Map<String, Boolean> map = new HashMap<>();
        generateAll(s, map);

        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), s, 0, map);

        return res;
    }

    public void generateAll(String input, Map<String, Boolean> map) {
        char [] inputChars = input.toCharArray();
        for (int i=0; i<inputChars.length; i++) {
            map.put(i + "_" + i, true);
        }

        for (int end=0; end<inputChars.length; end++) {
            for (int begin=0; begin<end; begin++) {
                boolean isRoll = false;
                if (end-begin==1) {
                    isRoll = inputChars[end] == inputChars[begin];
                } else {
                    isRoll = map.get((begin + 1) + "_" + (end - 1)) &&
                            (inputChars[end] == inputChars[begin]);
                }
                map.put(begin + "_" + end, isRoll);
            }
        }
    }

    public void dfs(List<List<String>> res, List<String> cur, String input, int offset, Map<String, Boolean> map) {
        if (offset == input.length()) {
            res.add(cur);
            return;
        }

        char [] inputChars = input.toCharArray();
        for (int i=offset; i<inputChars.length; i++) {
            boolean isRoll = map.get(offset + "_" + i);
            if (isRoll) {
                List<String> newCur = new ArrayList<>(cur);
                newCur.add(input.substring(offset, i+1));

                dfs(res, newCur, input, i+1, map);
            }
        }
    }

}
