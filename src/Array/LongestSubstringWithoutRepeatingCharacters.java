package Array;
import java.util.*;

/*
3. Longest Substring Without Repeating Characters
Medium

4778

243

Favorite

Share
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */


/*
1. left，right 双指针，right 无脑走，left 在出现重复的时候更新，用 hashmap 记录 character 最近一次的 index，只在要更新l的时候才更新 max
max 因此有可能未更新（当 right 走出边界且没有出现重复的时候），因此在最后要加一个判断来 return 不同的值。
2. 另一点需要注意的是，hashmap 在判断过后才更新，否则每次loop 都会重复
3. left 不是出现重复就更新，而是出现重复并且要跳的位置比当前位置还要靠右时，才更新
具体可以 demo 一下来看
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int r = 0, l = 0;
        int max = 1;
        for (r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (map.containsKey(c) && map.get(c)+1 > l) {
                int temp = map.get(c);
                max = (max > r-l) ? max : r-l;
                l = temp+1;
            }
            map.put(c, r);
        }
        return (max > r-l) ? max : r-l;
    }
}
