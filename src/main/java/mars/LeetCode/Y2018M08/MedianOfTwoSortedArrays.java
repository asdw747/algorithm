package mars.LeetCode.Y2018M08;

import java.util.concurrent.atomic.AtomicInteger;

public class MedianOfTwoSortedArrays {

    /**

     There are two sorted arrays nums1 and nums2 of size m and n respectively.

     Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

     You may assume nums1 and nums2 cannot be both empty.

     Example 1:

     nums1 = [1, 3]
     nums2 = [2]

     The median is 2.0
     Example 2:

     nums1 = [1, 2]
     nums2 = [3, 4]

     The median is (2 + 3)/2 = 2.5

     */

    public static void main(String [] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-1, 3};

        System.out.println(findMedianSortedArrays(nums1, nums2));

    }

    //TODO 算法时间复杂度过大，待优化
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        boolean isEven = totalLength%2 == 0;


      int j = 0, k=0;
        boolean isJ = false;
        while ((j+k)<(totalLength/2)) {
            if ((k>=nums2.length) || ( j<nums1.length && nums1[j] < nums2[k])) {
                j++;
                isJ = true;
            } else {
                k++;
                isJ = false;
            }
        }

        if (isEven) {
            int next;
            if (j>=nums1.length) {
                next = nums2[k];
            } else if (k>=nums2.length) {
                next = nums1[j];
            } else {
                next = nums1[j] < nums2[k] ? nums1[j] : nums2[k];
            }

            if (isJ) {
                return (nums1[j-1] + next) / 2.0;
            } else {
                return (nums2[k-1] + next) / 2.0;
            }
        } else {
            if (j>=nums1.length) {
                return nums2[k];
            }

            if (k>=nums2.length) {
                return nums1[j];
            }

            return nums1[j]<nums2[k] ? nums1[j]:nums2[k];
        }
    }

}
