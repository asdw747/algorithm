package mars.leetcode.y2018m09;

public class StringToInteger {

    /**
     Implement atoi which converts a string to an integer.

     The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

     The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

     If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

     If no valid conversion could be performed, a zero value is returned.

     Note:

     Only the space character ' ' is considered as whitespace character.
     Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
     Example 1:

     Input: "42"
     Output: 42
     Example 2:

     Input: "   -42"
     Output: -42
     Explanation: The first non-whitespace character is '-', which is the minus sign.
     Then take as many numerical digits as possible, which gets 42.
     Example 3:

     Input: "4193 with words"
     Output: 4193
     Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
     Example 4:

     Input: "words and 987"
     Output: 0
     Explanation: The first non-whitespace character is 'w', which is not a numerical
     digit or a +/- sign. Therefore no valid conversion could be performed.
     Example 5:

     Input: "-91283472332"
     Output: -2147483648
     Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
     Thefore INT_MIN (−231) is returned.
     */

    public static void main(String [] args) {
        System.out.println(getNum("2147483648"));
    }


    private static int getNum(String input) {
        char []inputChar = input.toCharArray();
        StringBuilder numStr = new StringBuilder();
        if (inputChar.length <= 0) return 0;

        boolean isStart = false;
        int counter = 0;
        for (char c : inputChar) {
            if (counter == 0) {
                if (c=='0') {
                    isStart = true;
                    continue;
                }

                if (c>='1' && c<='9') {
                    counter++;
                    numStr.append(c);
                    continue;
                }

                if (!isStart && (c == '-' || c == '+')) {
                    isStart = true;
                    numStr.append(c);
                    continue;
                }

                if (!isStart && c == ' ') {
                    continue;
                }

                break;
            } else {
                if (c>='0' && c<='9') {
                    counter++;
                    numStr.append(c);
                    continue;
                }

                break;
            }

        }

        if (counter == 0 || (counter==1 &&
                ("-".equals(numStr.toString()) || "+".equals(numStr.toString())))) {
            return 0;
        }

        if (counter > 10) {
            return numStr.toString().startsWith("-") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        Long resLong = Long.parseLong(numStr.toString());
        if (resLong > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (resLong < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return resLong.intValue();
    }


}
