package BinaryTree;

/**
 * 250. Count Univalue Subtrees: https://leetcode.com/problems/count-univalue-subtrees/
 * Medium

 * Given the root of a binary tree, return the number of uni-value subtrees.
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,5,5,5,null,5]
 * Output: 4
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [5,5,5,5,5,null,5]
 * Output: 6
 *
 *
 * Constraints:
 *
 * The number of the node in the tree will be in the range [0, 1000].
 * -1000 <= Node.val <= 1000
 */
public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = new int[1]; // avoid the usage of global variable
        dfs(root, 0, count);
        return count[0];
    }

    // return if the subtree is the univalue subtree with given value "target".
    private boolean dfs(TreeNode root, int target, int[] count) {
        if (root == null) {
            return true;
        }

        boolean isLeftUnival = dfs(root.left, root.val, count);
        boolean isRightUnival = dfs(root.right, root.val, count);
        if (!isLeftUnival || !isRightUnival) {
            return false;
        }

        // as long as above succeed, this subtree is a univalueTree, although it might not be for the given target.
        count[0]++;
        return root.val == target;
    }
}
