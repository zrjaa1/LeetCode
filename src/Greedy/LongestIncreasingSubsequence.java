package Greedy;

import java.util.LinkedList;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 * Medium
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> minList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) { // O(n)
            int indexToUpdate = findSmallestBiggerNumberIndex(minList, nums[i]); // O(logn)
            if (indexToUpdate >= minList.size()) {
                minList.add(nums[i]);
            } else {
                minList.set(indexToUpdate, nums[i]);
            }
        }

        return minList.size();
    }

    private int findSmallestBiggerNumberIndex(List<Integer> minList, int target) {
        if (minList == null || minList.size() == 0) {
            return 0;
        }

        int start = 0, end = minList.size() - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (minList.get(mid) == target) {
                return mid;
            } else if (minList.get(mid) < target) { // go right
                start = mid + 1;
            } else { // go left;
                end = mid - 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        LongestIncreasingSubsequence tester = new LongestIncreasingSubsequence();
        int result = tester.lengthOfLIS(nums);
        System.out.println(result);
    }
}
