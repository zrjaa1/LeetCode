package Sort;

// *LeetCode* #283
/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

// *Thoughts*
// slow and fast, the fast found the first non-zero element and swap with slow. 
// Works when we don't care the order or zeros.

public class MoveZeros {
	public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        int slow = 0;
        int fast = 0;
        while (fast != nums.length) {
            if (nums[fast] != 0) {
                Util.Util.swapIntArray(nums, slow, fast);
                slow++;
            }
            fast++;
        }
    }
}
