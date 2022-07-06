package Array;
import PreDefinedClass.ListNode;

/*
83. Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 */

/*
different from the RemoveDuplicateArray, no watch ahead.
 */

public class RemoveDuplicateList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) cur.next = cur.next.next;
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicateList tester = new RemoveDuplicateList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);;
        tester.deleteDuplicates(head);
    }


}
