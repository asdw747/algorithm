package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge {


    /**
    给出一个区间的集合，请合并所有重叠的区间。

     示例 1:

     输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     输出: [[1,6],[8,10],[15,18]]
     解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

     */
    @Test
    public void test() {
        int [][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int [][] res = merge(input);
        System.currentTimeMillis();
    }


    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] item : intervals) {
            if (result.isEmpty()) {
                result.add(item);
            } else {
                int[] latestItem = result.get(result.size()-1);
                if (latestItem[1] < item[0]) {
                    result.add(item);
                } else if (item[1]>latestItem[1]) {
                    latestItem[1] = item[1];
                }
            }
        }

        return result.toArray(new int[result.size()][]);
    }

}
