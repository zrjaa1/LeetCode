package Bit;

/*
405. Convert a Number to Hexadecimal
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
 */

/*
Note: delete the leading 0s, edge case: input = 0
 */
public class ConvertNumberToHexadecimal {

    char map[]  = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public String toHex(int num) {
        if (num == 0) return Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int temp = 1*((num >> 4*i) & 1) + 2*((num >> 4*i+1) & 1) + 4*((num >> 4*i+2) & 1) + 8*((num >> 4*i+3) & 1);
            sb.insert(0, map[temp]);
        }
        while (sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }

    public static void main(String[] args) {
        ConvertNumberToHexadecimal tester = new ConvertNumberToHexadecimal();
        tester.toHex(-1);
    }
}
