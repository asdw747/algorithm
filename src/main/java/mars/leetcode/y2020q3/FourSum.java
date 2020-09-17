package mars.leetcode.y2020q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FourSum {

    /*
    给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

    注意：

    答案中不可以包含重复的四元组。

    示例：

    给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

    满足要求的四元组集合为：
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]

    [-3,-2,-1,0,0,1,2,3]

     */
    public static void main(String [] args) {
        System.out.println(fourSum(new int[]{1,-2,-5,-4,-3,3,3,5}, -11));
    }

    //TODO 重复元素待去除
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<Integer> orderedList = new ArrayList<>();
        for (int num : nums) {
            orderedList.add(num);
        }
        orderedList.sort(Comparator.comparingInt(a -> a));
        if (orderedList.size()<4) {
            return new ArrayList<>();
        }

        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        int aIndex = 0;
        while (aIndex<length-3) {
            if (aIndex>0 && orderedList.get(aIndex).equals(orderedList.get(aIndex-1))) {
                aIndex ++;
                continue;
            }

            int bIndex = aIndex+1;
            while (bIndex<length-2) {
                if (bIndex>aIndex+1 && orderedList.get(bIndex).equals(orderedList.get(bIndex-1))) {
                    bIndex ++;
                    continue;
                }

                int cIndex = bIndex+1;
                int dIndex = length-1;

                int prevC = Integer.MAX_VALUE;
                int prevD = Integer.MAX_VALUE;

                while (cIndex<dIndex) {
                    int sum = orderedList.get(aIndex) + orderedList.get(bIndex) + orderedList.get(cIndex) + orderedList.get(dIndex);
                    if (sum>target) {
                        dIndex--;
                    } else if (sum<target) {
                        cIndex++;
                    } else {
                        if (orderedList.get(cIndex) == prevC && orderedList.get(dIndex) == prevD) {

                        } else {
                            result.add(Arrays.asList(orderedList.get(aIndex), orderedList.get(bIndex), orderedList.get(cIndex), orderedList.get(dIndex)));
                            prevC = orderedList.get(cIndex);
                            prevD = orderedList.get(dIndex);
                        }

                        cIndex++;
                        dIndex--;
                    }
                }

                bIndex++;
            }

            aIndex++;
        }

        return result;
    }

}
