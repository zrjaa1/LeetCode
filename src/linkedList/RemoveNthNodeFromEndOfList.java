package linkedList;

import util.ListNode;

// *LeetCode* #19
/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
*/

// *Thoughts*
// slow fast
// be careful about the edge case : n == size(LinkedList) -> fast == null

public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        if (fast == null) return head.next;
        
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // post-processing
        slow.next = slow.next.next;
        return head;
    }
}
