package Others;

/*
31. Next Permutation
Medium

1559

475

Favorite

Share
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */

/*
Sol: 找到第一个nums[i] > nums[i-1]的地方，然后从[i, n-1]中选一个大于nums[i-1]的最小值，与nums[i-1]换，再recursion[index, n-1]
     如果整个序列都是降序（即找不到满足条件的i），就直接reverse整个序列（即从最大序列变到了最小序列，return）
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        helper(nums, 0, nums.length-1);
    }

    // next permuta between [l, r]
    private void helper(int[] nums, int l, int r) {
        if (l >= r) return;
        int index = r;
        while (index > l) {
            if (nums[index] > nums[index-1]) break;
            index--;
        }

        if (index == l) {
            reverseIntArray(nums, l, r);
            return;
        }

        int min = Integer.MAX_VALUE;
        int minIndex = r;
        for (int i = r; i >= index; i--) {
            if (min > nums[i] && nums[i] > nums[index-1]) {
                min = nums[i];
                minIndex = i;
            }
        }
        swapIntArray(nums, index-1, minIndex);
        helper(nums, index, r);

    }

    private void reverseIntArray(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    public static void swapIntArray(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        NextPermutation tester = new NextPermutation();
        int nums[] = {3,2,1};
        tester.nextPermutation(nums);
    }
}
