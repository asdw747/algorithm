package mars.leetcode.y2020q4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    /*
    39.组合总和
    给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    candidates 中的数字可以无限制重复被选取。

    说明：

    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合。 
    示例 1：

    输入：candidates = [2,3,6,7], target = 7,
    所求解集为：
    [
      [7],
      [2,2,3]
    ]
    示例 2：

    输入：candidates = [2,3,5], target = 8,
    所求解集为：
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]
     

    提示：

    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    candidate 中的每个元素都是独一无二的。
    1 <= target <= 500

     */
    public static void main(String [] args) {
        List<List<Integer>> result = combinationSum(new int[]{2,3,6,7}, 7);
        System.currentTimeMillis();
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> input = new ArrayList<>();
        for (int item : candidates) {
            input.add(item);
        }

        get(input, 0, target, null, result);

        return result;
    }

    public static void get(List<Integer> candidates, int index, int currentSum, List<Integer> curList, List<List<Integer>> allList) {
        if (currentSum<=0) {
            if (currentSum == 0) {
                allList.add(curList);
            }

            return;
        }

        for (int i=0; i<candidates.size(); i++) {
            int item = candidates.get(i);

            List<Integer> curListInput;
            if (index == 0) {
                curListInput = new ArrayList<>();
            } else{
                curListInput = new ArrayList<>(curList);
            }
            curListInput.add(item);

            List<Integer> candidatesCopy = candidates.subList(i, candidates.size());

            get(candidatesCopy, index+1, currentSum - item, curListInput, allList);
        }
    }

}
