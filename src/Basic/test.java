package Basic;
import java.util.*;

// test for keyword - static
interface func {
    public void print(String s);
}
public class test {
    public static void main(String[] args) {
        Queue<Integer> minHeap = new PriorityQueue<>((s1, s2) -> s1 - s2 < 0 ? -1 : 1);
    }
}
