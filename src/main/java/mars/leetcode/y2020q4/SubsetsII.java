package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    /**
     *
     */

    @Test
    public void test() {
        int [] nums = new int[]{1,2,2};
        List<List<Integer>> res = subsetsWithDup(nums);
        System.currentTimeMillis();
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

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

        int preValue = Integer.MIN_VALUE;
        for (int i=0; i<input.length; i++) {
            int curValue = input[i];
            if (curValue == preValue) {
                continue;
            }

            List<Integer> newCur = new ArrayList<>(cur);
            newCur.add(input[i]);
            int [] newInput = Arrays.copyOfRange(input, i+1, input.length);
            dfs(result, newInput, newCur);

            preValue = curValue;
        }

    }

}
