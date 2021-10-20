package mars.leetcode.str;

import org.junit.Test;

public class FindUnExpectedChar {

    @Test
    public void test() {
        char[] input1 = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        char[] input2 = new char[]{'a', 'b', 'c', 'd', 'f', 'g'};
        char res = unExpectedChar(input1, input2);

        System.currentTimeMillis();
    }

    public char unExpectedChar(char[] input1, char[] input2) {
        int leftLength = input1.length;
        int left = 0;
        int right = leftLength - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (input1[mid] == input2[mid]) {
                left = mid + 1;
            } else {
                if ((mid == 0 ||input1[mid-1] == input2[mid-1])) {
                    return input1[mid];
                } else {
                    right = mid - 1;
                }
            }
        }

        return ' ';
    }

}
