package BinaryTree;

/**
 * 222. Count Complete Tree Nodes: https://leetcode.com/problems/count-complete-tree-nodes/
 * Medium
 *
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */

/**
 * 1. 画出或了解CompleteTree和FullTree之间的关系之后，这道题就变得容易了
 * 2. 在Tree的recursion中，如果走两边，层数为O(n), 若只走一边，层数则为O(logn)
 */
public class CountCompleteTreeNodes {
    // O(T) = O(logn * logn)
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int heightLeft = getHeight(root.left);
        int heightRight = getHeight(root.right);

        // Left is a full tree
        if (heightLeft == heightRight) {
            return 1 + ((1 << heightLeft) - 1) + countNodes(root.right);
            // Right is a full tree
        } else if (heightLeft == heightRight + 1) {
            return 1 + countNodes(root.left) + ((1 << heightRight) - 1);
        } else {
            throw new IllegalArgumentException("Not a complete tree");
        }
    }

    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
