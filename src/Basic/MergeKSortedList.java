package Basic;
import Util.*;
import java.util.*;

/*
23. Merge k Sorted Lists
Hard

2193

139

Favorite

Share
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */

/*
sol1. recursively use mergeTwo
sol2. MaxHeap
 */

public class MergeKSortedList {
    /* sol1
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = mergeTwoLists(res, lists[i]);
        }
        return res;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    */

    // sol2
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        // Queue<ListNode> minHeap = new PriorityQueue<>((ListNode s1, ListNode s2) -> s1.val - s2.val < 0 ? -1 : 1);
        Queue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare (ListNode s1, ListNode s2){
                return s1.val - s2.val;
            }
        });
        for (ListNode node : lists) {
            while (node != null) {
                minHeap.offer(node);
                node = node.next;
            }
        }
        ListNode head = null, cur = null;
        while (minHeap.size() != 0) {
            if (head == null) {
                head = minHeap.poll();
                cur = head;
            } else {
                cur.next = minHeap.poll();
                cur = cur.next;
            }
        }
        if (cur != null) cur.next = null;
        return head;
    }
}
