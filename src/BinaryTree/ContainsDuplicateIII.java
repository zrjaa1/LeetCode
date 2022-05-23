package BinaryTree;

import java.util.TreeSet;

/**
 * 220. Contains Duplicate III: https://leetcode.com/problems/contains-duplicate-iii/
 * Medium
 *
 * Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }

        TreeSet<Integer> set = new TreeSet(); // 不用担心duplicate的问题，因为一旦window内有duplicate，那么直接返回true了（差值为0）
        for (int i = 0; i < nums.length; i++) {
            if (i >= k + 1) {
                set.remove(nums[i - k - 1]);
            }

            Integer floor = set.floor(nums[i]);
            if (floor != null && (long) floor >= (long) nums[i] - t) {
                return true;
            }
            Integer ceiling = set.ceiling(nums[i]);
            if (ceiling != null && (long) ceiling <= (long) nums[i] + t) {
                return true;
            }

            set.add(nums[i]);
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII tester = new ContainsDuplicateIII();
        System.out.println(tester.containsNearbyAlmostDuplicate(new int[] {1, 0, 1, 1}, 1, 2));
    }
}
