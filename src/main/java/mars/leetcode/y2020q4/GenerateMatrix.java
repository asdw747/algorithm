package mars.leetcode.y2020q4;

import org.junit.Test;

public class GenerateMatrix {

    /**
    给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

    示例:

    输入: 3
    输出:
    [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
    ]

     */
    @Test
    public void test() {
        int [][] res = generateMatrix(1);
        System.currentTimeMillis();
    }

    public int[][] generateMatrix(int n) {
        int [][]res = new int[n][n];
        int max = n * n;

        int status = 1;
        int a=0, b=0;
        int count = 0;
        for (int i=1; i<=max; i++) {
            if (status == 1) {
                res[a][b] = i;
                if (b>=n-1-count) {
                    status = 2;
                    a++;
                } else {
                    b++;
                }
            } else if (status == 2) {
                res[a][b] = i;
                if (a>=n-1-count) {
                    status = 3;
                    b--;
                } else {
                    a++;
                }
            } else if (status == 3) {
                res[a][b] = i;
                if (b<=count) {
                    status = 4;
                    a--;
                    count++;
                } else {
                    b--;
                }
            } else if (status == 4) {
                res[a][b] = i;
                if (a<=count) {
                    status = 1;
                    b++;
                } else {
                    a--;
                }
            }
        }

        return res;

    }

}
