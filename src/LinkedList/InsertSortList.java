package LinkedList;
import Util.ListNode;

public class InsertSortList {
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode curr = head.next;
            ListNode next = head;
            ListNode curhead = head;
            while(curr != null) {
                next = curr.next;
                curr.next = null;
                curhead = insert(curhead, curr);
                curr = next;
            }
            return curhead;
        }
        //head is List from head to curr
        //curr is what to insert
        private ListNode insert(ListNode head,ListNode newNode) {
            ListNode prev = head;
            if (head.next == null || head.val > newNode.val) {
                newNode.next = head;
                return newNode;
            }
            while (prev.next.val < newNode.val && prev.next != null) {
                prev = prev.next;
            }
            prev.next = newNode;
            newNode.next = prev.next;
            return head;
        }

        public static void main(String[] args) {
            InsertSortList tester = new InsertSortList();
            ListNode head = new ListNode(2);
            head.next = new ListNode(1);
            head.next.next = new ListNode(4);
            head.next.next.next = new ListNode(3);
            tester.insertionSortList(head);
        }
}
