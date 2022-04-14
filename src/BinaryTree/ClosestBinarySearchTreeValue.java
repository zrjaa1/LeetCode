package BinaryTree;

/**
 * 270. Closest Binary Search Tree Value: https://leetcode.com/problems/closest-binary-search-tree-value/
 * Easy
 *
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 * Example 2:
 *
 * Input: root = [1], target = 4.428571
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 109
 * -109 <= target <= 109
 */
class ClosestBinarySearchTreeValue {
 public int closestValue(TreeNode root, double target) {
     if (root == null) return Integer.MIN_VALUE;

     int res = root.val;
     TreeNode cur = root;
     // like DFS
     while(cur != null) {
         if (Math.abs((double)res - target) > Math.abs((double)cur.val - target)) res = cur.val;
         if (cur.val <= (int)target) cur = cur.right;
         else cur = cur.left;
     }
     return res;
 }
}
