package BinaryTree;
import java.util.ArrayList;
import java.util.Stack;

// *LeetCode* #94
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

// *Thoughts*
// sol1: recursion
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

// sol2: iteration
	// iteration, always go left, decide when to add value. pre-order traverse: when you meet to node; in-order: when you pop from the stack.
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
}
