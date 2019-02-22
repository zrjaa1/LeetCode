package LinkedList;
import Util.ListNode;

public class ReorderList {
	public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        cur = head;
        for (int i = 0; i < count/2-1; i++) {
            cur = cur.next;
        }
        ListNode temp = new ListNode(0);
        if (count % 2 == 0) {
            temp = cur.next;
            cur.next = null;
        } else {
            temp = cur.next.next;
            cur.next.next = null;
        }
        cur = reverseLinkedList(temp);
        head = mergeTwoLinkedList(head, cur);
    }

    private ListNode reverseLinkedList(ListNode head) {
        if (head.next == null) return head;
        ListNode res = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    public ListNode mergeTwoLinkedList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1.next != null && cur2.next != null) {
            ListNode temp1 = cur1.next;
            ListNode temp2 = cur2.next;
            cur1.next = cur2;
            cur2.next = temp1;
            cur1 = temp1;
            cur2 = temp2;
        }
        if (cur1.next == null) cur1.next = cur2;
        else cur2.next = cur1;
        return l1;
    }
    
    public static void main(String[] args) {
    	ReorderList tester = new ReorderList();
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(4);
    	head.next.next.next.next = new ListNode(5);
    	tester.reorderList(head);
    }
}
