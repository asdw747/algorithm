package mars.leetcode.str;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void test() {
        String input = "aba";
        int res = lengthOfLongestSubstring(input);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s == "") {
            return 0;
        }
        int max = 0;

        char [] inputChars = s.toCharArray();
        int left=0,right=0;
        Set<Character> set = new HashSet<>();
        while (right < inputChars.length) {
            if (!set.contains(inputChars[right])) {
                set.add(inputChars[right]);
                max = Math.max(max, set.size());
                right ++;
            } else {
                set.remove(inputChars[left]);
                left ++;
            }
        }

        return max;
    }

}
