package mars.leetcode.arr;

import org.junit.Test;

public class SortQuick {

    @Test
    public void test() {
        int [] input = new int[]{3,6,2,7,5,3,1,7,10,4};
        sort_quick(input);

        System.currentTimeMillis();
    }

    public void sort_quick(int [] input) {
        if(input == null || input.length<2) {
            return;
        }

        sort_quick_internal(input, 0, input.length-1);
    }

    public void sort_quick_internal(int[] input, int left, int right) {
        System.out.println("print," + left + "," + right);
        if (left>=right) {
            return;
        }

        int base = input[left];
        int i=left, j=right;
        boolean hasLeft = true;
        int rest = left;
        while (i<j) {
            if (hasLeft) {
                while (input[j]>base && i<j) {
                    j--;
                }

                if (i<j) {
                    input[rest] = input[j];
                    rest = j;
                    hasLeft = false;
                }
            } else {
                while (input[i]<=base && i<j) {
                    i++;
                }

                if (i<j) {
                    input[rest] = input[i];
                    rest = i;

                    hasLeft = true;
                }
            }

        }

        input[rest] = base;

        sort_quick_internal(input, left, rest-1);
        sort_quick_internal(input, rest+1, right);

    }
}
