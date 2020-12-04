package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    /**
     * https://leetcode-cn.com/problems/gray-code/
     * 格雷编码
     */

    @Test
    public void test() {
        List<Integer> res = grayCode1(3);
        System.currentTimeMillis();
    }

    public List<Integer> grayCode(int n) {
       List<Integer> res = new ArrayList<>();
       res.add(0);

       int base = 1;
       for(int i=0; i<n; i++){
           for (int j=res.size()-1; j>=0; j--) {
               res.add(base + res.get(j));
           }
           base <<= 1;
       }

       return res;
    }

    public List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<>();

        dfs(res, new ArrayList<>(), n, false);
        return res;
    }

    private boolean dfs(List<Integer> res, List<Integer> cur, int target, boolean reverse) {
        if (cur.size() == target) {
            res.add(getValue(cur));
            return false;
        }

        if(reverse){
            cur.add(1);
            boolean needR = dfs(res, cur, target, false);
            cur.remove(cur.size()-1);

            cur.add(0);
            dfs(res, cur, target, needR);
            cur.remove(cur.size()-1);
        } else {
            cur.add(0);
            boolean needR = dfs(res, cur, target, false);
            cur.remove(cur.size()-1);

            cur.add(1);
            dfs(res, cur, target, needR);
            cur.remove(cur.size()-1);
        }

        return true;
    }

    private int getValue(List<Integer> input) {
        int res=0;
        int base = 1;
        for (int i=input.size()-1; i>=0; i--) {
            res += base*input.get(i);
            base <<= 1;
        }

        return res;
    }

}
