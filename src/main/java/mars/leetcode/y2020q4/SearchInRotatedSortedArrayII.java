package mars.leetcode.y2020q4;

import org.junit.Test;

public class SearchInRotatedSortedArrayII {

    /**
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
     *
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     *
     */
    @Test
    public void test() {
        int [] nums = new int[] {2,5,6,0,0,1,2};
        boolean res = search(nums, 0);
        System.currentTimeMillis();
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {  //否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;

                }
            }
        }
        //一直没找到，返回false
        return false;
    }

}
