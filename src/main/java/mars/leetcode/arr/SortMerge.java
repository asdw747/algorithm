package mars.leetcode.arr;

import java.util.Arrays;

public class SortMerge {

    @org.junit.Test
    public void test() {
        int [] input = new int[]{3,6,2,7,5,3,1,7,10,4};
        int[] res = sort_merge(input);

        System.currentTimeMillis();
    }

    public int[] sort_merge(int [] input) {
        if (input.length<2) {
            return input;
        }
        int middle = input.length / 2;
        int[] left = Arrays.copyOfRange(input, 0, middle);
        int[] right = Arrays.copyOfRange(input, middle, input.length);
        return merge(sort_merge(left), sort_merge(right));
    }

    public int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int l=0,r=0;
        while (l<left.length && r<right.length) {
            if (left[l] < right[r]) {
                res[l+r] = left[l];
                l++;
            } else if (left[l] >= right[r]) {
                res[l+r] = right[r];
                r++;
            }
        }

        if (l<left.length) {
            for (int i=l; i<left.length; i++) {
                res[r+i] = left[i];
            }
        }

        if (r<right.length) {
            for (int i=r; i<right.length; i++) {
                res[l+i] = right[i];
            }
        }

        return res;
    }
}
