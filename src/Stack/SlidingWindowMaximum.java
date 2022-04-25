package Stack;

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
 * 3. Sorted Stack(Deque): O(n)
 */
public class SlidingWindowMaximum {
    // sol3: Sorted Stack
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (i >= k && deque.getFirst() == i - k) {
                deque.pollFirst();
            }

            // Same as Rectangle Question, remove the useless data from the queue, imagine it's a descending sequence from first -> last
            while (!deque.isEmpty() && cur >= nums[deque.getLast()]) {
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
