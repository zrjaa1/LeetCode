package Bit;

/**
 * 137. Single Number II: https://leetcode.com/problems/single-number-ii/
 * Medium
 *
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int[] sum = new int[32];
        for (int num: nums) {
            for (int i = 0; i < 32; i++) {
                sum[i] += num & 1;
                num = num >> 1;
            }
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (sum[i] % 3) << i; // 这里用左移而不是 * Math.pow(2, i)，才可以handle负数的情况。
        }

        return res;
    }

    public static void main(String[] args) {
        SingleNumberII tester = new SingleNumberII();
        int[] nums = {2, 2, 3, 2};
        int res = tester.singleNumber(nums);
        System.out.println(res);
    }
}
