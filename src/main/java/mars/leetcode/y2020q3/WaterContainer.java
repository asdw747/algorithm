package mars.leetcode.y2020q3;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterContainer {

    /*
    给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, a) 。在
    坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, a) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

    说明：你不能倾斜容器，且 n 的值至少为 2。

    示例：
    输入：[1,8,6,2,5,4,8,3,7]
    输出：49
     */
    public static void main(String [] args) {

        List<Integer> items = Arrays.asList(1,8,6,2,5,4,8,3,7);
        System.out.println(count2(items));

    }

    /*
    双指针同时移动方案更好
     */
    private static int count(List<Integer> input) {
        int volume = 0;
        for (int i=0; i<input.size(); i++) {
            int begin = i;
            int beginValue = input.get(begin);

            int end = begin + 1;
            while (end<input.size()) {
                int endValue = input.get(end);
                volume = Math.max((end - begin) * Math.min(beginValue, endValue), volume) ;

                end ++;
            }
        }

        return volume;
    }

    /*
    双指针方案
     */
    private static int count2(List<Integer> input) {
        int volume = 0;

        int begin = 0;
        int end = input.size() -1;
        while (end > begin) {
            int beginValue = input.get(begin);
            int endValue = input.get(end);

            volume = Math.max(volume, (end-begin) * Math.min(beginValue, endValue));

            if (endValue > beginValue) {
                begin ++;
            } else  {
                end --;
            }
        }

        return volume;
    }


}
