package ArrayString;
import java.util.*;

/*
 * 76. Minimum Window Substring
Hard

1887

129

Favorite

Share
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

/*
 * similar to most of sliding window problem, use hashmap.
 * 1. the right pointer go right directly, each time it meets the requirement, stop
 * 2. the left pointer go right after the right point stopped. And it stop when the requirement is not satisfied anymore.
 * 3. record every possible min value.
 * 4. note: update the min value and res string even if the min == r-l+2, since we want to make sure that the case s = "a", t = "a" test case can be passed.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) return new String ("");
        // use hashset to record the characters in String t.
        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        HashMap<Character, Integer> sMap = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!tMap.containsKey(c)) tMap.put(c, 1);
            else tMap.put(c, tMap.get(c)+1);
        }
        int l = 0;
        int min = s.length();
        String res = "";
        for (int r = 0; r < s.length(); r++) {
            Character c = s.charAt(r);
            if (sMap.containsKey(c)) sMap.put(c, sMap.get(c)+1);
            else sMap.put(c, 1);
            if (!Satisfy(tMap, sMap)) continue;
            // if we reach here, we know that at this moment, the requirement is satisfied.
            while (Satisfy(tMap, sMap)) {
                Character temp = s.charAt(l);
                sMap.put(temp, sMap.get(temp)-1);
                l++;
            }
            if (min >= r-l + 2) {
                min = r - l + 2;
                res = s.substring(l-1, r+1);
            }

        }
        return res;
    }

    private boolean Satisfy(HashMap<Character, Integer> tMap, HashMap<Character, Integer> sMap) {
        for (Character c : tMap.keySet()) {
            if (!sMap.containsKey(c) || sMap.get(c) < tMap.get(c)) return false;
        }
        return true;
    }
}
