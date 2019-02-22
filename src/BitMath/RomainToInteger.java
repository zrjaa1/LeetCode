package BitMath;
import java.util.*;

/*
13. Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
 */

/*
Thought:
The fastest way is to use many if statement.
my solution: use hashmap, and record the previous character. In general case, the character is in large -> small sequence, if not, it might be value like 9, 4 and so on.
in this case, we need to decrease the res by 2*map.get(prev).
 */
public class RomainToInteger {
    char[] roman = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    int[] number = {1000, 500, 100, 50, 10, 5, 1};
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        char cur = s.charAt(0);
        char prev = s.charAt(0);
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < roman.length; i++) {
            map.put(roman[i], number[i]);
        }
        for (int j = 0; j < s.length(); j++) {
            cur = s.charAt(j);
            res += map.get(cur);
            if (map.get(cur) > map.get(prev)) {
                res -= 2*map.get(prev);
            }
            prev = cur;
        }
        return res;
    }
}
