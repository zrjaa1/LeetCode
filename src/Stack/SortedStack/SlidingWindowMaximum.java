package Stack.SortedStack;

/**
 * 239. Sliding Window Maximum: https://leetcode.com/problems/sliding-window-maximum/
 * Hard
 *
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1. Brute Force: O(nk)
 * 2. MaxHeap: O(nlogk)
 * 3. Deque - Sorted Stack: O(n). Maintain a descending deque from first -> last. Deque中放index而不是value。想一些example其实很好做，但注释里的细节还是要想清楚
 */
public class SlidingWindowMaximum {
    // sol3: Sorted Stack
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<>(); // storing the index
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            // remove indexes that are not in the window any more. 只用check first就行，因为如果要删的不是当前的最大值，那么只有一个可能，就是这个值在这个当前的最大值进来时被删掉了。而比当前最大值后进queue的，也会在当前最大值出deque之后出去。
            if (i >= k && deque.getFirst() == i - k) {
                deque.pollFirst();
            }

            // Same as LC 84. Largest Rectangle in Histogram, remove the useless data from the queue, imagine it's a descending sequence from first -> last
            while (!deque.isEmpty() && cur >= nums[deque.getLast()]) { // 如果有新来的元素，比当前的last都要大，那么说明last在deque中已经没有意义了（又比它新，又比它大）
                deque.pollLast();
            }
            deque.offerLast(i);

            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.getFirst()];
            }
        }

        return res;
    }
}
