package BinarySearch;
import java.util.Arrays;

// Leetcode #34: 

//my solution: use binary search, once find, break; 
//Time Complexity: O(n)
//Space Complexity: O(1)
/*
class SearchRange {
 public int[] search(int[] nums, int target) {
     int[] res = new int[2];
     if (nums == null || nums.length == 0) {
         res[0] = -1;
         res[1] = -1;
         return res;
     };
     
     
     int left = 0;
     int right = nums.length-1;
     int mid = 0;
     while (left < right - 1) {
         mid = left + (right-left)/2;
         if (nums[mid] == target) break;
         else if (nums[mid] < target) left = mid;
         else if (nums[mid] > target) right = mid;
     }
     if (nums[mid] == target) {
         left = mid;
         right = mid;
     } else if (nums[left] == target) {
         right = left;
     } else if (nums[right] == target) {
         left = right;
     } else {
         res[0] = -1;
         res[1] = -1;
         return res;
     }
     
     while(left >= 0 && nums[left] == target) left--;
     while(right < nums.length && nums[right] == target) right++;
     res[0] = left+1;
     res[1] = right-1;
     return res;
 }
}
*/

//online solution: use binary search twice to fix left and right
class SearchRange{
public int[] search(int[] nums, int target) {
     if (nums == null || nums.length == 0) return new int[]{-1, -1};;
     
     int[] res = new int[2];
     int left = 0;
     int right = nums.length-1;
     int mid = 0;
     while (left < right -1) {
         mid = left + (right-left)/2;
         if (nums[mid] >= target) right = mid;
         else left = mid;
     }
     if (nums[left] == target) res[0] = left;
     else if (nums[right] == target) res[0] = right;
     else return new int[]{-1, -1};
     
     left = 0;
     right = nums.length-1;
     while (left < right -1) {
         mid = left + (right-left)/2;
         if (nums[mid] > target) right = mid;
         else left = mid;
     }
     if (nums[right] == target) res[1] = right;
     else if (nums[left] == target) res[1] = left;
     else return new int[]{-1, -1};
     return res;
 }

	public static void main(String[] args) {
		SearchRange test = new SearchRange();
		int[] nums = {5,7,7,8,8,10};
		int target = 8;
		int[] res = test.search(nums, target);
		System.out.println(Arrays.toString(res));
	}
}
