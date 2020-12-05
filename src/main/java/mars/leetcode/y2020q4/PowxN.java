package mars.leetcode.y2020q4;

import org.junit.Test;

public class PowxN {
    /**
     * https://leetcode-cn.com/problems/powx-n/
     *
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 示例 1:
     *
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     *
     */

    @Test
    public void test() {
        double res = getResult(2.00000, -2147483648);
        System.currentTimeMillis();
    }

    /** 快速幂、二分法、递归
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double res = myPow(x, n/2);
        return n%2 == 0 ? res*res : res * res * x;
    }

    public double getResult(double x, int n) {
        return n>0 ? myPow(x, n) : 1/myPow(x, -n);
    }

}
