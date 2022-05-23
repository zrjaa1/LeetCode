package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum: https://leetcode.com/problems/continuous-subarray-sum/
 * Medium
 *
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 */

/**
 * 这道题主要考察:
 * 1. preSum的应用，以及在Map中记录index
 * 2. base case的考虑：在sum % k == 0时如何return TRUE，从而想到在initialize的时候map.put(0, -1)
 */
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int curSum = 0;
        map.put(0, -1); // 思考在sum % k == 0时如何return TRUE，从而想到在initialize的时候map.put(0, -1)
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            int target = curSum % k;
            if (map.containsKey(target)) {
                int j = map.get(target);
                if (i - j >= 2) {
                    return true;
                }
            } else {
                map.put(curSum % k, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum tester = new ContinuousSubarraySum();
        int[] nums = {2, 4, 3};
        int target = 6;
        System.out.println(tester.checkSubarraySum(nums, target));
    }
}
