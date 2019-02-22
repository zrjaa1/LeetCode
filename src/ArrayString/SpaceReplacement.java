package ArrayString;

/*
Lintcode
212. Space Replacement
中文English
Write a method to replace all spaces in a string with %20. The string is given in a characters array, you can assume it has enough space for replacement and you are given the true length of the string.

You code should also return the new length of the string after replacement.

Example
Example 1:

Input: string[] = "Mr John Smith" and length = 13
Output: string[] = "Mr%20John%20Smith" and return 17
Explanation:
The string after replacement should be "Mr%20John%20Smith", you need to change the string in-place and return the new length 17.
Example 2:

Input: string[] = "LintCode and Jiuzhang" and length = 21
Output: string[] = "LintCode%20and%20Jiuzhang" and return 25
Explanation:
The string after replacement should be "LintCode%20and%20Jiuzhang", you need to change the string in-place and return the new length 25.
Challenge
Do it in-place.
 */

/*
最简单的做法是多用一个 charArray，in-place 操作需要从后往前做，提前计算出需要额外的多少空间把 slow 指向末位。否则的话如果要替换的内容
长于之前的内容，会覆盖掉原有的内容。

** 拓展到所有双指针 inplace 操作，一旦有 override 的可能性，就必须提前计算预留空间反着做。
 */
public class SpaceReplacement {
    public int replaceBlank(char[] string, int length) {
        if (string == null || length == 0) return 0;
        int space = 0;
        for (int i = 0; i < length; i++) {
            if (string[i] == ' ') space++;
        }
        int slow = length+2*space-1;
        for (int fast = length-1; fast >= 0; fast--) {
            if (string[fast] == ' ') {
                string[slow--] = '0';
                string[slow--] = '2';
                string[slow--] = '%';
            } else string[slow--] = string[fast];
        }
        return length+2*space;
    }
}
