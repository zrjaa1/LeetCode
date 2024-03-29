package SlidingWindow;

/**
 * 560. Subarray Sum Equals K: https://leetcode.com/problems/subarray-sum-equals-k/
 * Medium
 *
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 1. In case of all positive integer, use 2 pointers: O(T) = O(n), O(S) = O(1)
 * 1. In case of negative integer exists, use prefix sum: O(T) = O(n), O(S) = O(n)
 * HOWEVER, this problem needs to know the # of subarrays, instead of if it exists. So we need a Map instead of Set to record frequency.
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        Map<Integer, Integer> sum = new HashMap<>(); // key: sum of subarray [0, i], value: how many subarrays exist for this sum
        int curSum = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            curSum += nums[i];
            int target = curSum - k; // k == sum[i, j] == sum[0, j] - sum[0, i - 1], where sum[0, i - 1] is the target we're trying to find
            if (sum.containsKey(target)) {
                res += sum.get(target);
            }
            if (target == 0) { // if target is 0, we need to add on it one more time, as sum[0, j] itself is one of answer.
                res++;
            }

            sum.put(curSum, sum.getOrDefault(curSum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK tester = new SubarraySumEqualsK();
        int[] nums = {1, -1, 1};
        int k = 0;
        int res = tester.subarraySum(nums, k);
        System.out.println(res);
    }
}
