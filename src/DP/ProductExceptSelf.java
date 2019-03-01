package DP;

/*
238. Product of Array Except Self
Medium

1917

152

Favorite

Share
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

/*
    1. 可以用两个数组，一个存当前位置向左的乘积，另一个存当前位置向右看的乘积（都不包含），相当于pre-processing；然后one pass得结果
    2. 第二种方法麻烦一些，先得到所有的乘积，再按位除直接得到结果。但麻烦的地方在于需要考虑0. >=2个0直接return全0；一个0的时候，需要记录不为0的product（Line 39）然后把res的该位设为product（Line43）
       并且，如果countZero为1，其他位都应该是0，而不是product/nums[i] (Line44)
 */
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int[] res = new int[nums.length];
        int product = 1;
        int countZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) countZero++;
            else product *= nums[i];
        }
        if (countZero >= 2) return res;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) res[i] = product;
            else if (countZero != 1) res[i] = product/nums[i];
        }
        return res;
    }
}
