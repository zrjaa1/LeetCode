package Bit;

/**
 * 260. Single Number III: https://leetcode.com/problems/single-number-iii/
 * Medium
 *
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 *
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 *
 * Input: nums = [0,1]
 * Output: [1,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each integer in nums will appear twice, only two integers will appear once.
 */

/**
 * Use xor to find the first flag position that could be used to distinguish 2 result elements, as well as other common elements.
 * After we have separated them as 2 groups, we could use the same thought as Single Number I to find special element in each group.
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int xor = 0;
        // initialization
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        // find the flag
        int mask = 1;
        while ((xor & mask) == 0) {
            mask = mask << 1;
        }

        int nums1 = xor;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & mask) != 0) {
                nums1 ^= nums[i];
            }
        }

        return new int[] {nums1, xor ^ nums1};
    }
}
