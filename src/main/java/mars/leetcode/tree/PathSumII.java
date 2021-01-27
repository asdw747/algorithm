package mars.leetcode.tree;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {

    /**
     * https://leetcode-cn.com/problems/path-sum-ii/
     */
    @Test
    public void test() {
        TreeNode rootNode = TreeBuilder.generate("5(4(11(7,2),),8(13,4(5,1)))");

        List<List<Integer>> result = pathSum(rootNode, 22);

        System.currentTimeMillis();
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        dfs(root, new ArrayList<>(), 0, sum, result);
        return result;
    }

    private void dfs(TreeNode curNode, List<Integer> curSumList, int curSum, int sum, List<List<Integer>> result) {
        curSumList.add(curNode.val);
        curSum += curNode.val;

        if (curSum == sum) {
            if (curNode.left == null && curNode.right == null) {
                result.add(new ArrayList(curSumList));

                return;
            }
        }

        if (curNode.left != null) {
            dfs(curNode.left, curSumList, curSum, sum, result);
            curSumList.remove(curSumList.size()-1);
        }

        if (curNode.right!=null) {
            dfs(curNode.right, curSumList, curSum, sum, result);
            curSumList.remove(curSumList.size()-1);
        }
    }


}
