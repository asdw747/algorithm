package mars.leetcode.y2020q4;

import org.junit.Test;

public class MaxSubArray {

    /*
    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例:

    输入: [-2,1,-3,4,-1,2,1,-5,4]
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    @Test
    public void test() {
        int result = maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.currentTimeMillis();
    }

    public int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int num:nums) {
            if (num>0) {
                count = Math.max(count, 0);
                count += num;
            } else {
                if (count+num>0) {
                    count = count+num;
                } else {
                    count = num;
                }
            }

            sum = Math.max(sum, count);
        }

        return sum;
    }

}
