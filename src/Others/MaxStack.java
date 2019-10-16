package Others;
import java.util.*;
import Util.DListNode;

/*
716. Max Stack
Easy

276

52

Favorite

Share
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
 */

/*
用一个treeMap来sort，用DLinkedList来代表stack
本题主要学习了TreeMap的应用，TreeMap相当于一个balanced binary tree，默认按照key的大小sort，因此找到最大值、最小值都是O（logn），添加、删除节点也是O（logn）
在push、pop等操作中，都需要同时更新map和list。
push: map -> O(logn) (write：sort) 或者O（1）（已经有了，直接添加到尾部）, list -> O(1)
pop: list -> O(1)找到，map -> O(logn)如果需要删除key或O（1）
peek/peekMax: O(1)
popMax: map -> O(logn)找到最大，O（logn)删除key或O（1）删一个元素，list O（1）删一个元素（doubly linked list）
需要注意的点：
1. 建立Double linked list的时候，记得要把head.next, head.prev都指向head
2. 在map的value field中存的List<DListNode>只是一个ArrayList，节点之间没有连接，有连接的地方是在我们等效于stack的Doubly linked list中
 */
class MaxStack {
    TreeMap<Integer, List<DListNode>> map;
    DListNode head;
    /** initialize your data structure here. */
    public MaxStack() {
        map = new TreeMap<>();
        head = new DListNode(0);
        head.prev = head;
        head.next = head;
    }

    public void push(int x) {
        DListNode node = new DListNode(x);
        List<DListNode> list;
        if (!map.containsKey(x)) list = new ArrayList<>();
        else list = map.get(x);
        list.add(node);
        map.put(x, list);
        node.addNodeHead(node, head);
    }

    public int pop() {
        DListNode node = head.next;
        if (node == head) return 0;
        head.removeNode(node);
        List<DListNode> list = map.get(node.val);
        if (list.size() == 1) map.remove(node.val);
        else list.remove(list.size()-1);
        return node.val;
    }

    public int top() {
        return head.next.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        List<DListNode> list = map.get(map.lastKey());
        DListNode last = list.get(list.size()-1);
        if (list.size() == 1) map.remove(last.val);
        else list.remove(list.size()-1);
        last.removeNode(last);
        return last.val;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */