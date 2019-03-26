package OA;
import java.util.*;

public class MinimumTime {
    int minimumTime(int numOfParts, List<Integer> parts) {
        if (parts == null || parts.size() == 0) return 0;
        if (parts.size() == 1) return parts.get(0);

        Queue<Integer> minHeap = new PriorityQueue<>();
        for (Integer e : parts) {
            minHeap.offer(e);
        }

        int sum = 0;
        while (minHeap.size() > 1) {
            int combo = minHeap.poll() + minHeap.poll();
            sum += combo;
            minHeap.offer(combo);
        }
        return sum;
    }

    public static void main(String[] args) {
        MinimumTime tester = new MinimumTime();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(6);
        list.add(12);
        list.add(8);
        System.out.println(tester.minimumTime(4, list));
    }
}
