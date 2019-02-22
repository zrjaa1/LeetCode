package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

// *LeetCode* 145

public class BinaryTreePostorderTraversal {
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList() {};
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
}


// iteration
class Solution {
    public LinkedList<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while ( !stack.empty() || root != null) {
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
}
