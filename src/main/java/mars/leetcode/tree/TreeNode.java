package mars.leetcode.tree;

import lombok.Data;

@Data
public class TreeNode {

    private TreeNode left;
    private TreeNode right;

    private int value;

    public TreeNode() {

    }

    public TreeNode(int value) {
        this.value = value;
    }

    public static TreeNode init(int value) {
        if (value<=0) {
            return null;
        }

        return new TreeNode(value);
    }

}
