package mars.leetcode.y2020q4;

import org.junit.Test;

public class MinimumPathSum {

    /**
     * https://leetcode-cn.com/problems/minimum-path-sum/
     */
    @Test
    public void test() {
        int [][] input = new int[][]{{}};
        int res = minPathSum(input);
        System.currentTimeMillis();
    }

    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        if (columns==0) {
            return 0;
        }

        int [][] counter = new int[rows][columns];
        for (int j=0; j<columns; j++) {
            counter[0][j] = j>0 ? (grid[0][j] + counter[0][j-1]) : grid[0][j];
        }

        for (int i=1; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                if (j==0) {
                    counter[i][j] = counter[i-1][j] + grid[i][j];
                } else {
                    counter[i][j] = Math.min(counter[i-1][j], counter[i][j-1]) + grid[i][j];
                }
            }
        }

        return counter[rows-1][columns-1];
    }

}
