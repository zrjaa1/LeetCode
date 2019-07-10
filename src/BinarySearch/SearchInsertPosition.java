package BinarySearch;

// Leetcode #35
//Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//You may assume no duplicates in the array.
//
//Example 1:
//
//Input: [1,3,5,6], 5
//Output: 2
//Example 2:
//
//Input: [1,3,5,6], 2
//Output: 1
//Example 3:
//
//Input: [1,3,5,6], 7
//Output: 4
//Example 4:
//
//Input: [1,3,5,6], 0
//Output: 0

class SearchInsertPosition {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while (left < right - 1) {
            mid = left + (right-left)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid;
            else if (nums[mid] > target) right = mid;
        }
        if (nums[left] == target) return left;
        else if (nums[right] == target) return right;
        else if (nums[left] > target) return left;
        else if (nums[right] < target) return right+1;
        else return right;
    }
    
    public static void main(String[] args) {
    	SearchInsertPosition test = new SearchInsertPosition();
    	int[] nums = {1,3,5,6};
    	int target = 5;
    	int res = test.search(nums, target);
    	System.out.println(res);
    }
}
