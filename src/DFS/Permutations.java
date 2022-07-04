package DFS;
import java.util.*;

/* 46

 */

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) return res;
        List<Integer> sol = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            sol.add(nums[i]);
        }
        dfs(sol, 0, nums.length-1, res);
        return res;
    }

    private void dfs(List<Integer> sol, int index, int max, List<List<Integer>> res)     {
        if (index == max) {
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = index; i < max; i++) {
            Collections.swap(sol, index, i);
            dfs(sol, index+1, max, res);
            Collections.swap(sol, index, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Permutations tester = new Permutations();
        tester.permute(nums);
    }
}
