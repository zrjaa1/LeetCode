package BFS;
import javafx.util.Pair;

import java.util.*;

/**
127. Word Ladder: https://leetcode.com/problems/word-ladder/
Hard

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

/**
1. BFS + generate graph O(n * k) from wordList, where n is the number of word in the list, and k is the length of word.
   Note that the solution that I write use a convert function to calculate each time, but the best solution should be constructing a graph
2. find the entry in the graph (1 distance from beginWord)
3. Optimization: Bidirectional BFS -> in case the startWord has much more edges than endWord, we could start from both side, use 2 sets to record and check if the set contains begin/endWord.
See Leetcode Solution for reasons, but basically, you can save 1 level traverse time in the Tree, if the endWord located at the very bottom of a complete tree, then it save a half of time (still constant factor)
Note:注意审题，求的是最小链的长度而不是 distance
 */
public class WordLadder {
    /**
     * Sol1: BFS + convert()找neighbor。不是最优解，最优解应该是Sol2 pre-constructed graph，而不是每次都去convert来尝试寻找neighbors
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // corner case
        if (beginWord == null || endWord == null) {
            return 0;
        }

        // pre-processing
        Set<String> wordSet = convertListToSet(wordList);

        if (!wordSet.contains(endWord)) return 0;

        // bfs
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        return bfs(endWord, wordSet, queue);
    }

    private int bfs(String endWord, Set<String> wordSet, Queue<String> queue) {
        int minLen = 1;
        while (queue.size() != 0) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                wordSet.remove(cur);
                List<String> neighbors = convert(cur, wordSet);
                for (String neighbor: neighbors) {
                    if (neighbor.equals(endWord)) {
                        return minLen + 1;
                    }
                    queue.offer(neighbor);
                    wordSet.remove(neighbor);
                }
            }
            minLen++;
        }
        return 0;
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
                if (cc != c && wordSet.contains(newStr)) {
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

    /**
     * Sol2: Bi-direction BFS + Pre-constructed BFS
     */

    private int L = 0;
    private Map<String, List<String>> allComboDict = new HashMap<>();

    private int visitWordNode(
            Queue<Pair<String, Integer>> Q,
            Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {

        for (int j = Q.size(); j > 0; j--) {

            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();

            for (int i = 0; i < this.L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (othersVisited.containsKey(adjacentWord)) {
                        return level + othersVisited.get(adjacentWord);
                    }

                    if (!visited.containsKey(adjacentWord)) {

                        // Save the level as the value of the dictionary, to save number of hops.
                        visited.put(adjacentWord, level + 1);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return -1;
    }

    public int ladderLengthBiDirectionBFS(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.L = beginWord.length();

        wordList.forEach( word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> transformations =
                        this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                this.allComboDict.put(newWord, transformations);
            }
        });

        // Queues for birdirectional BFS
        // BFS starting from beginWord
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
        // BFS starting from endWord
        Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        int ans = -1;

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

            // Progress forward one step from the shorter queue
            if (Q_begin.size() <= Q_end.size()) {
                ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            } else {
                ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            }

            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        WordLadder tester = new WordLadder();
        String start = "hot";
        String end = "dog";
        List<String> wordList= Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot");
        int res = tester.ladderLengthBiDirectionBFS(start, end, wordList);
        System.out.println(res);
    }
}
