package mars.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateBinarySearchTree {

    /**
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     */

    @Test
    public void test() {
        String treeInput = "3(1(0,2),5(4,6))";

        TreeNode rootNode = TreeBuilder.generate(treeInput);
        boolean res = isValidBST(rootNode);

        System.currentTimeMillis();
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<Integer> leftIntOfL = new ArrayList<>();
        leftIntOfL.add(root.val);
        List<Integer> rightIntOfR = new ArrayList<>();
        rightIntOfR.add(root.val);
        return validInternal(root.left, leftIntOfL, new ArrayList<>()) & validInternal(root.right, new ArrayList<>(), rightIntOfR);
    }

    public boolean validInternal(TreeNode node, List<Integer> left, List<Integer> right) {
        if (node == null) {
            return true;
        }

        if(node.left!=null && node.left.val >= node.val){
            return false;
        }

        if (node.right!=null && node.right.val <= node.val) {
            return false;
        }

        for (Integer lItem : left) {
            if (node.val>=lItem) {
                return false;
            }
        }

        for (Integer rItem : right) {
            if (node.val<=rItem) {
                return false;
            }
        }

        List<Integer> newLeftOfL = new ArrayList<>(left);
        List<Integer> newRightOfL = new ArrayList<>(right);
        newLeftOfL.add(node.val);

        List<Integer> newLeftOfR = new ArrayList<>(left);
        List<Integer> newRightOfR = new ArrayList<>(right);
        newRightOfR.add(node.val);

        return validInternal(node.left, newLeftOfL, newRightOfL) & validInternal(node.right, newLeftOfR, newRightOfR);
    }

}
