package Tree;

/*
236. Lowest Common Ancestor of a Binary Tree: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
Medium

1670

130

Favorite

Share
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]




Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
 */

/*
Thought: Recursion. 1.如果root是其中一个，直接return root（这导致了另一个不在这个tree中，仍然得出了错误结果的可能性，需要make sure q、p
                      一定在tree中 -> pre-processing检查是否包含p&q）。
                    2.如果左边和右边都有反值，则返回当前root。
                    3.如果左边和右边有一边有，则返回那边
                    4.否则返回null

Follow up 1: find ancestor of k nodes: almost the same algorithm, add some base case
Follow up 2: find ancestor of 2 nodes in n-branch-tree: add the # of recursions, count non-null returned treenode, count == 1, return returned value, if count == 2, return root.
Follow up 3: find ancestor of k nodes in n-branch-tree: the same as above, expect count >= 2, return root.
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = null;
        if (root == null || p == null || q == null) return res;
        return contains(root, p, q);
    }

    // return a TreeNode that contains either p or q. return null if not containing any of them.
    private TreeNode contains(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = contains(root.left, p, q);
        TreeNode right = contains(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
