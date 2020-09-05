package mars.leetcode.y2020q3;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;

public class FindAbaStr {

    /*

    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

    示例 1：

    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。


    示例 2：

    输入: "cbbd"
    输出: "bb"

     */
    public static void main(String[] args) {
        System.out.println(5/2);
//        System.out.println(longestPalindrome(""));
    }

    /*
    1.中心计算法
     */
    public static String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }

        char [] chars = s.toCharArray();
        int length = chars.length;

        int start = 0;
        int end = 0;

        int max = 1;
        for (int i=0; i<length; i++) {
            int value1 = expandAroundCenter(s, i, i);
            int value2 = expandAroundCenter(s, i, i+1);
            int maxValue = Math.max(value1, value2);

            if (maxValue>=max) {
                start = i - (maxValue - 1) / 2;
                end = i + maxValue / 2;

                max = end - start + 1;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    private static boolean isPalindrome(String input) {
        char [] chars = input.toCharArray();
        int begin = 0;
        int end = input.length()-1;
        while (begin<=end && chars[begin] == chars[end]) {
            if (begin == end || end == begin+1) {
                return true;
            }

            begin++;
            end--;
        }

        return false;
    }

    /*
   2.动态规划算法
    */
    public static String longestPalindrome1(String s) {
        char [] chars = s.toCharArray();
        int length = chars.length;

        boolean[][] dp = new boolean[length][length];
        String result = "";

        for (int j=0; j<length; j++) {
            for (int i=0; i<=j; i++) {
                if (i==j) {
                    dp[i][j] = true;
                } else if (chars[i] == chars[j]) {
                    if (j-i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j] && (j-i)>=result.length()) {
                    result = s.substring(i,j+1);
                }
            }
        }

        return result;
    }

}

