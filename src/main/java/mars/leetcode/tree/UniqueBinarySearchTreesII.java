package mars.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {

    /**
     * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/bu-tong-de-er-cha-sou-suo-shu-ii-by-leetcode-solut/
     */
    @Test
    public void test() {
        List<TreeNode> res = generateTrees(5);
        System.currentTimeMillis();
    }

    public List<TreeNode> generateTrees(int n) {
        if (n<=0) {
            return new ArrayList<>();
        }

        List<TreeNode> res = generateInternal(1, n);
        return res;
    }

    private List<TreeNode> generateInternal(int start, int end) {
        List<TreeNode> all = new ArrayList<>();
        if (start > end) {
            all.add(null);
            return all;
        }

        for (int i=start; i<=end; i++) {
            List<TreeNode> leftAll = generateInternal(start, i-1);
            List<TreeNode> rightAll = generateInternal(i+1, end);

            for (TreeNode leftItem : leftAll) {
                for (TreeNode rightItem : rightAll) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.val = i;
                    treeNode.left = leftItem;
                    treeNode.right = rightItem;
                    all.add(treeNode);
                }
            }
        }

        return all;
    }


}
