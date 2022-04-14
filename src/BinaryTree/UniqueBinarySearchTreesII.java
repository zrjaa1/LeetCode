package Tree;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II: https://leetcode.com/problems/unique-binary-search-trees-ii/
 * Medium
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 */
public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int min, int max) {
        List<TreeNode> result = new ArrayList<>();
        if (min > max) {
            result.add(null);
            return result;
        }

        if (min == max) {
            result.add(new TreeNode(min));
            return result;
        }

        for (int i = min; i <= max; i++) {
            List<TreeNode> lefts = dfs(min, i - 1);
            List<TreeNode> rights = dfs(i + 1, max);
            for (TreeNode left: lefts) {
                for (TreeNode right: rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        return result;
    }
}
