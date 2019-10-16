package ArrayString;
import java.util.*;
/*
17. Letter Combinations of a Phone Number
Medium

1954

269

Favorite

Share
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

/*
用一个Queue<StringBuilder>来存，然后用BFS的方法
注意：StringBuilder的constructor不支持character
 */

public class LetterCombinationsOfPhoneNumber {
    private final char[][] letters = {{}, {}, {'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'},
            {'m','n','o'}, {'p','q','r','s'},{'t','u','v'}, {'w','x','y','z'}};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        Queue<StringBuilder> q = new LinkedList<>();
        for (int i = 0; i < letters[digits.charAt(0)-'0'].length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(letters[digits.charAt(0)-'0'][i]);
            q.offer(sb);
        }
        int count = 1;
        while (count < digits.length()) {
            int index = digits.charAt(count)-'0';
            int size = q.size();
            while (size-- != 0) {
                StringBuilder cur = q.poll();
                for (int i = 0; i < letters[index].length; i++) {
                    StringBuilder temp = new StringBuilder(cur.toString());
                    temp.append(letters[index][i]);
                    q.offer(temp);
                }
            }
            count++;
        }
        while (!q.isEmpty()) {
            StringBuilder sol = q.poll();
            res.add(sol.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber tester = new LetterCombinationsOfPhoneNumber();
        String s = "23";
        tester.letterCombinations(s);
    }
}
