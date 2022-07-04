package DFS;
import java.util.*;

/*
216
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k <= 0 || n <= 0) return null;
        List<Integer> sol = new ArrayList<Integer>();
        dfs(k, 1, sol, n, res);
        return res;
    }

    // index - how many numbers has been used
    private void dfs(int k, int digit, List<Integer> sol, int left, List<List<Integer>> res) {
        if (left == 0 && sol.size() == k) {
            res.add(new ArrayList<Integer>(sol));
            return;
        }
        if (left < 0 || sol.size() > k) return;

        for (int i = digit; i < 10; i++) {
            sol.add(i);
            dfs(k, i+1, sol, left-i, res);
            sol.remove(sol.size()-1);
        }
    }
}
