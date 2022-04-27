package BDFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 126. Word Ladder II: https://leetcode.com/problems/word-ladder-ii/
 * Hard
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 1000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */

/**
 * 1. BFS + DFS， BFS 找邻居和endWord的最短路径长度，DFS还原路径
 * 2. BFS 保证了无环， 因此DFS不用查环
 */
public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // corner case
        List<List<String>> result = new LinkedList<>();
        if (beginWord == null || endWord == null) {
            return result;
        }

        // pre-processing
        Set<String> wordSet = convertListToSet(wordList);

        // bfs
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Map<String, List<String>> routeMap = bfs(endWord, wordSet, queue);

        if (routeMap.size() != 0) {
            List<String> path = new LinkedList<>();
            path.add(beginWord);
            dfs(beginWord, endWord, routeMap, path, result);
        }
        return result;
    }

    private Map<String, List<String>> bfs(String endWord, Set<String> wordSet, Queue<String> queue) {
        Map<String, List<String>> route = new HashMap<>();
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> removeSet = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                removeSet.add(cur);
                if (cur.equals(endWord)) {
                    found = true;
                }
                for (String neighbor: convert(cur, wordSet)) {
                    if (!removeSet.contains(neighbor)) { // if we haven't visited this before, add it to queue and remove set
                        queue.offer(neighbor);
                        removeSet.add(neighbor);
                    }

                    List<String> neighbors = route.getOrDefault(cur, new ArrayList<>());
                    neighbors.add(neighbor);
                    route.put(cur, neighbors);
                }
            }

            if (found) {
                return route;
            }

            for (String removeStr: removeSet) { // This avoid cycles
                wordSet.remove(removeStr);
            }
        }

        return Collections.emptyMap();
    }

    private void dfs(String cur, String endWord, Map<String, List<String>> routeMap, List<String> path, List<List<String>> res) {
        if (cur.equals(endWord)) {
            List<String> resList = new ArrayList<>(path);
            res.add(resList);
            return;
        }

        // we don't need to detect cycles, as it's already done in BFS
        List<String> neighbors = routeMap.get(cur);
        if (neighbors != null) {
            for (String neighbor: routeMap.get(cur)) {
                path.add(neighbor);
                dfs(neighbor, endWord, routeMap, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    // O(k*26) where k is the length of string
    private List<String> convert(String str, Set<String> wordSet) {
        List<String> converted = new LinkedList<>();
        char[] strArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            char c = strArray[i];
            for (char cc = 'a'; cc <= 'z'; cc++) {
                strArray[i] = cc;
                String newStr = new String(strArray);
                if (c != cc && wordSet.contains(newStr)) {
                    converted.add(newStr);
                }
            }
            // recover
            strArray[i] = c;
        }
        return converted;
    }

    // O(n) where n is the lenght of list
    private Set<String> convertListToSet(List<String> list) {
        Set<String> set = new HashSet<>();
        for (String str: list) {
            set.add(str);
        }
        return set;
    }
}
