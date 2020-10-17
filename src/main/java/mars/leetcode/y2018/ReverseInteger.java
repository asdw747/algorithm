package mars.leetcode.y2018;

public class ReverseInteger {

    /**
     Given a 32-bit signed integer, reverse digits of an integer.

     Example 1:

     Input: 123
     Output: 321
     Example 2:

     Input: -123
     Output: -321
     Example 3:

     Input: 120
     Output: 21
     Note:
     Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
     For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */

    public static void main(String [] args) {

        System.out.print(reverse(-2147483648));

    }

//    public static int reverse(int x) {
//        int rev = 0;
//        while (x != 0) {
//            int pop = x % 10;
//            x /= 10;
//            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
//            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
//            rev = rev * 10 + pop;
//        }
//        return rev;
//    }

    private static int reverse(int x) {
        if (x == 0) return 0;
        String inputStr = String.valueOf(x);

        boolean isNegative = false;
        if (inputStr.startsWith("-")) {
            isNegative = true;
        }

        char [] inputChar = isNegative ? inputStr.substring(1).toCharArray():inputStr.toCharArray();

        StringBuilder outputStr = new StringBuilder();
        if (isNegative) outputStr.append("-");
        boolean isInvalidZero = false;
        for (int i=inputChar.length-1; i>=0; i--) {
            if ('0' == inputChar[i] && i==inputChar.length-1) {
                isInvalidZero = true;
                continue;
            } else if ('0' == inputChar[i] && isInvalidZero) {
                continue;
            }

            isInvalidZero = false;
            outputStr.append(inputChar[i]);
        }

        return (Long.parseLong(outputStr.toString())>=Integer.MAX_VALUE || Long.parseLong(outputStr.toString())<=Integer.MIN_VALUE)  ?
                0:Integer.parseInt(outputStr.toString());
    }


}
