package Util;

class DListNode {
    int key;
    int val;
    DListNode next;
    DListNode prev;

    public static void addNodeHead(DListNode node, DListNode head) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void addNodeTail(DListNode node, DListNode tail) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    public static void removeNode(DListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static DListNode popTail(DListNode tail) {
        DListNode res = tail.prev;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        return res;
    }
}