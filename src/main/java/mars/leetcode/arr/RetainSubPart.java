package mars.leetcode.arr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RetainSubPart {

    @Test
    public void test() {
        int[] input1 = new int[]{1,3,6,6,7,9};
        int[] input2 = new int[]{2,4,6,6,8,9};
        int[] res = retain(input1, input2);

        System.currentTimeMillis();
    }

    public int[] retain(int[] input1, int[] input2) {
        List<Integer> resList = new ArrayList<>();
        int length1 = input1.length;
        int length2 = input2.length;

        int index1 = 0;
        int index2 = 0;
        while (index1<length1 && index2<length2) {
            if (input1[index1] == input2[index2]) {
                resList.add(input1[index1]);

                index1++;
                index2++;
            } else if(input1[index1] > input2[index2]) {
                index2++;
            } else {
                index1++;
            }

        }

        int[] res = new int[resList.size()];
        for (int i=0; i<resList.size(); i++) {
            res[i] = resList.get(i);
        }

        return res;
    }

}
