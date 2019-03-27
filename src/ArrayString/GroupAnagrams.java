package ArrayString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
49. Group Anagrams
Medium

1448

101

Favorite

Share
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 */

/*
本题的关键在于Hash Code 的设计
sol1: HashMap<String, List<String>> -> 把 string 转化成 charArray，sort 之后再转回String，这样相同的字母组合会 sort 成一样的 array
sol2：HashMap<Integer, Integer>> -> prime hash， key 是质数相乘的结果，value 是对应 list 在 res 中的 index
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z

        List<List<String>> res = new ArrayList<>();
        // key - 用质数相乘算出来的 hash code； value - list 在 res 中的 index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }

    // sol1
    /*
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String newStr = String.valueOf(arr);
            if (!map.containsKey(newStr)) {
                List<String> sol = new ArrayList<>();
                sol.add(str);
                res.add(sol);
                map.put(newStr, sol);
            } else {
                map.get(newStr).add(str);
            }
        }
        return res;
    }
    */
}
