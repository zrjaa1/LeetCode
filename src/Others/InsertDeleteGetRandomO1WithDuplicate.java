package Others;
import java.util.*;

/*
381. Insert Delete GetRandom O(1) - Duplicates allowed
Hard

378

40

Favorite

Share
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
 */

/*
Similar to LC380 (no duplicate case). We want a duplicate version, simply use a list<Integer> to store all the index of key in the arraylist.
e.g. for the key = 4, we have a list [0,3,7,9] representing 4 different locations in the arraylist, and we choose the last one - 9 to delete.
if index == 9 is not the last element in the arraylist, we swap it with the last one, and detele it.
And we also need to get the list of last one, e.g. [4,10], and update it to [4, 9], and udpate the hashmap for this entry.
remember to remove the entry in the map if the list.size() == 0.
 */
public class InsertDeleteGetRandomO1WithDuplicate {
    int size;
    Map<Integer, List<Integer>> map;
    List<Integer> content;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1WithDuplicate() {
        size = 0;
        map = new HashMap<Integer, List<Integer>>();
        content = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        List<Integer> index;
        if (!map.containsKey(val)) {
            index = new ArrayList<>();
            index.add(size);
            map.put(val, index);
        } else {
            index = map.get(val);
            index.add(size);
            map.put(val, index);
        }
        content.add(val);
        size++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        List<Integer> index = map.get(val);
        int indexToDelete = index.get(index.size()-1);
        // we delete the last added one in the list. if it is not located at the end of the content
        if (indexToDelete < size-1) {
            int curLastone = content.get(size-1);
            List<Integer> indexCurLastone = map.get(curLastone);
            // update the lastone in the hashmap
            for (int i = 0; i < indexCurLastone.size(); i++) {
                if (indexCurLastone.get(i) == size-1) indexCurLastone.set(i, indexToDelete);
            }
            content.set(indexToDelete, curLastone);
            map.put(curLastone, indexCurLastone);
        }
        content.remove(size-1);
        index.remove(index.size()-1);
        // remember to delete the entry in the map if needed
        if (index.size() == 0) map.remove(val);
        else map.put(val, index);
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
