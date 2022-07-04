package Tree;
/*
114. Flatten Binary Tree to Linked List: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
Medium

1292

161

Favorite

Share
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */

/*
sol1: similar to "SerializeToDoublyLinkedList", use post-order traverse and a global prev. (since we want to change the left/right, we want recursion first, then do something,
use system stack to record the sequence of traversing).
需要特别注意的是，这种in-place操作需要保证，先访问要修改的child，再进行修改，否则会进入无限循环。如果没有把握的话，直接选用post-order是最保险的。
如何思考：例如本题需要的是 root.right = left, 如果用pre-oder: root, left, right, 首先无法直接用prev，其次哪怕用prev.right = root也是错误的，因为会导致无限循环
而如果使用变形版post-order：right, left, root，那么可以直接进行root.right = prev的操作，逻辑上也很简单。
sol2: use divide and conquer (pure recursion)
 */
public class FlattenBinaryTreeToLinkedList {
    // sol1: post-order
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode[] prev = new TreeNode[1];
        postOrder(root, prev);
    }


    private void postOrder(TreeNode node, TreeNode[] prev) {
        if (node == null) return;

        postOrder(node.right, prev);
        postOrder(node.left, prev);
        node.right = prev[0];
        node.left = null;
        prev[0] = node;
    }

    // sol2 标准recursion，自己想出来的
    /*
    public void flatten(TreeNode root) {
        if (root == null) return;
        helper(root);
    }
    private TreeNode helper(TreeNode root) {
        if (root == null) return root;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        if (left != null) {
            root.right = left;
            left.left = null;
            while (left.right != null) left = left.right;
            if (right != null) {
                left.right = right;
                left.left = null;
            }
        }
        root.left = null;
        return root;
    }
     */

    /*
    // sol3 一个很精妙的recursion，hard to understand
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode[] prev = new TreeNode[1];
        postOrder(root, null);
    }
    private TreeNode postOrder(TreeNode node, TreeNode tail) {
        if (node == null) return tail;
        node.right = postOrder(node.left, postOrder(node.right, tail));
        node.left = null;
        return node;
    }
    */
}
