package sort;

// *Leetcode* #88
/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/

// *Thoughts*
// Since we want to inplace, if we start from the left, some elements could be overwrite. If we start from the right, we can avoid this.
public class MergeTwoSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null || nums2.length == 0) return;
        int cur = m+n-1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[cur] = nums1[m];
                m--;
            } else {
                nums1[cur] = nums2[n];
                n--;
            }
            cur--;
        }
        
        // post-processing
        if (m < 0) {
            while (n >= 0 && cur >= 0) {
                nums1[cur] = nums2[n];
                cur--;
                n--;
            }
        }
    }
}
