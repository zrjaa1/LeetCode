package ArrayString;
import Util.*;

public class RemoveDuplicateListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (prev.next == cur) prev = cur;
            else prev.next = cur.next;
            cur = cur.next;
        }
        return dummyNode.next;
    }
}
