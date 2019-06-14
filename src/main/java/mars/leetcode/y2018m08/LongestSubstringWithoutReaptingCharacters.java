package mars.leetcode.y2018m08;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringWithoutReaptingCharacters {

    /**
     Given a string, find the length of the longest substring without repeating characters.

     Examples:

     Given "abcabcbb", the answer is "abc", which the length is 3.
     Given "bbbbb", the answer is "b", with the length of 1.
     Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
     "pwke" is a subsequence and not a substring.

     */

    public static void main(String [] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "dvdf";

        System.out.println(lengthOfLongestSubstring(s4));
    }

    private static int lengthOfLongestSubstring(String s) {
        char [] chars = s.toCharArray();
        int max = 0;

        int i = 0;
        List<Character> charQueue = new ArrayList<Character>();
        for (char ignored : chars) {
            //这里简单做一下优化，计算上一个char数值时产生的临时队列先不丢弃，把首字母去掉后作为本char的既有队列
            if (charQueue.size()>0) {
                charQueue.remove(0);
            }

            int count = charQueue.size();

            while ((i+count) < chars.length  && addNoRepeat(charQueue, chars[i+count])) {
                count++;
            }

            max = Math.max(max, count);
            i++;
        }

        return max;
    }

    private static boolean addNoRepeat(List<Character> charArray, char newC) {
        boolean isRepeat = charArray.contains(newC);

        if (!isRepeat)  {
            charArray.add(newC);
        }

        return !isRepeat;
    }

}
