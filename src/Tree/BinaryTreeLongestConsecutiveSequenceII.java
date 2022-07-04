package Tree;

/**
 * 549. Binary Tree Longest Consecutive Sequence II: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
 * Medium
 *
 * Given the root of a binary tree, return the length of the longest consecutive path in the tree.
 *
 * A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.
 *
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * Example 2:
 *
 *
 * Input: root = [2,1,3]
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 */

/**
 * Key of this problem is to understand how the increasing sequence and decreasing sequence could combine as a consecutive sequence,
 * also, insist on the definition of dfs. In recursion of tree, the return value usually helps the upper layer to solve problems.
 */
public class BinaryTreeLongestConsecutiveSequenceII {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[1];
        dfs(root, max);
        return max[0];
    }

    // return the length of the longest consecutive sequence starting from root
    // int[0] = length of increasing sequence, int[1] = length of decreasing sequence
    private int[] dfs(TreeNode root, int[] max) {
        if (root == null) {
            return new int[] {0, 0};
        }

        int lenIncreasing = 1;
        int lenDecreasing = 1;
        int[] leftResult = dfs(root.left, max);
        int[] rightResult = dfs(root.right, max);

        // determine the returning value, insist on the dfs definition
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                lenIncreasing += leftResult[0];
            } else if (root.left.val == root.val - 1) {
                lenDecreasing += leftResult[1];
            }
        }

        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                lenIncreasing = Math.max(lenIncreasing, 1 + rightResult[0]);
            } else if (root.right.val == root.val - 1) {
                lenDecreasing = Math.max(lenDecreasing, 1 + rightResult[1]);
            }
        }

        // combine increasing and decreasing sequence to a consecutive sequence
        int len = 1;
        if (lenIncreasing != 1 && lenDecreasing != 1) {
            len = lenIncreasing + lenDecreasing - 1;
        } else if (lenIncreasing != 1) {
            len = lenIncreasing;
        } else if (lenDecreasing != 1) { // lenDecreasing == 1
            len = lenDecreasing;
        }

        max[0] = Math.max(max[0], len);
        return new int[] {lenIncreasing, lenDecreasing};
    }
}
