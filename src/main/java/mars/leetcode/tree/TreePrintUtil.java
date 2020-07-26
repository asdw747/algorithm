package mars.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePrintUtil {

    /*
     *       1
     *     /   \
     *   2      3
     *  / \    / \
     * 4   5  6   7
     */

    /**
     * 先序遍历
     * 每遇到一个节点，先访问，然后再遍历其左右子树
     * 1 2 4 5 3 6 7
     */
    public static void printUsePreOrder(TreeNode rootNode) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        stack.push(rootNode);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            System.out.print(treeNode.getValue() + " ");
            if (treeNode.getRight() != null) {
                stack.push(treeNode.getRight());
            }

            if (treeNode.getLeft() != null) {
                stack.push(treeNode.getLeft());
            }
        }

        System.out.println();
    }

    /**
     * 中序遍历
     * 第一次经过时不访问，等遍历完左子树之后再访问，然后遍历右子树
     * 4 2 5 1 6 3 7
     */
    public static void printUseInOrder(TreeNode rootNode) {

    }

    /**
     * 后序遍历
     * 第一次和第二次经过时都不访问，等遍历完该节点的左右子树之后，最后访问该节点
     * 4 5 2 5 7 3 1
     */
    public static void printUsePostOrder(TreeNode rootNode) {

    }

    /**
     * 树形遍历
     */
    public static void printUseTreeModel(TreeNode rootNode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("├── ").append(rootNode.getValue());
        String prefix = "  ";
        printUseTreeModelInternal(rootNode, stringBuilder, prefix);
        System.out.println(stringBuilder.toString());
    }

    private static void printUseTreeModelInternal(TreeNode treeNode, StringBuilder stringBuilder, String prefix) {
        if (treeNode == null) {
            return;
        }

        stringBuilder.append("\n");
        if (treeNode.getLeft() != null) {
            TreeNode left = treeNode.getLeft();
            stringBuilder.append(prefix).append("├── ").append(left.getValue());
            printUseTreeModelInternal(left, stringBuilder, prefix + "  ");
        }

        if (treeNode.getRight() != null) {
            TreeNode right = treeNode.getRight();
            stringBuilder.append(prefix).append("├── ").append(right.getValue());
            printUseTreeModelInternal(right, stringBuilder, prefix + "  ");
        }
    }

}
