package mars.leetcode.y2020q3;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        int [] nums = new int[]{-2, 0, 0, 2, 2};
        List<List<Integer>> result = threeSum(nums);

        for (List<Integer> list : result) {
            for (Integer num : list) {
                System.out.print(num);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    /*
    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

    注意：答案中不可以包含重复的三元组。

     

    示例：

    给定数组 nums = [-1, 0, 1, 2, -1, -4]，

    满足要求的三元组集合为：
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]

     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<Integer> orderedList = new ArrayList<>();
        for (int num : nums) {
            orderedList.add(num);
        }
        orderedList.sort(Comparator.comparingInt(a -> a));
        if (orderedList.size()<3 || orderedList.get(0)>0 || orderedList.get(orderedList.size()-1)<0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        int prevIndexValue = -1;
        int prevLValue = -1;
        int prevRValue = -1;

        int index = 0;
        while (index<orderedList.size()-2 && orderedList.get(index)<=0) {
            if (index>0 && orderedList.get(index).equals(orderedList.get(index-1))) {
                index ++;
                continue;
            }

            int l = index + 1;
            int r = orderedList.size() - 1;

            while (l<r) {

                if (orderedList.get(index) + orderedList.get(l) + orderedList.get(r) > 0) {
                    r--;
                } else if (orderedList.get(index) + orderedList.get(l) + orderedList.get(r) < 0) {
                    l++;
                } else {
                    int indexValue = orderedList.get(index);
                    int lValue = orderedList.get(l);
                    int rValue = orderedList.get(r);

                    if (prevIndexValue == indexValue &&
                            prevLValue == lValue &&
                            prevRValue == rValue) {

                    } else {
                        result.add(Arrays.asList(indexValue, lValue, rValue));
                    }

                    prevIndexValue = indexValue;
                    prevLValue = lValue;
                    prevRValue = rValue;

                    r--;
                    l++;
                }
            }

            index++;
        }

        return result;
    }

}
