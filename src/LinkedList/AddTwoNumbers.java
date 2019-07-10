package LinkedList;
import PreDefinedClass.*;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int count1 = 1, count2 = 1;
        ListNode cur1 = l1;
        while (cur1.next != null) {
            cur1 = cur1.next;
            count1++;
        }
        ListNode cur2 = l2;
        while (cur2.next != null) {
            cur2 = cur2.next;
            count2++;
        }
        boolean flag = (count1 >= count2) ? true : false;
        if (flag) {
            for (int i = count2; i < count1; i++) {
                ListNode temp = new ListNode(0);
                cur2.next = temp;
                cur2 = temp;
            }
            cur2.next = null;
        } else {
            for (int i = count1; i < count2; i++) {
                ListNode temp = new ListNode(0);
                cur1.next = temp;
                cur1 = temp;
            }
            cur1.next = null;
        }
        if (flag) {
            int res = helper(l1, l2, 0);
            if (res == 1) {
                ListNode temp = new ListNode(res);
                cur1.next = temp;
                temp.next = null;
            }
            return l1;
        }
        else {
            int res = helper(l2, l1, 0);
            if (res == 1) {
                ListNode temp = new ListNode(res);
                cur2.next = temp;
                temp.next = null;
            }
            return l2;
        }
    }

    private int helper(ListNode l1, ListNode l2, int carry) {
        if (l1.next == null) {
            int res = l1.val + l2.val + carry;
            l1.val = res%10;
            return res/10;
        }
        int res = l1.val + l2.val + carry;
        l1.val = res%10;
        return helper(l1.next, l2.next, res/10);
    }
}
