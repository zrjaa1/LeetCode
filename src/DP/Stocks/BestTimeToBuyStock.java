package DP.Stocks;

/**
121. Best Time to Buy and Sell Stock
Easy

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

/**
Thought: min - the min value till now.
         max - the max profit that can be made through [0, i)
 */

public class BestTimeToBuyStock {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        int min = Integer.MIN_VALUE;
        int max = 0; // use 0 instead of Integer.MIN_VALUE here as the profit cannot be less than 0.

        for (int i = 0; i < days; i++) {
            if (i != 0) {
                max = Math.max(max, min + prices[i]);
            }
            min = Math.max(min, -prices[i]);
        }

        return max;
    }
}
