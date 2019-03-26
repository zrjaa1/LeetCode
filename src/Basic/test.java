package Basic;
import java.util.*;

// test for keyword - static
interface func {
    public void print(String s);
}
public class test {
    public static void main(String[] args) {
        String[] str = {"123", "456"};
        func test = ((String s) -> System.out.println(s));
        test.print(str[0]);
    }
}
