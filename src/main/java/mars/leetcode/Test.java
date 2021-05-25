package mars.leetcode;


public class Test {

    @org.junit.Test
    public void test() {
        int [] input = new int[]{3,6,2,7,5};
        sort(input);
        System.currentTimeMillis();
    }

    public int[] sort(int [] input) {
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

}
