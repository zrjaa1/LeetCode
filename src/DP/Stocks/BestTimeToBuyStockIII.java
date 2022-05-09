package DP.Stocks;

/**
 * 123. Best Time to Buy and Sell Stock III: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * Hard
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */

/**
 * We could also use 2D dp
 */
public class BestTimeToBuyStockIII {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        int[] buy1 = new int[days]; // the max profit in ith day when the last operation is buy, with at most 1 transactions
        int[] buy2 = new int[days]; // the max profit in ith day when the last operation is buy, with at most 2 transactions
        int[] sell1 = new int[days];
        int[] sell2 = new int[days];

        buy1[0] = -prices[0];
        buy2[0] = -prices[0]; // This is correct logically, as the definition is "at most" 2 transactions.

        for (int i = 1; i < days; i++) {
            sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
            buy1[i] = Math.max(buy1[i - 1], -prices[i]);
            sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);
            buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
        }

        return sell2[days - 1];
    }
}
