package linkedList;

import util.ListNode;

// *Leetcode* #25
/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

// *Thoughts*
// use recursion to partition, then iteratively reverse the first part (like reverse linked list)

public class ReverseLinkedListByK {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode cur = head;
        boolean terminate = false;
        int times = k;
        while (times != 0) {
            if (cur == null) {
                terminate = true;
                break;
            }
            cur = cur.next;
            times--;
        }
        
        if (terminate == true) return head;
        
        ListNode subHead = reverseKGroup(cur, k);
        // iteratively reverse sub List
        ListNode newCur = head;
        ListNode prev = null;
        ListNode next = head.next;
        while (newCur != cur) {
            next = newCur.next;
            newCur.next = prev;
            prev = newCur;
            newCur = next;
        }
        
        head.next = subHead;
        
        return prev;
    }
	
	public static void main(String[] args) {
		ReverseLinkedListByK test = new ReverseLinkedListByK();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		ListNode res = test.reverseKGroup(head, 2);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}
