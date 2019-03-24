package ArrayString;

/*
80. Remove Duplicates from Sorted Array II
Medium

581

499

Favorite

Share
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.

 */
public class RemoveDuplicateArrayII {
    /* my solution
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int slow = 0;
        boolean exceed = false;
        boolean result = true;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int fast = 0; fast < nums.length; fast++) {
            result = set.add(nums[fast]);
            if (result == true) {
                nums[slow++] = nums[fast];
                exceed = false;
            }
            if (result == false && exceed == false) {
                exceed = true;
                nums[slow++] = nums[fast];
            }
            if (result == false && exceed == true)  {
                continue;
            }
        }
        return slow;
    }
    */

    // best solution
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }
}
