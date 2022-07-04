package DP;

/**
 * 312. Burst Balloons: https://leetcode.com/problems/burst-balloons/
 * Hard
 *
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 *
 * Input: nums = [1,5]
 * Output: 10
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 */

/**
 * 为什么可以选择后打k:
 * 1. 因为dp也是搜索了所有可能性，所以先打k，还是后打k，对结果并无影响
 * 2. 在计算dp[i, j]所依赖的其他dp值时，其实已经考虑了其邻居k，所以逻辑上，我们实际上是先打邻居，再打k
 * 3. 在计算每一个dp值时，我们都当做[i, j]左边和右边都是1，这样才能时dp的等式合理。画图一下就明白了
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[][] dp = new int[len][len]; // dp[i][j]: nums[i, j]能打出的最大value

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                for (int k = i; k <= j; k++) {
                    int leftVal = k == i ? 0 : dp[i][k - 1]; // 在计算每一个dp值时，我们都当做[i, j]左边和右边都是1，这样才能时dp的等式合理。画图一下就明白了
                    int rightVal = k == j ? 0 : dp[k + 1][j];
                    int midVal = nums[k] * (i == 0 ? 1 : nums[i - 1]) * (j == len - 1 ? 1 : nums[j + 1]);
                    dp[i][j] = Math.max(dp[i][j], leftVal + rightVal + midVal);
                }
            }
        }
        return dp[0][len - 1];
    }
}
