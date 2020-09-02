package mars.leetcode.y2020q3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class NoRepeatStr {

    /*
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    示例 1:

    输入: "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

    示例 2:

    输入: "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

    示例 3:

    输入: "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

     */
    public static void main(String [] args) {
        System.out.println(lengthOfLongestSubstringNew("abcabcbb"));
    }

    /*
    比较low的解法，最好用双指针滑动窗口来做
     */
    public static int lengthOfLongestSubstring(String s) {
        char [] chars = s.toCharArray();
        int maxDiffLength = 0;

        int i = 0;
        while (i<chars.length) {
            Map<Character, Integer> diffCharsAndIndex = new HashMap<>();
            int j = i;

            Integer preCharIndex = null;
            while (j < chars.length) {
                preCharIndex = diffCharsAndIndex.putIfAbsent(chars[j], j);
                if (preCharIndex == null) {
                    j ++;
                } else {
                    break;
                }
            }

            i = preCharIndex == null ? i:preCharIndex;
            int currentDiffLength = diffCharsAndIndex.size();
            maxDiffLength = Math.max(currentDiffLength, maxDiffLength);

            i++;
        }

        return maxDiffLength;
    }

    /*
    双指针滑动窗口解法
     */
    public static int lengthOfLongestSubstringNew(String s) {
        char [] chars = s.toCharArray();
        int maxDiffLength = 0;

        int begin = 0 ;
        int end = 0 ;

        Map<Character, Integer> diffCharsAndIndex = new HashMap<>();
        while (end < chars.length) {
            Integer preCharIndex = diffCharsAndIndex.put(chars[end], end);
            if (preCharIndex != null && preCharIndex>=begin) {
                int currentDiffLength = end - begin;
                maxDiffLength = Math.max(currentDiffLength, maxDiffLength);

                begin = preCharIndex + 1;
            }

            end ++;
        }

        int lastLength = end - begin;
        return Math.max(maxDiffLength, lastLength);
    }

}
