package Others;

/*
844. Backspace String Compare
Easy

555

42

Favorite

Share
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
 */

public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) return false;
        StringBuilder s = new StringBuilder(), t = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '#' && s.length() > 0) s.setLength(s.length()-1);
            else if (c != '#') s.append(c);
        }
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (c == '#' && t.length() > 0) t.setLength(t.length()-1);
            else if (c != '#') t.append(c);
        }
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) != t.charAt(i)) return false;
        return true;
    }
}
