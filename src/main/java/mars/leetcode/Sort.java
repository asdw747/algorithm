package mars.leetcode;

import java.util.Arrays;

public class Sort {

    @org.junit.Test
    public void test() {
        int [] input = new int[]{3,6,2,7,5,3,1,7,10,4};
//        sort_maopao(input);
//        sort_pick(input);
//        int[] res = sort_merge(input);
        sort_quick(input);
        System.currentTimeMillis();
    }

    public int[] sort_maopao(int [] input) {
        for (int i=0; i<input.length-1; i++) {
            for (int j=0;j <input.length-1-i; j++) {
                if (input[j] > input[j+1]) {
                    int tmp = input[j+1];
                    input[j+1] = input[j];
                    input[j] = tmp;
                }
            }
        }

        return input;
    }

    public int[] sort_pick(int [] input) {
        for (int i=0; i<input.length-1; i++) {
            int minIndex = i;
            for (int j=i+1;j <input.length; j++) {
                if (input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }

            int tmp = input[i];
            input[i] = input[minIndex];
            input[minIndex] = tmp;
        }

        return input;
    }


    public int[] sort_hash(int [] input) {
        return input;
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
