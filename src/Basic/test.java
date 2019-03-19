package Basic;
import java.util.*;

// test for keyword - static
public class test {
    public static void main(String[] args) {
        int[] tester = {1,3,5,7,9};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tester.length; i++) {
            map.put(tester[i], i);
        }
        int res = map.get(7);
        System.out.println(res);
        System.out.println(map.get(5));
    }
}
