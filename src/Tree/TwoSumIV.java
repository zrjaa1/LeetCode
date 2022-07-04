package Tree;
/**
 * 653. Two Sum IV - Input is a BST: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * Easy
 *
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 */

import java.util.Stack;

/**
 * O(T) = O(n), O(S) = O(n) with sorted array, while we could optimize it to O(logn) with 2 stacks.
 */
public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> leftStack = initializeLeftStack(root);
        Stack<TreeNode> rightStack = initializeRightStack(root);
        int left = root.val;
        int right = root.right != null ? root.right.val : 0;
        while (!leftStack.empty() && !rightStack.empty()) {
            TreeNode leftNode = leftStack.peek();
            TreeNode rightNode = rightStack.peek();
            if (leftNode == rightNode) {
                return false;
            }
            if (leftNode.val + rightNode.val == k) {
                return true;
            } else if (leftNode.val + rightNode.val < k) {
                leftPlus(leftStack);
            } else {
                rightMinus(rightStack);
            }
        }

        // Not necessary, as program will never reach here, but needed for compiler.
        return false;
    }

    private Stack<TreeNode> initializeLeftStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        return stack;
    }

    private Stack<TreeNode> initializeRightStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.right;
        }
        return stack;
    }

    private void leftPlus(Stack<TreeNode> leftStack) {
        TreeNode cur = leftStack.peek();
        leftStack.pop();
        cur = cur.right;
        while (cur != null) {
            leftStack.push(cur);
            cur = cur.left;
        }
    }

    private void rightMinus(Stack<TreeNode> rightStack) {
        TreeNode cur = rightStack.peek();
        rightStack.pop();
        cur = cur.left;
        while (cur != null) {
            rightStack.push(cur);
            cur = cur.right;
        }
    }
}
