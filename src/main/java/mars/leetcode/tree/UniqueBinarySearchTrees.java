package mars.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBinarySearchTrees {

    /**
     * https://leetcode-cn.com/problems/unique-binary-search-trees/
     */
    @Test
    public void test() {
        int res = generateTrees(15);
        System.currentTimeMillis();
    }

    public Map<Integer, Integer> map = new HashMap<>();

    public int generateTrees(int n) {
        if (n<=0) {
            return 0;
        }

        return generateInternal(1, n);
    }

    private int generateInternal(int start, int end) {
        int length = (end-start) + 1;
        if (map.containsKey(length)) {
            return map.get(length);
        }

        int all = 0;
        if (start > end) {
            all = 1;
            return all;
        }

        for (int i=start; i<=end; i++) {
            int leftAll = generateInternal(start, i-1);
            int rightAll = generateInternal(i+1, end);

            all += leftAll*rightAll;
        }

        map.put(length, all);
        return all;
    }

}
