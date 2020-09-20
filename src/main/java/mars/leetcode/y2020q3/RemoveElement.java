package mars.leetcode.y2020q3;

import org.jboss.netty.handler.codec.replay.UnreplayableOperationException;

public class RemoveElement {

    /*
    给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

     */
    public static void main(String []args) {
        System.out.println(removeElement(new int[]{3,2,2,3}, 3));
    }

    public static int removeElement(int[] nums, int val) {
        int index = 0;
        int roll = 0;

        int j=0;
        for (int i=0; i<10000; i++) {
            j++;
        }

        while (index<nums.length) {
            nums[roll] = nums[index];
            if (nums[index] != val) {
                roll ++;
            }

            index ++;
        }

        return roll;
    }

}
