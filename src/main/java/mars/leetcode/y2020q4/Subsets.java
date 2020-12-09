package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    /**
     * https://leetcode-cn.com/problems/subsets/
     */
    @Test
    public void test() {
        int [] nums = new int[]{1,2,3};
        List<List<Integer>> res = subsets(nums);
        System.currentTimeMillis();
    }


    /*
    给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

    说明：解集不能包含重复的子集。

    示例:

    输入: nums = [1,2,3]
    输出:
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]

     */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        dfs(res, nums, new ArrayList<>());
        return res;
    }

    private void dfs(List<List<Integer>> result, int[]input, List<Integer>cur) {
        if (cur.size()>0) {
            result.add(cur);
        }

        if (input.length<=0) {
            return;
        }

        for (int i=0; i<input.length; i++) {
            List<Integer> newCur = new ArrayList<>(cur);
            newCur.add(input[i]);
            int [] newInput = Arrays.copyOfRange(input, i+1, input.length);
            dfs(result, newInput, newCur);
        }

    }


}
