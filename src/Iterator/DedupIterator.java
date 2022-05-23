package Iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of string with possible duplicates, implements an Iterator so that the consecutive duplicate elements are only output once.
 */
public class DedupIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer next;
    boolean setNext;

    public DedupIterator(List<Integer> nums) {
        iterator = nums.iterator();
        next = null;
        setNext = false;
    }

    @Override
    public boolean hasNext() {
        return setNext || iterator.hasNext();
    }

    @Override
    public Integer next() {
        Integer res;
        if (setNext) {
            res = next;
            next = null;
            setNext = false;
        } else {
            res = iterator.next();
        }

        while (iterator.hasNext()) {
            next = iterator.next();
            if (!next.equals(res)) {
                setNext = true;
                break;
            }
        }

        return res;
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
