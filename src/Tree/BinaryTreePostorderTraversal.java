package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal: https://leetcode.com/problems/binary-tree-postorder-traversal/
 * Easy
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

public class BinaryTreePostorderTraversal {
    // Sol1: recursion
    public ArrayList<Integer> postorderTraversalRecursion(TreeNode root) {
        if (root == null) return new ArrayList() {
        };
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode cur, ArrayList<Integer> res) {
        if (cur == null) return;
        helper(cur.left, res);
        helper(cur.right, res);
        res.add(cur.val);
    }

    // Sol2: Iteration using stack
    public LinkedList<Integer> postorderTraversalIteration(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root == null) {
                root = stack.pop();
                root = root.left;       //reverse of pre-order
            } else {
                stack.push(root);
                res.addFirst(root.val); //reverse of pre-order
                root = root.right;      //reverse of pre-order
            }
        }
        return res;
    }

    //Sol3: Using stack + Prev
    public List<Integer> postorderTraversalWithPrev(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (prev == null || prev.left == cur || prev.right == cur) { // case 1, the first time a tree node gets into the stack
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    res.add(cur.val);
                    stack.pop();
                }
            } else if (prev == cur.left) { // case 2, get back from left child
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            } else if (prev == cur.right) { // case 3, get back from right child
                res.add(cur.val);
                stack.pop();
            }

            prev = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreePostorderTraversal tester = new BinaryTreePostorderTraversal();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        List<Integer> res = tester.postorderTraversalWithPrev(root);
        for (Integer i : res) {
            System.out.print(i);
        }
    }
}
