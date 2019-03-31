package Util;

// 首尾未相连
public class DListNode {
    public int val;
    public DListNode next;
    public DListNode prev;

    public DListNode(int x) {
        val = x;
    }
    public static void addNodeHead(DListNode head, DListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void addNodeTail(DListNode tail, DListNode node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    public static DListNode sortInsert(DListNode head, DListNode node) {
        if (head == null) return node;
        if (node.val < head.val) {
            node.next = head;
            head.prev = node;
            return node;
        } else {
            DListNode cur = head;
            while (cur.next != null && cur.next.val < node.val) {
                cur = cur.next;
            }
            if (cur.next == null) {
                cur.next = node;
                node.prev = cur;
            } else {
                addNodeHead(cur, node);
            }
            return head;
        }
    }

    public static void removeNode(DListNode node) {
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public static DListNode popTail(DListNode tail) {
        DListNode res = tail.prev;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        return res;
    }
}