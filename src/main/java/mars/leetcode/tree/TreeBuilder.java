package mars.leetcode.tree;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.List;

public class TreeBuilder {

    public static String ORIGIN_CONTENT = "[50[40[30,45[44,46]],60[,70]]]";

    public static List<Character> specialChar = Arrays.asList('[', ']', ',', ' ');

    public static TreeNode generate() {
        TreeNode node = new TreeNode(50);

        internal(node, 3);
        return node;
    }


    public static void internal(TreeNode node, int offset) {
        char[] content = ORIGIN_CONTENT.toCharArray();

        int score = 0;
        String currentValueStr = "";
        int leftNodeValue = -1;
        int leftOffset = 0;
        int rightNodeValue = -1;
        int rightOffset = 0;
        boolean half = false;
        for (int i=offset; i<content.length; i++) {
            //计算当前score
            if (content[i] == '[') {
                score ++;
            } else if (content[i] == ']') {
                score --;
            }

            if (score != 1) {
                if (score == 2 && content[i] == '[' && !half) {
                    leftOffset = i;
                    continue;
                }

                if (score == 2 && content[i] == '[' && half) {
                    rightOffset = i;
                    continue;
                }

                continue;
            }

            if (content[i] == ',') {
                half = true;
            }

            int currentValue = -1;
            if (specialChar.contains(content[i])) {
                //遇到特殊字符，生成currentValue
                if (StringUtils.isNotBlank(currentValueStr)) {
                    currentValue = NumberUtils.toInt(currentValueStr);
                    currentValueStr = "";
                }
            } else {
                currentValueStr += content[i];
                continue;
            }


            if (leftNodeValue == -1) {
                leftNodeValue = currentValue;
            } else if (content[i]!=',') {
                rightNodeValue = currentValue;
            }

        }

        TreeNode leftNode = new TreeNode(leftNodeValue);
        TreeNode rightNode = new TreeNode(rightNodeValue);
        node.setLeft(leftNode);
        node.setRight(rightNode);

        if (leftOffset != 0) {
            internal(leftNode, leftOffset);
        }

        if (rightOffset != 0) {
            internal(rightNode, rightOffset);
        }

    }

}
