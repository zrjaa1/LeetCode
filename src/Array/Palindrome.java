package Array;

/*
125. Valid Palindrome
Easy

513

1438

Favorite

Share
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false

 */

/*
通过本题学会了 int 和 char 之间的转换（ASCII）
记住 0 - 9 -> 48 - 57
     A - Z -> 65 - 90
     a - z -> 97 - 122
     Alphanumeric 包括了大小写字母和数字
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() <= 1) return true;
        char[] array = new char[s.length()];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            int temp = (int)s.charAt(i);
            if (temp >= 65 && temp <= 90) array[j++] = (char)(temp + 32);
            else if (temp >= 97 && temp <= 122) array[j++] = s.charAt(i);
            else if (temp >= 48 && temp <= 57) array[j++] = s.charAt(i);
        }
        return helper(new String(array).substring(0, j));

    }

    private boolean helper(String s) {
        if (s == null) return false;
        if (s.length() <= 1) return true;
        int l = -1, r = s.length();
        while (++l < --r) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }

    public static void main(String args[]) {
        Palindrome tester = new Palindrome();
        String s = "OP";
        tester.isPalindrome(s);
    }
}
