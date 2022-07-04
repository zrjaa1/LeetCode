package DFS;
import java.util.*;

/* 40
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return res;
        List<Integer> sol = new ArrayList<Integer>();
        Arrays.sort(candidates);
        dfs(candidates, target, sol, 0, res);
        return res;
    }

    private void dfs(int[] candidates, int left, List<Integer> sol, int level, List<List<Integer>> res) {
        // base case
        // note here the base case is the next level of leaf node.
        if (left == 0) {
            res.add(new ArrayList<Integer>(sol));
            return;
        }
        if (left < 0) return;
        for (int i = level; i < candidates.length; i++) {
            if (i > level && candidates[i] == candidates[i-1]) continue;
            sol.add(candidates[i]);
            dfs(candidates, left-candidates[i], sol, i+1, res);
            sol.remove(sol.size()-1);
        }
    }

    public static void main(String[] args) {
        CombinationSumII tester = new CombinationSumII();
        int[] nums = {10,1,2,7,6,1,5};
        int target = 8;
        tester.combinationSum2(nums, target);
    }
}
