package ArrayString;
import java.util.*;

/*
394. Decode String
Medium

1295

72

Favorite

Share
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

/*
sol: using stack, 每次遇到']'时处理，把累积的characters放回stack for many times。for loop一次之后，post processing，一次把stack里的东西全部拿出来放到stringbuilder里面
further optimization: stack里不放character而是放string，减少push和pop的次数
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<Character> stack = new Stack<>();
        int cur = 0;
        while (cur < s.length()) {
            char c = s.charAt(cur);
            if (c == ']') {
                List<Character> list = new ArrayList<>();
                while (stack.peek() != '[') {
                    list.add(stack.pop());
                }
                stack.pop();
                int times = 0;
                Stack<Integer> num = new Stack<>();
                while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                    num.push(stack.pop()-'0');
                }
                while (!num.isEmpty()) times = times * 10 + num.pop();
                for (int i = 0; i < times; i++) {
                    for (int j = list.size()-1; j >= 0; j--)
                        stack.push(list.get(j));
                }
            } else {
                stack.push(c);
            }
            cur++;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString tester = new DecodeString();
        String s = "3[a]2[bc]";
        tester.decodeString(s);
    }
}
