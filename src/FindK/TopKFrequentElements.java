package FindK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 347. Top K Frequent Elements: https://leetcode.com/problems/top-k-frequent-elements/
 * Medium
 *
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

/**
 * 看到K这个数的时候就应该想到quick selection - O(n)。还可以用Heap来做 - O(k logn)或O(klogk)。
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // build map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int freq = map.getOrDefault(nums[i], 0);
            map.put(nums[i], freq + 1);
        }

        // dedup
        List<Integer> numsList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            numsList.add(key);
        }

        int[] numsDedup = numsList.stream().mapToInt(i -> i).toArray();
        if (k >= nums.length) {
            return numsDedup;
        }

        // find k
        findK(numsDedup, 0, numsDedup.length - 1, map, k);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = numsDedup[i];
        }

        return res;
    }

    // to sort the int[] so that the first k elements are the biggest k items
    private void findK(int[] nums, int left, int right, Map<Integer, Integer> map, int k) {
        if (left == right) {
            return;
        }

        int index = partition(nums, left, right, map);
        int ranking = index - left + 1;
        if (ranking == k) {
            return;
        }
        if (ranking > k) {
            findK(nums, left, index - 1, map, k);
        } else {
            findK(nums, index + 1, right, map, k - ranking);
        }
    }

    // return the index within [left, right] where nums[left, index) are bigger than nums[index] and nums(index, nums.length - 1] are smaller
    private int partition(int[] nums, int left, int right, Map<Integer, Integer> map) {
        if (left == right) {
            return left;
        }

        int blocker = left;
        for (int i = left; i < right; i++) {
            if (map.get(nums[i]) >= map.get(nums[right])) {
                swap(nums, i, blocker++);
            }
        }
        swap(nums, blocker, right);
        return blocker;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        TopKFrequentElements tester = new TopKFrequentElements();
        int[] nums = {2,3,4,1,4,0,4,-1,-2,-1};
        int k = 2;
        int[] res = tester.topKFrequent(nums, k);
    }
}
