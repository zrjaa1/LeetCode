package ArrayString;

public class MedianTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return findMedian(nums2);
        if (nums2 == null || nums2.length == 0) return findMedian(nums1);
        int m = nums1.length;
        int n = nums2.length;
        int remaining = (m+n)/2;
        boolean even = (m+n) % 2 == 0 ? true : false;
        int midone = 0;
        int midtwo = 0;
        while ((!even && remaining > 0) || (even && remaining > 1)) {
            if (nums1[midone] < nums2[midtwo] || midtwo >= n) midone = (midone + (m-1-midone)/2 == midone) ? midone + 1 : midone + (m-1-midone)/2;
            else midtwo = (midtwo + (n-1-midtwo)/2 == midtwo) ? midtwo + 1 : midtwo + (n-1-midtwo)/2;
            remaining = (m+n)/2 - midone - midtwo;
        }
        if (midone == m) {
            if (even) return 0.5*(nums2[midtwo]*nums2[midtwo+1]);
            else return nums2[midtwo];
        } else if (midtwo == n) {
            if (even) return 0.5*(nums1[midone]*nums1[midone+1]);
            else return nums1[midone];
        } else {
            if (even) return 0.5*(nums1[midone] * nums2[midtwo]);
            else return nums1[midone] < nums2[midtwo] ? nums1[midone] : nums2[midtwo];
        }
    }
    private double findMedian(int[] nums) {
        int n = nums.length;
        if (n % 2 == 0) return 0.5*(nums[n/2] + nums[n/2-1]);
        else return nums[n/2];
    }

    public static void main(String[] args) {
        MedianTwoSortedArray tester = new MedianTwoSortedArray();
        int[] nums1 = {1,2};
        int[] nums2 = {-1,0};
        tester.findMedianSortedArrays(nums1, nums2);
    }
}
