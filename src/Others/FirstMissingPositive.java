package Others;

/**
 * 41. First Missing Positive: https://leetcode.com/problems/first-missing-positive/
 * Hard
 *
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 */

/**
 * 这道题比较Tricky，跟Zuma、LongestIncreasingSubsequence等题一样，只记忆即可，不具备参考价值
 * 1. 1st pass, 查看1是否作为可能的答案
 * 2. 2nd pass, 把所有不可能作为答案的nums[i]，in place变为1，包括：0，负数，> nums.length的数
 * 3. 3rd pass, 以nums[i] - 1作为index（第二步保证了index >= 0)，将nums[index]变为负数，表示数字index已经出现过
 * 4. 4th pass，再次遍历，第一个出现nums[i] > 0的情况，表示没有出现过，即为first missing positive
 * 5. 如果还没有得到结果，说明是类似[1, 2, 3, 4, 5]的连续数列，直接return nums.length + 1
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        // 1st pass, 查看1是否作为可能的答案
        boolean missingOne = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                missingOne = false;
                break;
            }
        }

        if (missingOne) {
            return 1;
        }

        // 2nd pass, 把所有不可能作为答案的nums[i]，in place变为1，包括：0，负数，> nums.length的数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }

        // 3rd pass, 以nums[i] - 1作为index（第二步保证了index >= 0)，将nums[index]变为负数，表示数字index已经出现过
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }

        // 4th pass，再次遍历，第一个出现nums[i] > 0的情况，表示没有出现过，即为first missing positive
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // 如果还没有得到结果，说明是类似[1, 2, 3, 4, 5]的连续数列，直接return nums.length + 1
        return nums.length + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive tester = new FirstMissingPositive();
        int[] nums = {1, 2, 0};
        int res = tester.firstMissingPositive(nums);
        System.out.println(res);
    }
}
