package mars.leetcode.arr;

import org.junit.Test;

import java.util.*;

public class BinarySearch {

    @Test
    public void test() {
        int [] input = new int[]{1,2,3,4,5,6,7,8,9};
        search(input, 5);
        System.currentTimeMillis();
    }


    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target > nums[mid]) {
                left = mid+1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
