package mars.leetcode.tree;

import lombok.Data;

@Data
public class TreeNode {

    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode init(int value) {
        if (value<=0) {
            return null;
        }

        return new TreeNode(value);
    }

}
