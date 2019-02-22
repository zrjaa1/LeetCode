package LinkedList;

import Util.ListNode;

// *Leetcode* #206
/*
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

// *Thoughts*
// Sol1: Iteration, be cautious about the initial value of prev and next. Return prev at last
public class ReverseLinkedList {
	public ListNode reverseListIteratively(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode prev = null;
        ListNode next = cur.next;
        
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        return prev;
    }

	public ListNode reverseListRecursively(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode res = reverseListRecursively(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}
	
	public static void main(String[] args) {
		ReverseLinkedList test = new ReverseLinkedList();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		ListNode res = test.reverseListRecursively(head);
		while(res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}
