package mars.leetcode.y2021q1;

import org.junit.Test;

public class SurroundedRegions {

    /**
     * https://leetcode-cn.com/problems/surrounded-regions/
     */
    @Test
    public void test() {
        char [][] input = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        solve(input);

        System.currentTimeMillis();
    }

    public void solve(char[][] input) {
        if (input.length == 0 || input[0].length == 0) {
            return;
        }

        boolean[][] find = new boolean[input.length][input[0].length];
        for (int i=0; i<input.length; i++) {
            if (i==0 || i==input.length - 1) {
                for (int j=0; j<input[0].length; j++) {
                    find(input, find, i, j);
                }
            } else {
                find(input, find, i, 0);
                find(input, find, i, input[0].length-1);
            }
        }

        for (int i=0; i<input.length; i++) {
            for (int j=0; j<input[0].length; j++) {
                if (input[i][j] == 'M') {
                    input[i][j] = 'O';
                } else if (input[i][j] == 'O') {
                    input[i][j] = 'X';
                }
            }
        }
    }

    public void find(char[][] input, boolean[][] find, int i, int j) {
        int iLength = input.length;
        int jLength = input[0].length;

        if(input[i][j] == 'X') {
            return;
        }

        find[i][j] = true;
        input[i][j] = 'M';

        //right
        if (j+1 < jLength && !find[i][j+1]) {
            find(input, find, i, j+1);
        }

        if (j-1 >= 0 && !find[i][j-1]) {
            find(input, find, i, j-1);
        }

        if (i+1 < iLength && !find[i+1][j]) {
            find(input, find, i+1, j);
        }

        if (i-1 >= 0 && !find[i-1][j]) {
            find(input, find, i-1, j);
        }
    }


}
