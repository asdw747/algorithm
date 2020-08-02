package mars.leetcode.tree;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TreeBuilder {

    private static List<Character> specialChar = Arrays.asList('(', ')', ',', ' ');

    private static ThreadLocal<String> localInput = new ThreadLocal<>();

    public static TreeNode generate(String input) {
        localInput.set(input);
        String originInput = localInput.get();
        validate(originInput);

        TreeNode node = getRootNode(originInput);
        System.out.println("print node:" + node.toString());

        generateInternal(node, originInput.toCharArray());
        System.out.println("generate complete tree ok.");
        return node;
    }

    private static void validate(String originInput) {
        if(originInput == null)
            throw new RuntimeException("no valid input");

        Pattern pattern = Pattern.compile("^\\d+(\\d|[(]|[)]|,)+");
        if(!pattern.matcher(originInput).matches()) {
            throw new RuntimeException("no valid input");
        }
    }

    private static TreeNode getRootNode(String originInput) {
        char[] content = originInput.toCharArray();
        int rootValue;
        StringBuilder currentValueStr = new StringBuilder();

        int index = 0;
        while (index<content.length && !specialChar.contains(content[index])) {
            currentValueStr.append(content[index]);
            index ++;
        }

        rootValue = NumberUtils.toInt(currentValueStr.toString());
        return new TreeNode(rootValue);
    }

    /**
     * input: 50(40(30,),60(55,70))
     * generated value: 40  60
     * generated input: 40(30,)  60(55,70)
     */
    private static void generateInternal(TreeNode node, char[] itemInput) {
        if (itemInput.length<=0) {
            return;
        }

        int start = 0;
        int end = 0;

        int score = 0;
        boolean half = false;

        StringBuilder currentStr = new StringBuilder();
        for (int i=0; i<itemInput.length; i++) {
            if (itemInput[i] == '(') {
                score ++;
                if (score == 1) {
                    start = i+1;
                }
            }

            if (itemInput[i] == ')') {
                score --;
            }

            if (score == 1 && !specialChar.contains(itemInput[i])) {
                currentStr.append(itemInput[i]);
            }

            if (score==1 && itemInput[i] == ',') {
                //遇到本层级的','，进行左子树递归
                half = true;
                end = i;

                if (StringUtils.isNotBlank(currentStr)) {
                    int currentValue = NumberUtils.toInt(currentStr.toString());
                    TreeNode leftNode = new TreeNode(currentValue);
                    node.setLeft(leftNode);
                    char[] leftItemInput = ArrayUtils.subarray(itemInput, start, end);
                    generateInternal(leftNode, leftItemInput);
                }

                start = i+1;
                currentStr = new StringBuilder();
            }

            if (score==0 && half) {
                //本层级遍历完毕，进行右子树递归
                end = i;

                if (StringUtils.isNotBlank(currentStr)) {
                    int currentValue = NumberUtils.toInt(currentStr.toString());
                    TreeNode rightNode = new TreeNode(currentValue);
                    node.setRight(rightNode);
                    char[] rightItemInput = ArrayUtils.subarray(itemInput, start, end);
                    generateInternal(rightNode, rightItemInput);
                }

            }
        }
    }

}
