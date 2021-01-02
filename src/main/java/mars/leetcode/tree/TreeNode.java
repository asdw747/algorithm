package mars.leetcode.tree;

import lombok.Data;

@Data
public class TreeNode {

    public TreeNode left;
    public TreeNode right;

    public int val;

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
