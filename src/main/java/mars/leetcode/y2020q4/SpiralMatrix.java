package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     *
     */
    @Test
    public void test() {
        int [][] input = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        spiralOrder(input);
        System.currentTimeMillis();
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int m = matrix[0].length;
        int n = matrix.length;

        int a = 0;
        int b = m;
        int c = n;
        int d = 0;

        int type = 1;
        int i=0,j = 0;
        for (int index=0; index<m*n; index++) {
            result.add(matrix[i][j]);
            if (type == 1) {
                if (j>=b-1) {
                    type = 2;
                    a++;
                    i++;
                } else {
                    j++;
                }
            } else if (type == 2) {
                if (i>=c-1) {
                    type = 3;
                    b--;
                    j--;
                } else {
                    i++;
                }
            } else if (type == 3) {
                if (j<=d) {
                    type = 4;
                    c--;
                    i--;
                } else {
                    j--;
                }
            } else {
                if (i<=a) {
                    type = 1;
                    d++;
                    j++;
                } else {
                    i--;
                }
            }
        }

        return result;
    }

}
