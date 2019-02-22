package DP;

/*
152. Maximum Product Subarray
Medium

1754

81

Favorite

Share
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

// O(1) DP，dp[i]只与dp[i-1]有关，所以可以用一个int来表示。
// 因为负数的存在，我们还需要记录当前可能的最小值
// 这里还有一个技巧，不用考虑逻辑，只无脑取num[i], num[i]*curMin, num[i]*curMax中的最大&最小值。
public class MaxmiumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int curMin = nums[0];
        int curMax = nums[0];
        int max = curMax;
        for (int i = 1; i < nums.length; i++) {
            int choice1 = nums[i] * curMax;
            int choice2 = nums[i] * curMin;
            int choice3 = nums[i];
            curMin = Math.min(choice1, choice2);
            curMin = Math.min(curMin, choice3);
            curMax = Math.max(choice1, choice2);
            curMax = Math.max(curMax, choice3);
            if (max < curMax) max = curMax;
        }
        return max;
    }
}
