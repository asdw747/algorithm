package mars.leetcode.tree;

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
            System.out.print(treeNode.getVal() + " ");
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
     *
     * 方法：
     *   1.栈依次存入左节点所有点，直到最左侧在栈底
     *   2.开始抛出栈底并访问，(例如第一个抛出4)。如果有右节点，那么打印当前节点后，将右节点加入栈中，然后重复1
     */
    public static void printUseInOrder(TreeNode rootNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(rootNode);
        boolean needInLeft = true;
        while (!stack.empty()) {
            TreeNode currentNode = stack.pop();
            if (needInLeft && currentNode.getLeft() != null) {
                stack.push(currentNode);
                stack.push(currentNode.getLeft());

                continue;
            }

            //左子树到最深了
            needInLeft = false;
            System.out.print(currentNode.getVal() + " ");
            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
                needInLeft = true;
            }
        }

        System.out.println();
    }

    /**
     * 后序遍历
     * 第一次和第二次经过时都不访问，等遍历完该节点的左右子树之后，最后访问该节点
     * 4 5 2 6 7 3 1
     *
     * 1.申请一个栈s1，然后将头节点压入栈s1中。
     * 2.从s1中弹出的节点记为cur，然后依次将cur的左孩子节点和右孩子节点压入s1中。
     * 3.在整个过程中，每一个从s1中弹出的节点都放进s2中。
     * 4.不断重复步骤2和步骤3，直到s1为空，过程停止。
     * 5.从s2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序。
     */
    public static void printUsePostOrder(TreeNode rootNode) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(rootNode);
        while (!stack1.empty()) {
            TreeNode treeNode = stack1.pop();
            stack2.push(treeNode);
            if (treeNode.getLeft() != null) {
                stack1.push(treeNode.getLeft());
            }

            if (treeNode.getRight() != null) {
                stack1.push(treeNode.getRight());
            }
        }

        while (!stack2.empty()) {
            TreeNode treeNode = stack2.pop();
            System.out.print(treeNode.getVal() + " ");
        }

        System.out.println();
    }

    /**
     * 树形遍历
     */
    public static void printUseTreeModel(TreeNode rootNode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("├── ").append(rootNode.getVal());
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
            stringBuilder.append(prefix).append("├── ").append(left.getVal());
            printUseTreeModelInternal(left, stringBuilder, prefix + "  ");
        }

        if (treeNode.getRight() != null) {
            TreeNode right = treeNode.getRight();
            stringBuilder.append(prefix).append("├── ").append(right.getVal());
            printUseTreeModelInternal(right, stringBuilder, prefix + "  ");
        }
    }

}
