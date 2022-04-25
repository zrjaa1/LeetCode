package Iterator;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 281. Zigzag Iterator: https://leetcode.com/problems/zigzag-iterator/
 * Medium
 *
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 *
 * Example 1:
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 * Example 2:
 *
 * Input: v1 = [1], v2 = []
 * Output: [1]
 * Example 3:
 *
 * Input: v1 = [], v2 = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 0 <= v1.length, v2.length <= 1000
 * 1 <= v1.length + v2.length <= 2000
 * -231 <= v1[i], v2[i] <= 231 - 1
 *
 *
 * Follow up: What if you are given k vectors? How well can your code be extended to such cases?
 *
 * Clarification for the follow-up question:
 *
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
 *
 * Follow-up Example:
 *
 * Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
 * Output: [1,4,8,2,5,9,3,6,7]
 */

/**
 * Sol1: use queue, not necessary in this problem
 * Sol2: use deque, so that we could control the sequence of zigzag iterates that is needed.
 * Sol3: Use a double linked list wrapper of Iterators, more complex to write.
 */
public class ZigzagIterator {
    // Sol1: use queue
    Queue<Iterator<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (v1 != null && v1.size() != 0)
            queue.offer(v1.iterator());
        if (v2 != null && v2.size() != 0)
            queue.offer(v2.iterator());
    }

    public int next() {
        Iterator<Integer> iterator = queue.poll();
        int res = iterator.next();
        if (iterator.hasNext()) {
            queue.offer(iterator);
        }
        return res;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    // Sol2: Use Deque so that we can control the sequence of Zigzag sequence.
//    Deque<Iterator<Integer>> deque = new LinkedList<>();
//    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
//        deque = new LinkedList<>();
//        if (v1 != null && v1.size() != 0)
//            deque.offer(v1.iterator());
//        if (v2 != null && v2.size() != 0)
//            deque.offer(v2.iterator());
//    }
//
//    public int next() {
//        Iterator<Integer> iterator = deque.poll();
//        int res = iterator.next();
//        if (iterator.hasNext()) {
//            deque.offer(iterator);
//        }
//        return res;
//    }
//
//    public boolean hasNext() {
//        return !deque.isEmpty();
//    }
}
