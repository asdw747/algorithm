package mars.leetcode.y2021q1;

import org.junit.Test;

public class BestTimeToBuyAndSellStock {

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     */
    @Test
    public void test() {
        int [] input = {7,1,5,3,6,4};
        int res = maxProfit(input);

        System.out.println(res);
    }

    public int maxProfit(int[] prices) {
        int minIndex = 0;
        int min = Integer.MAX_VALUE;
        int guessIncome = 0;
        int maxIncome = 0;

        for (int i=0; i<prices.length; i++) {
            minIndex = prices[i] < min ? i : minIndex;
            min = Math.min(prices[i], min);

            guessIncome = Math.max(prices[i] - min, 0);
            maxIncome = Math.max(guessIncome, maxIncome);
        }

        return maxIncome;
    }

}
