package BinaryTree;

/**
 * 333. Largest BST Subtree: https://leetcode.com/problems/largest-bst-subtree/
 * Medium
 *
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
 *
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *
 * The left subtree values are less than the value of their parent (root) node's value.
 * The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
 * Example 2:
 *
 * Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -104 <= Node.val <= 104
 */
public class LargestBSTSubtree {
    class Result {
        int min;
        int max;
        int size;

        public Result(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        int[] max = new int[1];
        dfs(root, max);
        return max[0];
    }

    private Result dfs(TreeNode root, int[] max) {
        // not returning null otherwise would be confused with the case that the subtree is not a valid BST
        if (root == null) {
            return new Result(0, 0, 0);
        }

        Result leftResult = dfs(root.left, max);
        Result rightResult = dfs(root.right, max);
        // if the left/right node exists but the subtree represented by left/right node is not a valid subtree
        if (leftResult == null || rightResult == null) {
            return null;
        }

        // it's a valid BST subtree
        if ((leftResult.size == 0 || root.val > leftResult.max) && (rightResult.size == 0 || root.val < rightResult.min)) {
            int size = leftResult.size + 1 + rightResult.size;
            max[0] = Math.max(max[0], size);
            return new Result(leftResult.size != 0 ? leftResult.min : root.val, rightResult.size != 0 ? rightResult.max : root.val, size);
        }

        // not a valid BST subtree because the root.val does not match the BST rule
        return null;
    }
}
