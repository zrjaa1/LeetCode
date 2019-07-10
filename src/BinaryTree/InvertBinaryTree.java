package BinaryTree;

// *Leetcode* 226
/*
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can��t invert a binary tree on a whiteboard so f*** off.
 */

//Sol1: standard recursion
/*
 * 
class InvertBinaryTree {
 public TreeNode invertTree(TreeNode root) {
     if (root == null) return root;
     TreeNode newRight = invertTree(root.left);
     TreeNode newLeft = invertTree(root.right);
     root.left = newLeft;
     root.right = newRight;
     return root;
 }
}
*/
//Sol2: DFS recursion
class InvertBinaryTree {
 public TreeNode invertTree(TreeNode root) {
     if (root == null) return root;
     TreeNode tempNode = root.left;
     root.left = root.right;
     root.right = tempNode;
     invertTree(root.left);
     invertTree(root.right);
     return root;
 }
}