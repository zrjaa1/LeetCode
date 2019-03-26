package LinkedList;
import java.util.*;

/*
138. Copy List with Random Pointer
Medium

1384

401

Favorite

Share
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.



Example 1:



Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 */

/*
my sol:用HashMap，two pass，第一次pass复制node并且利用hashmap建立原来node与复制node的映射，第二次pass通过hashmap来完成random的链接, Time & Space -> O(n)
best sol: Space O(1), 3 pass.
          1. 第一次遍历，在每个node之后加入一个新的copy node，形成 1->1'->2->2'
          2. 第二次遍历，复制random node的连接
          3. 第三次遍历，断开，恢复成1->2和1'->2'
 */
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
public class CopyListWithRandomPointer {
    /* my sol
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node copy = new Node(0, null, null);
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            copy = new Node(cur.val, cur.next, cur.random);
            if (prev != null) prev.next = copy;
            prev = copy;
            map.put(cur, copy);
            cur = cur.next;
        }
        if (prev != null) prev.next = null;
        cur = head;
        while (cur != null) {
            copy = map.get(cur);
            copy.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
    */

    // best solution
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node copy = new Node(cur.val, null, null);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            if (cur.random != null) copy.random = cur.random.next;
            cur = copy.next;
        }
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            Node next = copy.next;
            cur.next = copy.next;
            if (next != null) copy.next = next.next;
            cur = next;
        }
        return newHead;
    }
}
