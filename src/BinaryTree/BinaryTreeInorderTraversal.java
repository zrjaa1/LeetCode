package BinaryTree;
import java.util.ArrayList;
import java.util.Stack;

/** *LeetCode* #94: https://leetcode.com/problems/binary-tree-inorder-traversal/
/*
 * Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

 */

/**
 *  *Thoughts*
 *  sol1: recursion
 */

public class BinaryTreeInorderTraversal {
	// recursion
	/*
	class Solution {
	    public List<Integer> inorderTraversal(TreeNode root) {
	        if (root == null) return new ArrayList() {};
	        ArrayList<Integer> res = new ArrayList<Integer>();
	        helper(root, res);
	        return res;
	    }
	    private void helper(TreeNode cur, ArrayList<Integer> res) {
	            if (cur == null) return;
	            helper(cur.left, res);
	            res.add(cur.val);
	            helper(cur.right, res);
	    }
	}
	*/

	// sol2: iteration with stack. Always go left, decide when to add value. pre-order traverse: when you meet to node; in-order: when you pop from the stack.
	class Solution {
	    public ArrayList<Integer> inorderTraversal(TreeNode root) {
	        ArrayList<Integer> res = new ArrayList<Integer>();
	        if (root == null) return res;

	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        while ( !stack.empty() || root != null) {
	            if (root == null) {
	                root = stack.pop();
	                res.add(root.val);
	                root = root.right;
	            } else {
	                stack.push(root);
	                root = root.left;
	            }
	        }
	        return res;
	    }
	}

	// Sol3: Iteration using stack and prev. This works better for post order traversal, not recommended for pre and in order.
	public ArrayList<Integer> inorderTraversalPrev(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
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
				} else {
					res.add(cur.val);
					stack.pop();
					if (cur.right != null) {
						stack.push(cur.right);
					}
				}
			} else if (prev == cur.left) { // case 2, get back from left child
				res.add(cur.val);
				stack.pop();
				if (cur.right != null) {
					stack.push(cur.right);
				}
			} else if (prev == cur.right) { // case 3, get back from right child
				stack.pop();
			} else { // other cases, [3, 1, null, null, 2], prev = 2, cur = 3, they have no relationship.
				res.add(cur.val);
				stack.pop();
				if (cur.right != null) {
					stack.push(cur.right);
				}
			}

			prev = cur;
		}
		return res;
	}
}
