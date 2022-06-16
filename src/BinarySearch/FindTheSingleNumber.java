package BinarySearch;

/**
 540. Single Element in a Sorted Array: https://leetcode.com/problems/single-element-in-a-sorted-array/
 Medium

 You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

 Return the single element that appears only once.

 Your solution must run in O(log n) time and O(1) space.

 Example 1:

 Input: nums = [1,1,2,3,3,4,4,8,8]
 Output: 2
 Example 2:

 Input: nums = [3,3,7,7,10,11,11]
 Output: 10


 Constraints:

 1 <= nums.length <= 105
 0 <= nums[i] <= 105
 */

/**
 * Note that for this question, the array does not have to be sorted. As long as pairs are neighbors to each other, then it is fine.
 * If we are going to return the value instead of index, we could use bit manipulation as well but it's O(n) instead of binary search O(logn)
 */
public class FindTheSingleNumber {
    /**
     * Best solution, same thought as mine. But watch how to set mid.
     */
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    /**
     * My solution, same thought, it's just the way provided solution used to set mid is more tricky
     */
    public int findTheSingleNumber(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = generateMid(left, right);
            if ((mid - 1 >= 0 && mid + 1 < nums.length && nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) ||
                (mid - 1 < 0 && nums[mid] != nums[mid + 1]) ||
                (mid + 1 >= nums.length && nums[mid] != nums[mid - 1])) {
                    return mid;
            } else if (nums[mid] != nums[mid - 1]) {
                right = mid - 1;
            } else if (nums[mid] != nums[mid + 1]) {
                left = mid + 1;
            }
        }

        return left;
    }

    // so that the [left, mid] has even number of elements
    private int generateMid(int left, int right) {
        int mid = left + (right - left) / 2;
        if ((mid - left + 1) % 2 == 1) {
            mid++;
        }

        return Math.min(mid, right);
    }

    public static final void main(String[] args) {
        FindTheSingleNumber tester = new FindTheSingleNumber();
        int[] nums = {2, 3, 3, 4, 4, 5, 5};
        int[] nums2 = {3, 3, 4, 4, 5, 6, 6};
        int[] nums3 = {3, 3, 4, 5, 5};
        int[] nums4 = {3, 3, 4, 4, 5, 5, 7};
        int[] nums5 = {1};
        int res = tester.findTheSingleNumber(nums5);
        System.out.println(res);
    }
}
