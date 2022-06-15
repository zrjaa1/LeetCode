package BinarySearch;

/**
 * You're given a int array that each number appears twice except the a specific number, which you'll need to return its index.
 * Note that the same number always appear in pairs, for example: 2334455, 3344566. You need a solution which executes in less than O(n) time.
 */

public class FindTheSingleNumber {
    public int findTheSingleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
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
        int res = tester.findTheSingleNumber(nums4);
        System.out.println(res);
    }
}
