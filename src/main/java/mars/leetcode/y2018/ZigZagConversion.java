package mars.leetcode.y2018;

public class ZigZagConversion {

    /**
     zigzag:Z字形布局
     给定一个字符串，转成Z字形布局

     思路：一行一行处理即可，一共n行，每个节点在初始字符串中的位置都可以用n变量来表示

     The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display
     this pattern in a fixed font for better legibility)

     P   A   H   N
     A P L S I I G
     Y   I   R
     And then read line by line: "PAHNAPLSIIGYIR"

     Write the code that will take a string and make this conversion given a number of rows:

     string convert(string s, int numRows);

     Example 1:

     Input: s = "PAYPALISHIRING", numRows = 3
     Output: "PAHNAPLSIIGYIR"
     Example 2:

     Input: s = "PAYPALISHIRING", numRows = 4
     Output: "PINALSIGYAHRPI"
     Explanation:

     P     I    N
     A   L S  I G
     Y A   H R
     P     I

     */


    public static void main(String [] args) {

        System.out.print(generateStr("A", 1));

    }

    private static String generateStr(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] cInput = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<numRows; i++) {
            int countInLine = i;
            boolean order = true;
            while (countInLine < s.length()) {
                sb.append(cInput[countInLine]);

                if (i==0 || i==(numRows-1)) {
                    countInLine += (numRows-1)*2;
                    continue;
                }

                if (order) {
                    countInLine += (numRows-i-1)*2;
                    order = false;
                } else {
                    countInLine += (i)*2;
                    order = true;
                }
            }
        }

        return sb.toString();
    }

}
