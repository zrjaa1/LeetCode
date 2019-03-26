package BDFS;
import java.util.*;

/*
301. Remove Invalid Parentheses
Hard

1198

49

Favorite

Share
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */

/*
首先思考：   1.如果遇到既不是'('也不是')'，可以不做任何处理，直接跳过；
            2.我们在决定要删除时，应该是')'>'('时。可以删的地方有多个，为了避免重复，我们要删的是连续')'的第一个或最后一个，
              并且，为了避免多次recursion造成的重复（例如，第一层和第二层可能删到同一个），我们用一个lastRemoveIndex来保证只能删掉这个后面的
            3. 我们想要这个recursion function直接把一个符合条件的string加到res里面，那么就需要检查的过程走到底，因此，如果我们要删，那么就先调用，把删掉之后的string放进去，再直接return
            4. 在主要过程跑完之后（for loop检查完之后），我们只检查了一边括号<=另一边，因此，我们需要把string reverse一个，检查括号换位（因此用了一个par[2]来表示两种括号），然后再次调用函数
                例如，如果我们以par[0] = '(', 那么"((())"第一次检查了，会直接跑过，这时其实仍然是不valid的状态
                我们需要以par[0] = ')'再跑一遍"))((("，得到"))(("然后再reverse一次，把它加到res中（具体看函数写法，使得只有经过两次reverse的string才能被加到res中）
            5. 这道题还有很多细节的地方，包括lastRemoveIndex的定义，string缩短后lastRemoveIndex后面的index都要减一，par[]的巧妙换位。值得细细思考
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;
        helper(s, 0, 0,  res, new char[] {'(', ')'});
        return res;
    }

    // handle ')' > '('
    private void helper(String s, int lastRemoveIndex, int start, List<String> res, char[] par) {
        for (int stack = 0, i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == par[0]) stack++;
            else if (c == par[1]) stack--;
            if (stack < 0) {
                for (int j = i; j >= lastRemoveIndex; j--) {
                    char a = s.charAt(j);
                    if (a == par[1] && (j == lastRemoveIndex || s.charAt(j-1) != par[1])) {
                        helper(s.substring(0, j)+s.substring(j+1, s.length()), j, i, res, par);
                    }
                }
                return;
            }
        }
        String reverse = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            helper(reverse, 0, 0, res, new char[] {')','('});
        } else {
            res.add(reverse);
        }
    }
}
