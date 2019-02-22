package linkedList;

import util.ListNode;

public class ReverseLinkedListBy2 {
	public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
      
		ListNode subHead = swapPairs(head.next.next);
        ListNode newHead = head.next;
		head.next = subHead;
		newHead.next = head;
		return newHead;
    }
}
