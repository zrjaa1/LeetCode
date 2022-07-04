package Tree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
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
// iteration
    class Solution {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<Integer>();
            if (root == null) return res;
            
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while ( !stack.empty() || root != null) {
                if (root == null) {
                    root = stack.pop();
                    root = root.right;       //reverse of pre-order
                } else {
                    stack.push(root);
                    res.add(root.val); //reverse of pre-order
                    root = root.left;      //reverse of pre-order
                }
            }
            return res; 
        }
    }
}
