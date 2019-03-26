package BDFS;
/*
322. Coin Change
Medium

1496

68

Favorite

Share
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
 */

/*
DFS的方法时间复杂度太高,用DP
dp[i]表示要凑到i块钱最少需要用多少个硬币 -> dp[0] = 0;
两层for loop：
    第一层用来填dp[i] -> i从1到amount
    第二层用来遍历每种coin的可能 -> 设初始的min为-1，以min来取到可能的最小值
返回dp[amount]
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (coins.length == 1 && amount%coins[0] != 0) return -1;
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = -1;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i-coins[j]] != -1) {
                    min = min == -1 ? dp[i-coins[j]]+1 : Math.min(min, dp[i-coins[j]]+1);
                }
            }
            dp[i] = min;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange tester = new CoinChange();
        int[] coins = {1,2,5};
        tester.coinChange(coins, 11);
    }
}
