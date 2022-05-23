package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * Medium
 *
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
 */

/**
 * 这道题主要考察：
 * 1. 复合数据结构：Sorted Double Link List + Map的应用，猜测LRU Cache与此类似
 * 2. Potential Optimization: Instead of updateIdx, remove the node directly and append again.
 * 3. Alternative Solution: Counter, applicable for both 2 and k cases.
 */
public class LongestSubstringAtMostK {
    class Node {
        char ch;
        int idx;
        Node prev, next;
        public Node(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
        }
    }

    class MyQueue {
        Map<Character, Node> map;
        Node head, tail;
        int k;
        public MyQueue(int k) {
            this.map = new HashMap<>(k + 1);
            this.k = k;
            head = new Node('\0', -1);
            tail = new Node('\0', -1);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * add a node in map and linked list
         * @param ch
         * @param idx
         * @return the next start idx if it needs update, otherwise return -1
         */
        public int addNode(char ch, int idx) {
            if (map.containsKey(ch)) {
                updateIdx(ch, idx);
            } else { // add a new node at the end of linked list
                Node node = new Node(ch, idx);
                map.put(ch, node);
                node.prev = tail.prev;
                node.next = tail;
                tail.prev.next = node;
                tail.prev = node;
                if (map.size() > k) {
                    Node remove = head.next;
                    removeNode(remove);
                    return remove.idx + 1;
                }
            }
            return -1;
        }

        /**
         * remove a node in map and linked list
         * @param node
         */
        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            map.remove(node.ch);
        }

        /**
         * update the index of last appearance of a character
         * @param ch
         * @param idx
         */
        private void updateIdx(char ch, int idx) {
            Node node = map.get(ch);
            node.idx = idx;
            Node cur = node;
            while (cur != tail && node.idx >= cur.idx) {
                cur = cur.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = cur.prev;
            node.next = cur;
            cur.prev.next = node;
            cur.prev = node;
        }
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        if (s.length() <= k) {
            return s.length();
        }

        MyQueue queue = new MyQueue(k);
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int res = queue.addNode(ch, i);
            if (res != -1) {
                start = res;
            }
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}
