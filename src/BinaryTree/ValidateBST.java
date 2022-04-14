package BinaryTree;

// *LeetCode* #98
/*
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.
 */

// *Thoughts*
// sol1: DFS globally carrying min max
/*
public class Validate BST {
	public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBST(root.left, Long.MIN_VALUE, root.val) &&
               isValidBST(root.right, root.val, Long.MAX_VALUE);
    }

    // overloading
    private boolean isValidBST(TreeNode root, long lowerBound, long upperBound) {
        if (root == null) return true;

        if (root.val <= lowerBound || root.val >= upperBound) return false;
        return isValidBST(root.left, lowerBound, root.val) &&
                isValidBST(root.right, root.val, upperBound);
    }
}
*/

// sol2: bottom up, record the min max value of my subtree
public class ValidateBST {
	public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        long[] leftBound;
        long[] rightBound;

        if (root.left == null) {
            rightBound = helper(root.right);
            if (rightBound == null) return false;
            else if (root.val >= rightBound[0]) return false;
            else return true;
        } else if (root.right == null) {
            leftBound = helper(root.left);
            if (leftBound == null) return false;
            else if (root.val <= leftBound[1]) return false;
            else return true;
        } else {
            leftBound = helper(root.left);
            rightBound = helper(root.right);

            if (leftBound == null || rightBound == null) return false;
            if (root.val <= leftBound[1] || root.val >= rightBound[0]) return false;
            else return true;
        }
    }

    // this function assume root != null
    private long[] helper(TreeNode root) {
        long[] rightBound;
        long[] leftBound;
        if (root.left == null && root.right == null) return new long[] {root.val, root.val};
        else if (root.left == null) {
            rightBound = helper(root.right);
            if (rightBound == null || root.val >= rightBound[0]) return null;
            else return new long[] {root.val, rightBound[1]};
        } else if (root.right == null) {
            leftBound = helper(root.left);
            if (leftBound == null || root.val <= leftBound[1]) return null;
            else return new long[] {leftBound[0], root.val};
        } else {
            leftBound = helper(root.left);
            rightBound = helper(root.right);
            if (leftBound == null || rightBound == null || root.val <= leftBound[1] || root.val >= rightBound[0]) return null;
            else return new long[] {leftBound[0], rightBound[1]};
        }
    }

    // sol3: Use prev pointer
    public boolean isValidBSTWithPrev(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return true;
        }

        if (!isValidBSTWithPrev(root.left, prev)) {
            return false;
        }

        if (prev[0] != null && root.val <= prev[0].val) {
            return false;
        }
        prev[0] = root;

        if (!isValidBSTWithPrev(root.right, prev)) {
            return false;
        }
        return true;
    }
}
