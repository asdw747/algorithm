package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetZeroes {

    /**
     * https://leetcode-cn.com/problems/set-matrix-zeroes/
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
     *
     * input:
     *  0,1,2,0
     *  3,4,5,2
     *  1,3,1,5
     * output:
     *  0,0,0,0
     *  0,4,5,0
     *  0,3,1,0
     */
    @Test
    public void test() {
        int [][] input = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(input);
    }

    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

}
