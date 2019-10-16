package Basic;
import java.util.*;
/*
706. Design HashMap
Easy

232

45

Favorite

Share
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);
hashMap.put(2, 2);
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found)

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap libra
 */

/*
sol1: 直接用一个ArrayList，如果size不够，直接加null
sol2: 设计hashcode，最简单的办法就是取模，然后重复的情况用linkedList来handle
      //代码不好粘贴，直接看最后一次submission，时间、空间都更优一些
 */
public class DesignHashMap {
    List<Integer> list;
    /** Initialize your data structure here. */
    public DesignHashMap() {
        list = new ArrayList<>();
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        while (key > list.size()-1) list.add(null);
        list.set(key, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if (key > list.size()-1) return -1;
        Integer value = list.get(key);
        if (value == null) return -1;
        else return value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        if (key > list.size()-1) return;
        else list.set(key, null);
    }
}
