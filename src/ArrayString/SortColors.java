package ArrayString;

/*
75. Sort Colors
Medium

1436

139

Favorite

Share
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

 */

import Util.Util;

/*
Thoughts: three pointers, 4 regions:
          00001111xxxx2222
              i   j  k
          [0, i) all 0s
          [i, j) all 1s
          [j, k] unsorted
          (k, end] all 2s
Further thought: 1. Quick sort is a recursively sort 2
                 2. this solution is use 3 pointers to sort 3, we can also use sort 2 for twice to sort 3.
                 3. use sort 2 three times to sort 4.
                 5. use sort 2 n times to sort many numbers -> quick sort


 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int i = 0, j = 0, k = nums.length-1;
        while (j <= k) {
            if (nums[j] == 0) {
                Util.swapIntArray(nums, j, i);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
                Util.swapIntArray(nums, j, k);
                k--;
            }
        }
    }
}
