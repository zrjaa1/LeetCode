package BinaryTree;

// *Leetcode* #110: https://leetcode.com/problems/balanced-binary-tree
/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.
*/

// *Thoughts*
// use getHeight, if the difference > 1, return false. Corner case: null return true.
class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getHeight(root.left);
        if (left == -1) return false;
        int right = getHeight(root.right);
        if (right == -1) return false;
        if (Math.abs(left-right) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);  
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (Math.abs(left-right) > 1) return -1;
        return Math.max(left,right)+1;
    }
}
