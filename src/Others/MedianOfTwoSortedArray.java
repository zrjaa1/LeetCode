package Others;
/*
4. Median of Two Sorted Arrays
Hard

3903

514

Favorite

Share
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

/*
这道题主要是考察对 median 的理解，思路直接看：https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
1. 把两个数组各分为左边和右边，用 i、j 隔开
2. 通过设置 i、j 的和而使两边的长度之和相等（即左右等分）
3. 找到合适的 i 值使左边的末端都小于右边的起始（binary search）
4. 然后计算并返回合适的 median
 */
public class MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) return 0;
        // 1. ensure m >= n
        int m = nums1.length, n = nums2.length;
        if (m < n) return findMedianSortedArrays(nums2, nums1);

        // 2. find the location that ensure -> nums2[i-1] >= nums1[j] && nums1[j-1] >= nums2[i]
        int i=0, j=0;
        int left = 0, right = n;
        while (left <= right) {
            i = left + (right-left)/2;
            // 3. this ensure i + j == (m+n)/2
            j = (m+n+1)/2-i;
                // 极端情况或 i 太小
            if (i < n && j > 0 && nums2[i] < nums1[j-1]) left = i+1;
                // 极端情况或 i 太大
            else if (j < m && i > 0 && nums1[j] < nums2[i-1]) right = i-1;
                // i, j are perfect
            else break;
        }

        int leftMax;
        if (i == 0 || j == 0)
            leftMax = i == 0 ? nums1[j-1] : nums2[i-1];
        else
            leftMax = Math.max(nums1[j-1], nums2[i-1]);
        if ((m+n)%2 ==1) return leftMax;
        else {
            int rightMin;
            if (i == n || j == m)
                rightMin = i == n ? nums1[j] : nums2[i];
            else
                rightMin = Math.min(nums1[j], nums2[i]);
            return 0.5*(leftMax+rightMin);
        }
    }
}
