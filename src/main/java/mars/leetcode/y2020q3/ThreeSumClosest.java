package mars.leetcode.y2020q3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ThreeSumClosest {

    public static void main(String [] args) {
        int [] nums = new int[]{-1,2,1,-4};
        System.out.println(threeSumClosest(nums, 1));
    }

    /*
    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。


    示例：

    输入：nums = [-1,2,1,-4], target = 1
    输出：2
    解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     

    提示：

    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4

     */
    public static int threeSumClosest(int[] nums, int target) {
        List<Integer> sortedNums = new ArrayList<>();
        for (int num : nums) {
            sortedNums.add(num);
        }

        sortedNums.sort(Comparator.comparingInt(a -> a));

        if (sortedNums.size()<3) {
            throw new RuntimeException();
        }

        if (sortedNums.get(0) >= target) {
            return sortedNums.get(0) + sortedNums.get(1) + sortedNums.get(2);
        }

        int closest = Integer.MAX_VALUE;
        int closestSum = Integer.MAX_VALUE;

        int index = 0;
        while (index<sortedNums.size()-2 && sortedNums.get(index) <= target) {
            int l = index + 1;
            int r = sortedNums.size() - 1;
            int indexValue = sortedNums.get(index);

            while (l<r) {
                int currentSum = indexValue + sortedNums.get(l) + sortedNums.get(r);
                int currentClosest = getClosest(currentSum, target);

                if (currentClosest < closest) {
                    closestSum = currentSum;
                    closest = currentClosest;
                }

                if (currentSum > target) {
                    r --;
                } else if(currentSum < target) {
                    l ++;
                } else {
                    return currentSum;
                }
            }

            index++;
        }

        return closestSum;
    }

    private static int getClosest(int a, int b) {
        if (a*b > 0) {
            return Math.abs(Math.abs(a) - Math.abs(b));
        } else if (a*b<0) {
            return Math.abs(a) + Math.abs(b);
        } else {
            return Math.abs(a+b);
        }
    }

}
