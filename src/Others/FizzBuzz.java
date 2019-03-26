package Others;
import java.util.*;
/*
412. Fizz Buzz
Easy

481

703

Favorite

Share
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */

/*
Too easy
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        if (n <= 0) return null;
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i%5==0 && i%3 ==0) res.add("FizzBuzz");
            else if (i%3 == 0) res.add("Fizz");
            else if (i%5==0) res.add("Buzz");
            else res.add(Integer.toString(i));
        }
        return res;
    }
}
