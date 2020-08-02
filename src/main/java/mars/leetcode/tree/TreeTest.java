package mars.leetcode.tree;

public class TreeTest {

    public static void main(String [] args) {
//        String treeInput = "50(40(30,),60(55,70))";
//        String treeInput = "50(40,60)";
        String treeInput = "1(2(4,5),3(6,7))";

        TreeNode rootNode = TreeBuilder.generate(treeInput);
        System.currentTimeMillis();

        TreePrintUtil.printUseTreeModel(rootNode);
        TreePrintUtil.printUsePreOrder(rootNode);
        TreePrintUtil.printUseInOrder(rootNode);
        TreePrintUtil.printUsePostOrder(rootNode);
    }

}
