package Graph;
import java.util.*;

/*
269. Alien Dictionary
Hard

762

148

Favorite

Share
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */

/*
BFS解法：
这道题考察Graph的应用。图的表示方式中，可以用hashMap<node, List<node>>，在这里，我们用HashMap<Character, Set<Character>>，named map用来表示edge（在本题中是directed）
然后用另一个HashMap<Character, Integer> named degree来表示每个node 的入度，入度>0，代表有人指向，即自己的优先级不是最高的
先遍历所有String，完善map和degree（degree的初始值需要设为0，否则之后可能会有问题），这里需要注意的是，完善map使的遍历，只需要比较words[i]和words[i+1]即可，因为我们需要的只是邻居之间的大小关系
例如，w>e>r，只需要w>e和e>r即可，不需要w>r。

在完成上一步之后，我们将所有入度为0的character放到queue中，用BFS的方法遍历，放入res中，然后把这个character所指向的所有character的入度-1，代表"我对你的压制已经不存在了，因为我已经放入res中了"
如果一个character的入度因此变为0，证明它现在已经是剩余的character中优先级最高的了，于是再把它放入queue中等待遍历。

DFS解法：
用查环的code，多一行res.add(0, cur);

Corner/Edge case: Input -> Output
1. ["aba"] -> ["ab"]
2. ["ab", "ab"] -> ["ab"]
3. [
 */

public class AlienDictionary {
    /**
     * BFS
     */
    public String alienOrderBFS(String[] words) {
        String res = "";
        if (words == null || words.length == 0) return res;
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        for (String str : words) {
            for (char c : str.toCharArray())
                degree.put(c, 0);
        }
        for (int i = 0; i < words.length-1; i++) {
            String cur = words[i];
            String next = words[i+1];
            int n = Math.min(cur.length(), next.length());
            for (int j = 0; j < n; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    // update the degree, and map
                    Set<Character> set = new HashSet<>();
                    if (map.containsKey(c1)) set = map.get(c1);
                    // 这里必须要先判断c2是否原本就在set里，不然不用加入度
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (Character c : degree.keySet()) {
            if (degree.get(c) == 0) q.add(c);
        }
        while (q.size() != 0) {
            Character cur = q.poll();
            res += cur;
            // 这行一定要有，因为有可能有出度为0的character，即最后一个character被遍历到，引起null pointer exception
            if (map.containsKey(cur)) {
                for (Character c : map.get(cur)) {
                    int deg = degree.get(c);
                    deg--;
                    degree.put(c, deg);
                    if (deg == 0) q.offer(c);
                }
            }
        }
        // 这里是判断是否有矛盾的节点，如 a->b, b->a, 则会输出aba
        if (res.length() != degree.size()) return "";
        return res;
    }

    /**
     * DFS
     */
    public String alienOrderDFS(String[] words) {
        // some corner case covered by Constraints

        // initialization
        Map<Character, List<Character>> graph;
        try {
            graph = buildGraph(words);
        } catch (IllegalStateException ex) { // Invalid input -> ["abc", "ab"], should be ["ab", "abc"]
            return "";
        }

        int[] visited = new int[26];
        StringBuilder sb = new StringBuilder();

        // recursion
        for (char ch: graph.keySet()) {
            if (containsCycle(graph, ch, visited, sb)) {
                return "";
            }
        }
        return sb.reverse().toString();
    }

    private boolean containsCycle(Map<Character, List<Character>> graph, char ch, int[] visited, StringBuilder sb) {
        int state = visited[ch - 'a'];
        if (state == 1) { // visiting
            return true;
        }

        if (state == 2) { // visited
            return false;
        }

        visited[ch - 'a'] = 1; // visiting
        for (Character next: graph.get(ch)) {
            if (containsCycle(graph, next, visited, sb)) {
                return true;
            }
        }
        visited[ch - 'a'] = 2; // visited
        sb.append(ch);
        return false;
    }

    private Map<Character, List<Character>> buildGraph(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word: words) {
            for (char ch: word.toCharArray()) {
                if (!graph.containsKey(ch)) {
                    graph.put(ch, new LinkedList<>());
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() > words[i + 1].length() && words[i].startsWith(words[i + 1])) {
                throw new IllegalStateException();
            }

            for (int j = 0; j < words[i].length(); j++) {
                char ch0 = words[i].charAt(j);
                char ch1 = words[i + 1].charAt(j);
                if (ch0 != ch1) {
                    graph.get(ch0).add(ch1);
                    break;
                }
            }
        }

        return graph;
    }
}
