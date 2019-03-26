package Others;

import ArrayString.Palindrome;

/*
9. Palindrome Number
Easy

1288

1240

Favorite

Share
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */

/*
1. 计算位数
2. 从两头取digit，判断
注意：在计算位数时，如果是使用x/divider != 0的判断条件，divier可能会溢出
     一种解决方法是divider用long
     另一种是用 x/divider >= 10的方式，提前判断，避免divider的溢出
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int size = 2;
        int divider = 10;
        while (x/divider >= 10) {
            size++;
            divider *= 10;
        }
        int moder = 1;
        while (size > 1) {
            int s1 = (x/divider)%10;
            int s2 = (x/moder)%10;
            if (s1 != s2) return false;
            divider /= 10;
            moder *= 10;
            size -= 2;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber tester = new PalindromeNumber();
        int num = 1000000001;
        tester.isPalindrome(num);
    }
}
