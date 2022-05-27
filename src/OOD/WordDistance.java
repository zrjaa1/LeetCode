package OOD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. Shortest Word Distance II: https://leetcode.com/problems/shortest-word-distance-ii/
 * Medium
 *
 * Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.
 *
 * Implement the WordDistance class:
 *
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 * int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 *
 * Explanation
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // return 3
 * wordDistance.shortest("makes", "coding");    // return 1
 *
 *
 * Constraints:
 *
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * word1 != word2
 * At most 5000 calls will be made to shortest.
 */

/**
 * 两个sorted找元素间的最小距离，只需要O(n)即可 -> 谁小移谁
 */
public class WordDistance {
    Map<String, List<Integer>> map;
    public WordDistance(String[] wordsDict) {
        map = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            List<Integer> index = map.getOrDefault(wordsDict[i], new ArrayList<>());
            index.add(i);
            map.put(wordsDict[i], index);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> index1 = map.get(word1);
        List<Integer> index2 = map.get(word2);
        int min = Integer.MAX_VALUE, i = 0, j = 0;
        while (i < index1.size() && j < index2.size()) {
            min = Math.min(min, Math.abs(index1.get(i) - index2.get(j)));
            if (index1.get(i) < index2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return min;
    }
}
