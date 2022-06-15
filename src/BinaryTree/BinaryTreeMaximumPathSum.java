package BinaryTree;

/*
124. Binary Tree Maximum Path Sum: https://leetcode.com/problems/binary-tree-maximum-path-sum/
Hard

1318

95

Favorite

Share
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */

/*
refer to MaximumAnyToRootPathSum, we prune the case that the left & right tree does not have contribution to the path sum
In the process of going back from the recursion, we can update a max to record the leaf - leaf maximum path sum.
And if we have prune here, we act like a any - any maximum path sum.
Note: if left or right < 0, set it to 0.
Note: any to any的题，只能recursion从下往上,从上往下做的方法无法cover "人"字型path
 */
public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        helper(root, res);
        return res[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = helper(root.left, max);
        int right = helper(root.right, max);
        if (left < 0) left = 0;
        if (right < 0) right = 0;
        max[0] = Math.max(max[0], left+right+root.val);
        return Math.max(left,right) + root.val;
    }
}
