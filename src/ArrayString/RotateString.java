package ArrayString;

/*
796. Rotate String
Easy

334

33

Favorite

Share
We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.

 */

/*
需要注意的是 corner case： A、B 都是 ""
 */

public class RotateString {
    public boolean rotateString(String A, String B) {
        if (A == null || B == null || A.length() != B.length()) return false;
        char[] arrA = A.toCharArray();
        int len = arrA.length;
        for (int i = 0; i < len-1; i++) {
            arrA = shiftByOne(arrA);
            String temp = new String(arrA);
            if (temp.equals(B)) return true;
        }
        return (len == 0) ? true : false;
    }

    private char[] shiftByOne(char[] c) {
        int len = c.length;
        char[] res = new char[len];
        res[0] = c[len-1];
        for (int i = 1; i < len; i++) {
            res[i] = c[i-1];
        }
        return res;
    }
}
