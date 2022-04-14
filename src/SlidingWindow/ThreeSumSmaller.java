package SlidingWindow;

/**
 * 259. 3Sum Smaller: https://leetcode.com/problems/3sum-smaller/
 * Medium
 *
 * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example 1:
 *
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 * Example 2:
 *
 * Input: nums = [], target = 0
 * Output: 0
 * Example 3:
 *
 * Input: nums = [0], target = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 0 <= n <= 3500
 * -100 <= nums[i] <= 100
 * -100 <= target <= 100
 */

import java.util.Arrays;

/**
 * 3 sum = 1 pointer + 2 sum
 * Concerns:
 * 1. Duplicates (verified later that we dont' need special processing for it)
 * 2. Sorted? (no need to clarify as the solution has O(n^2) time complexity)
 */
public class ThreeSumSmaller {
    // O(T) = O(n^2), O(S) = O(1)
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }

        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            int tar = target - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] >= tar) {
                    right--;
                } else { // nums[left] + nums[right] < tar
                    count += right - left;
                    left++;
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {
        ThreeSumSmaller tester = new ThreeSumSmaller();
        int[] nums = {1, 1, -2};
        int target = 1;
        tester.threeSumSmaller(nums, target);
    }
}
