package Tree;

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

/**
 * dfs: if the subtree is a uni value subtree for a given target.
 * 要牢记dfs定义的核心，如果中间出现了偏离，结果就会出错
 * 即使返回false，也可能是count++，因为subtree可能是uniValue Tree，但不等于target。具体见code
 */
public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = new int[1]; // avoid the usage of global variable
        dfs(root, root.val, count);
        return count[0];
    }

    // return if the subtree is the univalue subtree with given value "target". Meanwhile count the number of uniValue subtrees along the way.
    private boolean dfs(TreeNode root, int target, int[] count) {
        if (root == null) {
            return true;
        }

        boolean isLeftUnival = dfs(root.left, root.val, count); // 这里不用target的原因是：target只对当前层有限定，（例如case [1,1,1,5,5,null,5])
        boolean isRightUnival = dfs(root.right, root.val, count);
        if (!isLeftUnival || !isRightUnival) {
            return false;
        }

        // as long as above succeed, this subtree is a univalueTree, although it might not be for the given target.
        count[0]++;
        return root.val == target;
    }

    /**
     * This solution won't work for case [1,1,1,5,5,null,5], as the definition of this function has been changed during the mid of execution
     */
    private int dfsInt(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }

        if (root.val == target && (root.left == null || root.left.val == target) && (root.right == null || root.right.val == target)) {
            return 1 + dfsInt(root.left, target) + dfsInt(root.right, target);
        } else if (root.left != null && root.right != null) {
            return dfsInt(root.left, root.left.val) + dfsInt(root.right, root.right.val); // we should not change the target to left val or right val, since this breaks our definition of this function
        } else if (root.left == null && root.right != null) {
            return dfsInt(root.right, root.right.val);
        } else if (root.right == null && root.left != null) {
            return dfsInt(root.left, root.left.val);
        } else {
            return 1;
        }
    }
}
