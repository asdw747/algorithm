package mars.leetcode.y2020q4;

import mars.leetcode.util.PermuteUtil;
import org.junit.Test;

public class UniquePaths {

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     *
     * 示例 1:
     *
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     */
    @Test
    public void test() {
        int res = uniquePaths(7,3);
        System.currentTimeMillis();

    }

    /**
     * 起点走到终点的步数时固定的，横向和纵向的步数也是固定的
     * 本质上是排列组合的问题，从m+n个数中选出n个或m个可能的无视顺序组合，
     *
     */
    public int uniquePaths(int m, int n) {
        return PermuteUtil.allPermuteUseC(new int[m+n-2], m-1).size();
    }

}