package Basic;

import java.util.Comparator;
import java.util.PriorityQueue;

// minHeap
public class Heap {
	public static void main (String[] args) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(2, customizedComparator);
		heap.offer(1);
		heap.offer(2);
		heap.offer(0);
		System.out.println(heap.peek());
	}
	public static Comparator<Integer> customizedComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer num1, Integer num2) {
			return num1 - num2;
		}
	};
}
