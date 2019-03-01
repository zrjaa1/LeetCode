package DP;

/*
53. Maximum Subarray
Easy

3673

128

Favorite

Share
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
 */

/*
if dp[i] >= 0, the previous subarray is helpful to the following;
otherwise I will start a new subarray from the current position.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp >= 0) dp += nums[i];
            else dp = nums[i];
            if (dp > res) res = dp;
        }
        return res;
    }
}
