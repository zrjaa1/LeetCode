package BDFS;
import java.util.*;

/*
127. Word Ladder
Medium

1264

770

Favorite

Share
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

/*
1. generate graph O(n^2) between wordList
2. find the entry in the graph (1 distance from beginWord)
3. BFS from both entries and get the shortest distance
Note:注意审题，求的是最小链的长度而不是 distance
 */
public class WordLAdder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0) return 0;
        // 1. generate graph
        int n = wordList.size();
        int endIndex = -1;
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            if (endWord.equals(wordList.get(i))) endIndex = i;
            for (int j = i; j < wordList.size(); j++) {
                if (i == j) graph[i][j] = true;
                else {boolean connect = oneReplaceDistance(wordList.get(i), wordList.get(j));
                    graph[i][j] = connect;
                    graph[j][i] = connect;
                }
            }
        }
        if (endIndex == -1) return 0;

        // 2. find entry in the graph
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (oneReplaceDistance(beginWord, wordList.get(i))) {
                // dfs 返回结果+2， 第一是因为从 beginWord 到起点有1，第二是因为是求链长 1-2-3-4 是 4 而不是 3
                min = Math.min(min, bfs(wordList, graph, i, endIndex)+2);
            }

        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private boolean oneReplaceDistance(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        int distance = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) distance++;
            if (distance >= 2) return false;
        }
        // not sure if same string should return true or false here.
        return true;
    }

    private int bfs(List<String> wordList, boolean[][] graph, int start, int end) {
        if (start == end) return 0;
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int distance = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                for (int i = 0; i < graph.length; i++) {
                    if (cur == i) continue;
                    if (!visited[i] && graph[cur][i] == true) {
                        // 这里提前返值了，所以要+1
                        if (i == end) return distance+1;
                        q.offer(i);
                        visited[i] = true;
                    }
                }
            }
            distance++;
        }
        return Integer.MAX_VALUE-2;
    }

    public static void main(String[] args) {
        WordLAdder tester = new WordLAdder();
        String start = "hit";
        String end = "cog";
        List<String> wordList= Arrays.asList("hot","dot","dog","lot","log","cog");
        tester.ladderLength(start, end, wordList);
    }
}
