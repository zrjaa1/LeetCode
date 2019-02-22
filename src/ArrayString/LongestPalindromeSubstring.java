package ArrayString;

/*
 * 5. Longest Palindromic Substring
Medium

2973

291

Favorite

Share
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */

/*
 * 从中间开花，考虑两种情况，奇数/偶数
 * follow up: 从中间往两边走，有剪枝的可能性
 */
public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        int max = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int l = i-1;
            int r = i+1;
            while (l >= 0 && r <= s.length()-1) {
                if (isPalindrome(s, l-1, r+1)) {
                    if (max < r-l+1) {
                        max = r-l+1;
                        res = l;
                    }
                    l--;
                    r++;
                } else break;
            }
        }
        for (int i = 0; i < s.length() - 1; i++) {
            int l = i;
            int r = i+1;
            while (l >= 0 && r <= s.length()-1) {
                if (isPalindrome(s, l-1, r+1)) {
                    if (max < r-l+1) {
                        max = r-l+1;
                        res = l;
                    }
                    l--;
                    r++;
                } else break;
            }
        }
        return s.substring(res, res+max);
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (++l < --r) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromeSubstring tester = new LongestPalindromeSubstring();
        String s = "abcdbbfcba";
        System.out.println(tester.longestPalindrome(s));
    }

}
