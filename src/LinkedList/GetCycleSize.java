package LinkedList;

import Util.ListNode;

// *LeetCode* #142

/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
 */

// *Thoughts*
/*
 * Sol1: HashSet || visited
 * Sol2: Mathematical:
 * 			a - the length before cycle
 * 			b - the length from start point of the cycle to the intersection of slow and fast
 * 			c - the length from the intersection back to the start point of cycle
 * 			we have:
 * 				a + b + n(b+c) = 2 (a+b)
 * 				(n-1)(b+c) + c = a
 * 			when slow == fast, we let fast = head, and slow continue.
 * 			slow = slow.next, fast = fast.next, the first time they meet, it's the start point of the cycle
 * 			(1. if fast walk "a" steps, slow must walk (n-1)(b+c)+c steps
 * 			 2. if fast walk "a" steps, it reaches the start point of the cycle
 * 			 3. if fast reaches the start point of the cycle, the slow has also reaches the start point of the cycle
 * 			 4. so the first time fast == slow, that means fast and slow are at the start point of the cycle
 */

public class GetCycleSize {
	public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        
        if (fast == null || fast.next == null) return null;
        
        fast = head;
        
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
