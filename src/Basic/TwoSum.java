package Basic;

import java.util.HashMap;

// *Leetcode* #1
//Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.

// *Thoughts*
// HashMap - key - value needed for target, value - index
public class TwoSum {
	static int value;
	
	public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) return null;
        
        // key - value needed for target, value - index
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();   
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) return new int[] {map.get(nums[i]), i};
            else map.put(target-nums[i], i);
        }
        
        return null;
    }
	
	public static void main(String[] args) throws NullPointerException {
		TwoSum test = new TwoSum();
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		int[] res = test.twoSum(nums, target);
		for (int element: res) {
            System.out.println(element);
        }
		
		TwoSum test2 = null;
		try {
			System.out.println(test2.twoSum(nums, target));
		} catch (NullPointerException e) {
			System.out.println("Handle error" + e);
		} finally {	// 
			System.out.println("finally");
		}
	}
	
}
