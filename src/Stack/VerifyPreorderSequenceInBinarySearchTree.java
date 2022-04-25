package Stack;

/**
 * 255. Verify Preorder Sequence in Binary Search Tree： https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 * Medium
 *
 * Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence of a binary search tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [5,2,1,3,6]
 * Output: true
 * Example 2:
 *
 * Input: preorder = [5,2,6,1,3]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 104
 * 1 <= preorder[i] <= 104
 * All the elements of preorder are unique.
 *
 *
 * Follow up: Could you do it using only constant space complexity?
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Keep a lower bound in a sorted stack in descending order(from bot to top of stack).
 */
public class VerifyPreorderSequenceInBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }

        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int n : preorder) {
            if (n < low) return false;

            // stack from bottom to top: decreasing order
            while (!stack.isEmpty() && n > stack.peek()) {
                low = stack.pop();
            }
            stack.push(n);
        }

        return true;
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }

        for (int i = 0; i < postorder.length / 2; i++) {
            int temp = postorder[i];
            postorder[i] = postorder[postorder.length - i - 1];
            postorder[postorder.length - i - 1] = temp;
        }

        int high = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int n: postorder) {
            if (n > high) {
                return false;
            }

            while (!stack.isEmpty() && n < stack.peek()) {
                high = stack.pop();
            }
            stack.push(n);
        }
        return true;
    }

    public static void main(String[] args) {
        VerifyPreorderSequenceInBinarySearchTree tester = new VerifyPreorderSequenceInBinarySearchTree();
        int[] postOrderSequence = {1, 3, 2, 6, 5};
        boolean res = tester.verifyPostorder(postOrderSequence);
        System.out.println(res);
    }

}
