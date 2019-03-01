package DP;

/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */

/*
1. Sol 1: dp, 每个位置往前看，看是否能跳出循环 -> 优化，填的时候从远到近填 -> 再优化，有maxIndex记录当前能到达的最远 （Greedy）
2. Sol 2: dp, 每个位置往后看，返回dp【0】 -> 优化，返回能到达终点的minIndex
3. Sol 2: recursion

 */
public class JumpGame {
    // sol 1
    /*
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int maxIndex = 0;
        // 在这里不能写 for (int i = maxIndex; i >=0; i--) 因为i的初始化只有一次
        for (int i = 0; i <= maxIndex; i++) {
            maxIndex = Math.max(maxIndex, i+nums[i]);
            if (maxIndex >= n-1) return true;
        }
        return false;
    }
    */

    // sol 2
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int minIndex = nums.length-1;
        for (int i = nums.length-2; i >= 0; i--) {
            for (int j = 0; j < nums[i]; j++) {
                if (i+j+1 >= minIndex) {
                    minIndex = i;
                    break;
                }
            }
        }
        return minIndex == 0;
    }
}
