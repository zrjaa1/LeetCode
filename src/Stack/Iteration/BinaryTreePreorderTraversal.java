package Stack.Iteration;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * sol1: recursion
 * sol2: using stack. Always go left if non-null, and pop and go right if null. Same for in-order, just when to add res is different.
 * sol3: Another stack solution. Using logical tree(containing right subtree), 在出栈时加入res list(not implemented yet)
 */
public class BinaryTreePreorderTraversal {
    /**
     * Sol1: recursion
     */
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList() {};
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }
    private void helper(TreeNode cur, ArrayList<Integer> res) {
            if (cur == null) return;
            res.add(cur.val);
            helper(cur.left, res);
            helper(cur.right, res);
    }

    /**
     * Sol2: Using stack. Always go left if non-null, and pop and go right if null. Same for in-order, just when to add res is different.
     */
    class Solution {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<>();
            if (root == null) return res;
            
            Stack<TreeNode> stack = new Stack<>();
            while ( !stack.empty() || root != null) {
                if (root == null) {
                    root = stack.pop();
                    root = root.right;
                } else {
                    stack.push(root);
                    res.add(root.val);
                    root = root.left;
                }
            }
            return res; 
        }
    }
}
