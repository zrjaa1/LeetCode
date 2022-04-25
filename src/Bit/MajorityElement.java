package Bit;

/**
 * 169. Majority Element: https://leetcode.com/problems/majority-element/
 * Easy
 *
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
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
            if (sum[i] >= (nums.length + 1) / 2) {
                res += 1 << i; // 这里用左移而不是 * Math.pow(2, i)，才可以handle负数的情况。
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MajorityElement tester = new MajorityElement();
        int[] nums = {3, 2, 3};
        int res = tester.majorityElement(nums);
        System.out.println(res);
    }
}
