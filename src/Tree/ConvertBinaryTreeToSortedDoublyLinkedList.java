package Tree;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List: https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * Medium
 *
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [4,2,5,1,3]
 *
 *
 * Output: [1,2,3,4,5]
 *
 * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 *
 * Example 2:
 *
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 * All the values of the tree are unique.
 */

/**
 * Java pass by value: 要在子函数中改变父函数的值，需要用array或list。否则不管是object还是primitive type，都不管用。Alternatively，use global variable
 */
public class ConvertBinaryTreeToSortedDoublyLinkedList {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }

        // 不能在一开始就设置prev，因为这样会无限循环
//        Node cur = root;
//        while (cur.right != null) {
//            cur = cur.right;
//        }
//        Node prev = cur;
        Node[] prev = new Node[1];
        inOrder(root, prev);
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        cur.left = prev[0];
        prev[0].right = cur;
        return cur;
    }

    private void inOrder(Node cur, Node[] prev) {
        if (cur == null) {
            return;
        }

        inOrder(cur.left, prev);

        if (prev[0] != null) {
            prev[0].right = cur;
            cur.left = prev[0];
        }
        prev[0] = cur;

        inOrder(cur.right, prev);
    }

    public static void main(String[] args) {
        ConvertBinaryTreeToSortedDoublyLinkedList tester = new ConvertBinaryTreeToSortedDoublyLinkedList();
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        Node res = tester.treeToDoublyList(root);
    }
}
