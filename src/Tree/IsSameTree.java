package Tree;

// *Leetcode* #100: https://leetcode.com/problems/same-tree

public class IsSameTree {
	class Solution {
	    public boolean isSameTree(TreeNode leftNode, TreeNode rightNode) {
	        if (leftNode == null && rightNode == null) return true;
	        if (leftNode == null || rightNode == null) return false;
	        if (leftNode.val != rightNode.val) return false;
	        
	        return isSameTree(leftNode.left, rightNode.left) && isSameTree(leftNode.right, rightNode.right);
	    }
	}
}
