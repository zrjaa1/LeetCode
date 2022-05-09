package DP.Stocks;

/**
 * 188. Best Time to Buy and Sell Stock IV: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * Hard
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */

/**
 * 这道题可以算是所有dp stock问题的根源，具体的写法都可以由此引申
 */
public class BestTimeToBuyStockIV {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int days = prices.length;
        int[][] buy = new int[days][k + 1]; // the max profit in ith day when the last operation is buy, with at most k transactions
        int[][] sell = new int[days][k + 1]; // the max profit in ith day when the last operation is sell, with at most k transactions

        for (int i = 0; i < days; i++) {
            buy[i][0] = 0;
            sell[i][0] = 0;
        }

        for (int i = 0; i < days; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    buy[i][j] = -prices[i];
                    sell[i][j] = 0;
                } else {
                    buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j - 1] - prices[i]);
                    sell[i][j] = Math.max(buy[i - 1][j] + prices[i], sell[i - 1][j]);
                }
            }
        }

        return sell[days - 1][k];
    }
}
