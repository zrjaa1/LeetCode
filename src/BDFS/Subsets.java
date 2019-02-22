package BDFS;
import java.util.*;

/* 78 Subsets
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

/* Thoughs
Sol1:
    dfs, every branch is a solution
Sol2:
    dfs, every leaf node is a solution
Sol3:
    bfs, every leaf node is a solution
Sol4:
    bfs, every branch is a solution
 */
public class Subsets {
    //sol1:
    /*
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<Integer>();
        helper(nums, sol, 0, res);
        return res;
    }

    public void helper(int[] nums, List<Integer> sol, int index, List<List<Integer>> res) {
        res.add(new ArrayList<>(sol));
        for (int i = index; i < nums.length; i++) {
            sol.add(nums[i]);
            helper(nums, sol, i+1, res);
            sol.remove(sol.size()-1);
        }
    }
}
    */

    //sol2:
    /*
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<Integer>();
        helper(nums, sol, 0, res);
        return res;
    }

    public void helper(int[] nums, List<Integer> sol, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<Integer>(sol));
            return;
        }
        sol.add(nums[index]);
        helper(nums, sol, index + 1, res);
        sol.remove(sol.size() - 1);
        helper(nums, sol, index + 1, res);
    }

    public static void main (String[] args) {
        Subsets tester = new Subsets();
        int[] nums = {1,2,3};
        List<List<Integer>> result = tester.subsets(nums);
    }
}
*/
    //sol3:
    /*
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<Integer>();
        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(temp);
        //
        for (int i = 0; i < nums.length; i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                temp = q.poll();
                temp.add(nums[i]);
                q.offer(new ArrayList<Integer>(temp));
                temp.remove(temp.size()-1);
                q.offer(new ArrayList<Integer>(temp));
            }
        }

        while (q.size() != 0) {
            res.add(q.poll());
        }
        return res;
    }
}
*/
    // sol4:
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<Integer>();
        Queue<List<Integer>> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        q.offer(temp);

        // since we want every branch to be a subset, we act like BFS(with a queue)
        while (q.size()!=0) {
            temp = q.poll();    // for each temp, we use a for loop in the future to get all its possible neighbors.
            res.add(temp);
            int k = 0; // if the temp.size() == 0, next line will have a compile error.
            if (temp.size() != 0) k = map.get(temp.get(temp.size()-1))+1; // get the index of the last element in the temp, since we don't want duplicate subsets.
            for (int j = k; j < nums.length; j++) { // find all possible neighbors
                temp.add(nums[j]);
                q.offer(new ArrayList<>(temp)); // always remember to use a new arraylist instead of temp, if not, the future change of temp will result in the modification of already added list.
                temp.remove(temp.size()-1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Subsets tester = new Subsets();
        int[] nums = {1,2,3};
        List<List<Integer>> res = tester.subsets(nums);
    }
}