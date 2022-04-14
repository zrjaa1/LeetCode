package BinarySearch;

/**
 * 334. Increasing Triplet Subsequence: https://leetcode.com/problems/increasing-triplet-subsequence/
 * Medium
 * Share
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 */

/**
 * Similar to LC 300 LongestIncreasingSubsequence, but easier and could be resolved in O(S) = O(1), O(T) = O(n)
 * 这道题最值得学习的是初始值的设置，如何根据我们的code以及goal，来设置first和second合适的初始值：
 * 1. 因为我们希望第一个value直接被赋值到first，所以把first设置为MAX_VALUE
 * 2. 因为我们希望在second有意义的情况下，才进入return true的条件语句，因此second也设置为MAX_VALUE，避免了意外返回true的情况
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return false;
        }

        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num > second) {
                return true;
            } else if (num <= first) {
                first = num;
            } else { // first < num <= second
                second = num;
            }
        }
        return false;
    }
}
