package Tree;
import java.util.*;

/*
113. Path Sum II: https://leetcode.com/problems/path-sum-ii/
Medium

754

29

Favorite

Share
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */

/*
Recursion，在做的过程中写出了一些bug：
1. base case 是leaf node而不是root == null && sum == 0, 这样会让上一层的left、right child都符合条件（2条path），而实际只需要一条path
2. 因为base case的更改，导致了只有一边不为null时，仍然试图进两边recursion的nullPointerException
 */

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> sol = new ArrayList<>();
        helper(root, sum, sol, res);
        return res;
    }

    private void helper(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res) {
        if (root.left == null && root.right == null) {
            if  (sum == root.val) {
                sol.add(root.val);
                List<Integer> temp = new ArrayList<>(sol);
                res.add(temp);
            }
            return;
        }
        sol.add(root.val);
        if (root.left != null) {
            List<Integer> leftSol = new ArrayList<>(sol);
            helper(root.left, sum - root.val, leftSol, res);
        }
        if (root.right != null) {
            List<Integer> rightSol = new ArrayList<>(sol);
            helper(root.right, sum - root.val, rightSol, res);
        }
    }
}
