package BinaryTree;

/*d
297 Serialization的变种，pre-order遍历将一个binary tree in place操作变为一个doubly linkedlist，要能acess它的head和tail
use left as prev, right as next.
after processing, the head will be the first time node to visit, so we check if the prev has not been set yet, we will set the head
and the tail will be the last one to be visited, so we set the tail the same as prev.
we update the root by doing recursion, update the prev by letting prev = root.
 */
public class SerializeToDoublyLinkedList {
    TreeNode prev;
    TreeNode head;
    TreeNode tail;
    public void serializeToDoublyLinkedList(TreeNode root) {
        preOrder(root);
        tail = prev;
    }
    public void preOrder(TreeNode root) {
        if (root == null) return;
        preOrder(root.left);
        // do something
        root.left = prev;
        if (prev != null) prev.right = root;
        else head = root;
        prev = root;
        //
        preOrder(root.right);
    }

    /* if we use prev as a input parameter
    public void preOrder(TreeNode root, TreeNode[] prev) {
        if (root == null) return;
        preOrder(root.left, prev);

        //
        root.left = prev[0];
        if (prev != null) prev[0].right = root;
        else head = root;
        prev[0] = root;
        //
        preOrder(root.right, prev);
    }
     */
}
