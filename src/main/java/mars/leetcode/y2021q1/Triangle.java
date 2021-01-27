package mars.leetcode.y2021q1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {

    @Test
    public void test() {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> row1 = Arrays.asList(2);
        List<Integer> row2 = Arrays.asList(3,4);
        List<Integer> row3 = Arrays.asList(6,5,7);
        List<Integer> row4 = Arrays.asList(4,1,8,3);
        input.add(row1);
        input.add(row2);
        input.add(row3);
        input.add(row4);

        int res = minimumTotal(input);

        System.currentTimeMillis();
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int minSumOutput = Integer.MAX_VALUE;
        List<Integer> preRow = null;
        for (int i=0; i<triangle.size(); i++) {
            int minSum = Integer.MAX_VALUE;
            List<Integer> row = triangle.get(i);
            List<Integer> row1 = new ArrayList<>();
            for (int j=0; j<row.size(); j++) {
                int curSum;
                if (i==0) {
                    curSum = row.get(0);
                } else {
                    if (j==0) {
                        curSum = preRow.get(0)+row.get(0);
                    } else if (j==row.size()-1) {
                        curSum = preRow.get(preRow.size()-1)+row.get(row.size()-1);
                    } else {
                        curSum = row.get(j) + (preRow.get(j-1)<preRow.get(j) ? preRow.get(j-1):preRow.get(j));
                    }
                }

                minSum = Math.min(curSum, minSum);
                row1.add(curSum);
            }

            minSumOutput = minSum;
            preRow = row1;
        }

        return minSumOutput;
    }

}










