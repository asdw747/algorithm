package mars.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SumRootRoLeafNumbers {

    /**
     * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
     */

    @Test
    public void test() {
        TreeNode rootNode = TreeBuilder.generate("4(9(5,1),0)");

        int res = sumNumbers(rootNode);

        System.currentTimeMillis();
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<List<Integer>> output = new ArrayList<>();
        dfs(output, new ArrayList<>(), root);

        int sum=0;
        for (List<Integer> item:output) {
            int curSum=0;
            for (int i=item.size()-1; i>=0; i--) {
                curSum += item.get(item.size()-1-i) * Math.pow(10, i);
            }
            sum += curSum;
        }

        return sum;
    }

    public void dfs(List<List<Integer>> output, List<Integer> cur, TreeNode curNode) {
        cur.add(curNode.val);
        if (curNode.left == null && curNode.right == null) {
            output.add(new ArrayList<>(cur));
            cur.remove(cur.size()-1);
            return;
        }

        if (curNode.left != null) {
            dfs(output, cur, curNode.left);
        }

        if (curNode.right != null) {
            dfs(output, cur, curNode.right);
        }

        cur.remove(cur.size()-1);
    }

}
