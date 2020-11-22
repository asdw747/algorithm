package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniquePaths2 {

    /**
     * https://leetcode-cn.com/problems/unique-paths-ii/
     *
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     *
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     *
     *
     * 示例 2：
     * 输入：obstacleGrid = [[0,1],[0,0]]
     * 输出：1
     */

    @Test
    public void main() {
        int [][] input = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int res = uniquePathsWithObstacles(input);
        System.out.println(res);
    }

    /**
     * 想用排列组合排除特定位置的方案去实现，结论是根本不可行
     */
//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        List<int []> output = new ArrayList<>();
//        int m = obstacleGrid[0].length-1;
//        int n = obstacleGrid.length-1;
//        int totalDistance = m + n;
//
//        int [] input = new int[totalDistance];
//        for (int i=0; i<totalDistance; i++) {
//            input[i] = i;
//        }
//
//        generate(obstacleGrid, input, output, new int[n], 0, n);
//        return output.size();
//    }
//
//    public void generate(int[][] obstacleGrid, int [] input, List<int[]> output, int [] outputItem, int index, int n) {
//        if (index == n) {
//            output.add(outputItem);
//            return;
//        }
//
//        int restTarget = n-index;
//        for (int i=0; i<input.length; i++) {
//            if (input.length-i < restTarget) {
//                break;
//            }
//
//            int [] newInput = Arrays.copyOfRange(input, i+1, input.length);
//            int [] newOutputItem = Arrays.copyOf(outputItem, outputItem.length);
//            newOutputItem[index] = input[i];
//            if (obstacleGrid[input[i]-index][index] == 1) {
//                continue;
//            }
//
//            generate(obstacleGrid, newInput, output, newOutputItem, index+1, n);
//        }
//    }


    /**
     * 上动态规划
     * https://leetcode-cn.com/problems/unique-paths-ii/solution/bu-tong-lu-jing-ii-by-leetcode-solution-2/
     *
     * 其实很简单
     * f(i,j) 来表示从坐标 (0,0)到坐标(i,j)的路径总数
     * u(i,j) 表示坐标(i,j)是否有障碍物
     *
     * 1) f(i,j) = f(i-1, j) + f(i, j-1)  //条件，u(i,j)!=0
     * 2) f(i,j) = 0  //条件，u(i,j)==0
     *
     * 结合以上方程式，从头开始对数组进行遍历计算即可
     *
     * 「滚动数组思想」
     * 以下代码用到了滚动数组思想
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }

}
