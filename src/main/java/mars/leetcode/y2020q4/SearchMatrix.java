package mars.leetcode.y2020q4;

import org.junit.Test;

public class SearchMatrix {

    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *  
     */
    @Test
    public void test() {
//        int [][] input = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int [][] input = new int[][]{{1,3}};
        boolean res = searchMatrix(input, 3);
        System.currentTimeMillis();
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length==0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        if (matrix[0][0]>target || matrix[rows-1][cols-1]<target) {
            return false;
        }

        int directRow = 0;
        while (directRow<rows && matrix[directRow][0]<=target) {
            directRow++;
        }

        directRow --;
        int l=0;
        int r=cols-1;
        while (l<=r) {
            int middle = (r+l)/2;
            if (matrix[directRow][middle]==target) {
                return true;
            }

            if (matrix[directRow][middle]>target) {
                r = middle-1;
            } else {
                l = middle+1;
            }
        }

        return false;
    }
}
