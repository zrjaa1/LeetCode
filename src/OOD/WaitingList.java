package OOD;

import java.util.Map;

/**
 * Very similar to LRU cache
 */
public class WaitingList {
    /**
     * Note, my own solution, not verified
     */
    DListNode smallGroupHead;
    DListNode smallGroupTail;
    // Add medium group head, tail, and large group head tail as well.
    Map<Integer, DListNode> smallGroupMap;  // key - groupId, value - groupNode

    class Group {
        int groupId;
        int size;
        int arrivalTime;
    }

    class Table {
        int size;
    }

    public WaitingList() {
        smallGroupHead = new DListNode();
        smallGroupTail = new DListNode();
    }

    // append at the tail of group list
    public void addGroup(Group group) {
    }

    // Get the node from the map, remove it in the list
    public void removeGroup(Group group) {
    }

    // get the groupHead.next(), remove it from the Doubly linked list
    public Group getNextGroup(Table table) {
        return new Group();
    }
}
