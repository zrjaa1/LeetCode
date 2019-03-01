package DP;

/*
198. House Robber
Easy

2174

69

Favorite

Share
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

/*
典型dp，需要注意的是更新dp[1]的时候，需要取dp[0]和dp[1]中的较大值（意味着取或不取），而不是无脑更新
 */
public class HouseRober {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(nums[i] + dp[0], dp[1]);
            dp[0] = Math.max(dp[1], dp[0]);
            dp[1] = cur;
        }
        return Math.max(dp[0], dp[1]);
    }
}
