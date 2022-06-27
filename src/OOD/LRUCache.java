package OOD;
import java.util.*;

/**
146. LRU Cache
Hard

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */

/**
Thoughts: 用两个数据结构，Doubly Linked List 和 HashMap<Integer, DListNode>，其中：
          Doubly Linked List用来记录key和val（同时），然后以frequency为priority排序
          HashMap用来通过key直接找到DListNode
          重点在于Doubly Linked List的一些utility方法，包括：addNodeHead, addNodeTail, popTail, removeNode
          LRUCache Class中需要有Head和Tail，并且在初始化node的过程中注意时刻保持node之间的相连。
 */
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

class LRUCache {
    private int count;
    private int capacity;
    private HashMap<Integer, DListNode> map;
    private DListNode head;
    private DListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        count = 0;
        map = new HashMap<>();
        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DListNode res = map.get(key);
        if (res == null) return -1;
        else {
            // update the LRU: head
            DListNode.removeNode(res);
            DListNode.addNodeHead(res, head);
        }
        return res.val;
    }

    public void put(int key, int value) {
        // check first if it exists in the map
        DListNode node = map.get(key);
        if (node == null) {
            count++;
            // if exceed the capacity, remove the last recent one first
            if (count > capacity) {
                DListNode toDelete = DListNode.popTail(tail);
                map.remove(toDelete.key);
            }
            node = new DListNode();
            node.key = key;
            node.val = value;
        } else {
            node.val = value;
            DListNode.removeNode(node);
        }
        DListNode.addNodeHead(node, head);
        map.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
