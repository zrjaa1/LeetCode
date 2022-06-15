package BinaryTree;

/**
 * 285. Inorder Successor in BST: https://leetcode.com/problems/inorder-successor-in-bst/
 * Medium
 *
 * Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. If the given node has no in-order successor in the tree, return null.
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3], p = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], p = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 * All Nodes will have unique values.
 */

/**
 * Memorize <= go right, > go left, set candidate before going left.
 */
public class InOrderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            throw new IllegalArgumentException();
        }

        TreeNode candidate = null;
        while (root != null) {
            if (root.val <= p.val) {
                // candidate = root; 在这里赋值会错误，因为这里代表当前值<= target，不可能赋值。具体什么时候赋值，demo一次来试一试就知道了，实在不能理解可以记忆或者用stack来做
                root = root.right;
            } else {
                candidate = root;
                root = root.left;
            }
        }
        return candidate;
    }
}
