package BinaryTree;

import java.util.Stack;

// *Leetcode* #173
/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

// *Thoughts*
// use the logic stack structure, similar to Two Summary BST.


public class BinarySearchTreeIterator {
	private Stack<TreeNode> s;
 
    public BinarySearchTreeIterator(TreeNode root) {
        s = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.empty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode result = s.pop();
        if (result.right != null) {
            TreeNode cur = result.right;
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
        }
        return result.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
