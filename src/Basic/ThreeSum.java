package Basic;
import java.util.*;

/*
15. 3Sum
Medium

3384

369

Favorite

Share
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

/*
先sort，再用two sum的办法找到
这道题的难点在于remove duplicate.方法是：
1. 在for loop第一个元素时，往前看，如果相同的话直接continue
2. 在while loop里确定第2、3个元素时，如果找到答案了，那么检查left和right临近的值，如果相等的话，直接跳过。这样避免了left或者right多次取相同值的可能（即对于同一right，left不能两次取相同的值）
 */

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int left = i+1;
            int right = n-1;
            while (left < right) {
                if (nums[left] + nums[right] + nums[i] == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //对同一个nums[i]，避免了left或right多次指向相同的元素，导致重复的情况
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] + nums[i] < 0) left++;
                else right--;
            }
        }
        return res;
    }
}
