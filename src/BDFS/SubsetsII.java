package BDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II: https://leetcode.com/problems/subsets-ii/
 * Medium
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // corner case
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);

        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> cur, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> result = new ArrayList<>(cur);
            res.add(result);
            return;
        }

        /**
         * sol2: remove the first k items
         */
        // branch: pick
        int nextIdx = jump(nums, idx);
        for (int i = idx; i < nextIdx; i++) {
            cur.add(nums[idx]);
        }

        dfs(nums, nextIdx, cur, res);

        for (int i = idx; i < nextIdx; i++) {
            cur.remove(cur.size() - 1);
        }

        // branch: remove (dont' add)
        dfs(nums, idx + 1, cur, res);

        /**
         * sol2: pick the first k items
         */
//        Integer cur = nums[idx];
//        int len = subset.size();
//        // pick the first n
//        subset.add(cur);
//        dfs(nums, idx + 1, subset, res);
//        subset.remove(len);
//
//        // don't pick and jump
//        int nextIdx = nums.length;
//        for (int i = idx; i < nums.length; i++) {
//            if (nums[i] != cur) {
//                nextIdx = i;
//                break;
//            }
//        }
//        dfs(nums, nextIdx, subset, res);
    }

    private int jump(int[] nums, int idx) {
        int num = nums[idx];
        int nextIdx = idx;
        while(nextIdx < nums.length && nums[nextIdx] == nums[idx]) {
            nextIdx++;
        }
        return nextIdx;
    }
}
