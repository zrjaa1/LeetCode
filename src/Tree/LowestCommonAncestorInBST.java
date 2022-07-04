package Tree;

/*
235. Lowest Common Ancestor of a Binary Search Tree: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
Easy

899

76

Favorite

Share
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]




Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.
 */

/*
sol1: Thought: similar to regular LCA, check the value of root, p, q to prune.
Sol2: Best Solution: traverse the BST, the first time a root.val is between [p.val, q.val], it is the LCA. Proof: by contradiction.
 */
public class LowestCommonAncestorInBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = null;
        if (root == null || p == null || q == null) return res;
        return contains(root, p, q);
    }

    private TreeNode contains(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left, right;
        if (root.val < p.val && root.val < q.val) left = null;
        else left = contains(root.left, p, q);
        if (root.val > p.val && root.val > q.val) right = null;
        else right = contains(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
