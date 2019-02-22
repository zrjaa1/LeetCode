package ArrayString;
import java.util.*;

/*
205. Isomorphic Strings
Easy

653

181

Favorite

Share
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
 */

/*
1. sol1: hashmap 双向 check，s->t, t->s (单向会出错）
2. sol2: 单指针 one pass，assume 当前的 pair 匹配，往前检查上一次该 character 出现的 index，需要这两个 index 匹配
         首先想到用 hashMap 来记录 index，然后引申到用256的 int array
         这里需要注意，array 里面存的 index 不能从0开始存，需要从1开始，否则例如"aa"，"ab"的情况，可能因为 array 初始化就是0而引发错误
         如果这里只有小写的26个字母，还可以用array[26] -> (int)'b' - (int)'a'
 */
public class Isomorphic {
    public boolean isIsomorphic(String s, String t) {
        /* sol2: Hashmap way
        if (s == null || t == null || s.length() != t.length()) return false;
        HashMap<Character, Integer> mapS = new HashMap<Character, Integer>();
        HashMap<Character, Integer> mapT = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if (!mapS.containsKey(charS)) {
                if (mapT.containsKey(charT)) return false;
                else {
                    mapS.put(charS, i);
                    mapT.put(charT, i);
                }
            } else {
                int tempS = mapS.get(charS);
                if (!mapT.containsKey(charT)) return false;
                int tempT = mapT.get(charT);
                if (tempS != tempT) return false;
                else {
                    mapS.put(charS, i);
                    mapT.put(charT, i);
                }
            }
        }
        return true;
        */

        // sol2: int[256] way
        if (s == null || t == null || s.length() != t.length()) return false;
        int[] arrayS = new int[256];
        int[] arrayT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            int indexS = (int) s.charAt(i);
            int indexT = (int) t.charAt(i);
            if (arrayS[indexS] == 0) {
                if (arrayT[indexT] != 0) return false;
                else {
                    arrayS[indexS] = i;
                    arrayT[indexT] = i;
                }
            } else {
                if (arrayT[indexT] == 0 || arrayT[indexT] != arrayS[indexS]) return false;
                arrayS[indexS] = i;
                arrayT[indexT] = i;
            }
        }
        return true;
    }
}
