package Others;
import java.util.*;

/**
380. Insert Delete GetRandom O(1)
Medium

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */

/*
Hashmap用来完成val-arraylist index的对应，重点在于arraylist的remove不是O（1），解决办法是和最后一位换一下，最后总是删除最后一位
arraylist可以完成random

 */
public class InsertDeleteGetRandomO1 {
    int size;
    List<Integer> content;
    Map<Integer, Integer> map;  // key - val, value - index in the list
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        size = 0;
        map = new HashMap<Integer, Integer>();
        content = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, size);
            content.add(val);
            size++;
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        // not the last one, swap with the last one.
        if (index < size-1) {
            int lastone = content.get(size-1);
            content.set(index, lastone);
            map.put(lastone, index);
        }
        content.remove(size-1);
        map.remove(val);
        size--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if (size <= 0) return -1;
        int random = (int)(Math.random()*size);
        return content.get(random);
    }
}
