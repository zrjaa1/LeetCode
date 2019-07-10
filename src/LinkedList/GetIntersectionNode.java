package LinkedList;

import Util.ListNode;

// *LeetCode* #160
/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

// *Thoughts*
// We can use this algorithm to solve LCA problem (only if we have parent pointer in the TreeNode)
// Sol1: HashSet || visited
// Sol2: Mathematical: 
// 			1. known the length of 2 linkedlist, vertically adjust them to the shorter one, and start together
// 			2. don't know the length of 2 linkedlist, simutaneously go forward, when reach the end of the list, 
//             transfer it to another head, continue go forward until the end of linkedlist,
//			   once ==, return, else return null.

public class GetIntersectionNode {
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA;
        ListNode curB = headB;
        
        if (curA == curB) return curA;
        while (curA != null) {
            curA = curA.next;
            if (curB == null) curB = headA;
            else curB = curB.next;
            if (curA == curB) return curA;
        }
        
        curA = headB;
        if (curA == curB) return curA;
        
        while (curA != null) {
            curA = curA.next;
            if (curB == null) curB = headA;
            else curB = curB.next;
            if (curA == curB) return curA;
        }
        return null;
    }
	
	public static void main(String[] args) {
		ListNode headA = new ListNode(2);
		ListNode headB = new ListNode(3);
		headA.next = headB;
		ListNode result = GetIntersectionNode.getIntersectionNode(headA, headB);
		System.out.println(result.val);
	}
}
