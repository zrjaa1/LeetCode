package BinaryTree;

/**
 * 99. Recover Binary Search Tree: https://leetcode.com/problems/recover-binary-search-tree/
 * Medium
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *
 *
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 *
 */
public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        TreeNode[] mistakes = new TreeNode[2];
        dfs(root, new TreeNode[1], mistakes);

        int temp = mistakes[0].val;
        mistakes[0].val = mistakes[1].val;
        mistakes[1].val = temp;
    }

    private void dfs(TreeNode root, TreeNode[] prev, TreeNode[] mistakes) {
        if (root == null) {
            return;
        }

        // go left
        dfs(root.left, prev, mistakes);

        // Mistakes found
        if (prev[0] != null && prev[0].val >= root.val) {
            if (mistakes[0] == null) {
                mistakes[0] = prev[0];
            }
            mistakes[1] = root;
        }
        prev[0] = root;

        // go right
        dfs(root.right, prev, mistakes);
    }
}
