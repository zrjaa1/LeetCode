package linkedList;

import util.ListNode;
// *LeetCode* #141
/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

// *Thoughts*
// Sol 1: HashSet
// Sol 2: 2 pointer: fast and slow.
public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) return false;  
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
