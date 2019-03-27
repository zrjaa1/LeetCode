package Others;

/*
67. Add Binary
Easy

861

172

Favorite

Share
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */

// Note: Don't remember to add carry at last
public class AddBinary {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        int m = a.length()-1, n = b.length()-1;
        StringBuilder sb = new StringBuilder();
        int sum = 0, carry = 0;
        while (m >= 0 || n >= 0) {
            if (m < 0) sum = b.charAt(n--) - '0' + carry;
            else if (n < 0) sum = a.charAt(m--) - '0' + carry;
            else sum = a.charAt(m--) - '0' + b.charAt(n--) - '0' + carry;
            carry = sum/2;
            sb.insert(0, sum%2);
        }
        if (carry != 0) sb.insert(0, carry);
        return sb.toString();
    }
}
