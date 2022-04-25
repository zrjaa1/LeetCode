package Iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of string with possible duplicates, implements an Iterator so that the consecutive duplicate elements are only output once.
 */
public class DedupIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    boolean setNext;
    Integer next;

    public DedupIterator(List<Integer> nums) {
        this.iterator = nums.iterator();
        this.setNext = false;
        this.next = null;
    }

    @Override
    public boolean hasNext() {
        return setNext || iterator.hasNext();
    }

    @Override
    public Integer next() {
        int target = -1;
        if (setNext) {
            setNext = false;
            target = next;
        } else {
            target = iterator.next();
        }

        while (iterator.hasNext()) {
            next = iterator.next();
            if (next != target) {
                setNext = true;
                break;
            }
        }

        return target;
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 5);
        DedupIterator tester = new DedupIterator(nums);
        while (tester.hasNext()) {
            int res = tester.next();
            System.out.print(res);
        }
    }
}
