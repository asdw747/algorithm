package mars.leetcode.y2019;

import org.apache.commons.lang.math.NumberUtils;

public class DivideTwoIntegers {

    /**
     Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

     Return the quotient after dividing dividend by divisor.

     The integer division should truncate toward zero.

     Example 1:
     Input: dividend = 10, divisor = 3
     Output: 3

     Example 2:
     Input: dividend = 7, divisor = -3
     Output: -2

     Note:
     Both dividend and divisor will be 32-bit signed integers.
     The divisor will never be 0.
     Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
     For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

     */

    /**
        用移位来实现除法操作，边界值比较恶心
     */
    public static void main(String [] args) {
        System.out.println(divide(-2147483648, 2));
    }


    //error
    private static int divideBack(int dividend, int divisor) {
        boolean isPositive = (dividend>0&&divisor>0) ||
                (dividend<0 && divisor<0);

        int absoluteDividend;
        if (dividend == -2147483648) {
            absoluteDividend = isPositive ? 2147483647:-2147483648;
        } else {
            absoluteDividend = dividend>0 ? dividend:-dividend;
        }
        int absoluteDivisor = divisor>0 ? divisor:-divisor;

        if (absoluteDivisor == 1) {
            return isPositive ? absoluteDividend:-absoluteDividend;
        }

        int counter = 0;
        int sum = absoluteDivisor;
        while (sum<=absoluteDividend) {
            counter++;
            sum += absoluteDivisor;
        }

        return isPositive ? counter:-counter;
    }

    private static int divide(int dividend, int divisor) {
        boolean isPositive = (dividend>0&&divisor>0) ||
                (dividend<0 && divisor<0);

        long absoluteDividend;
        if (dividend == -2147483648) {
            absoluteDividend = 2147483648L;
        } else {
            absoluteDividend = dividend>0 ? dividend:-dividend;
        }

        long absoluteDivisor;
        if (divisor == -2147483648) {
            absoluteDivisor = 2147483648L;
        } else {
            absoluteDivisor = divisor>0 ? divisor:-divisor;
        }

        long counterBase = 0;
        long counter = 0;
        long sum = absoluteDivisor;
        while (sum<=absoluteDividend) {
            if (counter == 0) {
                counter ++;
            } else {
                counter = counter + counter;
            }

            long sumBack = sum;
            if (sumBack >= absoluteDividend && sumBack<=(absoluteDividend + absoluteDividend)) {
                counterBase = counterBase + counter;
                break;
            }

            if (sum>=Integer.MAX_VALUE / 2) {
                counterBase = counterBase + counter;

                absoluteDividend = absoluteDividend - sumBack;
                counter = 0;
                sum = absoluteDivisor;
            } else {
                sum = sum<<1;
                if (sum>absoluteDividend) {
                    counterBase = counterBase + counter;

                    absoluteDividend = absoluteDividend - sumBack;
                    counter = 0;
                    sum = absoluteDivisor;
                }
            }
        }

        if (isPositive && counterBase>Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return Integer.parseInt(String.valueOf(isPositive? counterBase:-counterBase));
        }
    }

}
