package mars.leetcode;

public class Sort {

    @org.junit.Test
    public void test() {
        int [] input = new int[]{3,6,2,7,5};
//        sort_maopao(input);
        sort_pick(input);
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

}
