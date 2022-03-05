package base.leetcode.Problem309;

import java.util.Arrays;

public class StockCooldown {


    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[1] = Math.max(0, prices[1] - prices[0]);
        int max = dp[1];
        for (int i = 2; i < prices.length; i++) {
            int maxSoFar = 0;
            for (int j = 0; j < i; j++) {
                int diff = prices[i] - prices[j];
                int sum = 0;
                if (j >= 2) {
                    sum = dp[j-2];
                }
                maxSoFar = Math.max(maxSoFar, sum + diff);
            }
            max = Math.max(max, maxSoFar);
            dp[i] = max;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[] {1, 2, 3, 0, 2};
        StockCooldown sd = new StockCooldown();
        sd.maxProfit(prices);
    }
}
