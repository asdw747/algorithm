package mars.leetcode.y2020q3;

public class NextPermutation {

    /*
    实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

    如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

    必须原地修改，只允许使用额外常数空间。

    以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1

     */
    public static void main(String [] args) {
        int [] input = new int[]{1,5,1};
        nextPermutation(input);
        System.currentTimeMillis();
    }

    public static void nextPermutation(int[] nums) {
        int risePosition = 0;
        for (int index=1; index<nums.length; index++) {
            if (nums[index] > nums[index-1]) {
                risePosition = index;
            }
        }

        if (risePosition>0) {
            int findPosition = nums.length-1;
            while (findPosition>risePosition && nums[findPosition] <= nums[risePosition-1]) {
                findPosition--;
            }

            swap(nums, risePosition-1, findPosition);
            int left = risePosition;
            int right = nums.length-1;
            while (left < right) {
                int a = nums[left];
                nums[left] = nums[right];
                nums[right] = a;

                left++;
                right--;
            }
        } else {
            int left = 0;
            int right = nums.length-1;
            while (left < right) {
                int a = nums[left];
                nums[left] = nums[right];
                nums[right] = a;

                left++;
                right--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
