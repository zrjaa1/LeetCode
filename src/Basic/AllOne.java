package Basic;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 432. All O`one Data Structure: https://leetcode.com/problems/all-oone-data-structure/
 * Hard
 *
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 *
 * Constraints:
 *
 * 1 <= key.length <= 10
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */

/**
 * 本题主要考察：
 * 1. Bucket Sort的引申，即只有在取值连续变化且有一定范围的时候，可以在O(1)时间复杂度内实现Sort
 * 2. Double Link Bucket
 * Note： LFU Cache应该可用类似解法
 */
public class AllOne {
    class Bucket {
        int count;
        HashSet<String> set;
        Bucket prev, next;

        public Bucket(String s, int count) {
            this.count = count;
            set = new HashSet<>();
            set.add(s);
        }

        public Bucket increase(String s) {
            Bucket res;
            if (next.count == count + 1) { // move string to next bucket
                next.set.add(s);
                res = next;
            } else { // create a bucket if it does not exist
                Bucket bucket = new Bucket(s, count + 1);
                append(this, bucket);
                res = bucket;
            }

            set.remove(s);
            if (set.isEmpty()) { // remove this bucket
                removeBucket(this);
            }

            return res;
        }

        public Bucket decrease(String s) {
            Bucket res = null;
            if (prev.count == count - 1) { // move string to prev bucket
                prev.set.add(s);
                res = prev;
            } else if (count - 1 != 0) { // create a bucket
                Bucket bucket = new Bucket(s, count - 1);
                prepend(this, bucket);
                res = bucket;
            }

            set.remove(s);
            if (set.isEmpty()) { // remove this bucket
                removeBucket(this);
            }

            return res;
        }

        private void removeBucket(Bucket bucket) {
            bucket.prev.next = bucket.next;
            bucket.next.prev = bucket.prev;
        }

        public String getElement() {
            return set.iterator().next();
        }

        public void append(Bucket bucket, Bucket newBucket) {
            newBucket.prev = bucket;
            newBucket.next = bucket.next;
            bucket.next.prev = newBucket;
            bucket.next = newBucket;
        }

        public void prepend(Bucket bucket, Bucket newBucket) {
            newBucket.next = bucket;
            newBucket.prev = bucket.prev;
            bucket.prev.next = newBucket;
            bucket.prev = newBucket;
        }
    }

    HashMap<String, Bucket> map;
    Bucket head, tail;

    public AllOne() {
        map = new HashMap<>();
        head = new Bucket("", -1);
        tail = new Bucket("", -1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        Bucket bucket = map.get(key);
        if (bucket == null) {
            bucket = new Bucket(key, 0);
            bucket.append(head, bucket);
        }
        Bucket res = bucket.increase(key);
        map.put(key, res);
    }

    public void dec(String key) {
        Bucket bucket = map.get(key);
        if (bucket == null) {
            throw new IllegalArgumentException("The key does not exist in the data structure");
        }
        Bucket res = bucket.decrease(key);
        if (res != null) {
            map.put(key, res);
        } else {
            map.remove(key);
        }
    }

    public String getMaxKey() {
        return tail.prev.getElement();
    }

    public String getMinKey() {
        return head.next.getElement();
    }
}
