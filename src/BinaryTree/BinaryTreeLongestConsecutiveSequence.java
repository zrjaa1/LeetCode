package BinaryTree;

/**
 * 298. Binary Tree Longest Consecutive Sequence: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 * Medium

 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 *
 *
 * Input: root = [2,null,3,2,null,1]
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 */
public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[1];
        dfs(root, max);
        return max[0];
    }

    // return the length of the longest consecutive sequence starting from root
    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int len = 1;
        int leftLen = dfs(root.left, max);
        int rightLen = dfs(root.right, max);
        if (root.left != null && root.left.val == root.val + 1) {
            len = leftLen + 1;
        }

        if (root.right != null && root.right.val == root.val + 1) {
            len = Math.max(len, rightLen + 1);
        }
        max[0] = Math.max(max[0], len);
        return len;
    }
}
