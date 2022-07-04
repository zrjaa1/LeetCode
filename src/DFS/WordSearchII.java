package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * My Solution: Trie with Parent pointer for backtracing(forming res string) + Pointer to current TrieNode of DFS
 * Potential Optimization(see Leetcode solution below):
 * 1. Instead of storing characters in each TrieNode, store word at the leaf node.
 * 2. How we use the pointer to current TrieNode could be optimized (think about the dfs reset TrieNode by default)
 */
public class WordSearchII {
    /**
     * Optimized Solution
     */
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    /**
     * My solution
    class TrieNode {
        char ch;
        TrieNode[] children;
        TrieNode parent;
        boolean isWord;

        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[26];
            isWord = false;
        }
    }

    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode('\0');
        }

        public void insertWord(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode(ch);
                }
                cur.children[ch - 'a'].parent = cur;
                cur = cur.children[ch - 'a'];
            }
            cur.isWord = true;
        }

        public TrieNode find(TrieNode cur, char ch) {
            return cur.children[ch - 'a'];
        }

        public TrieNode backtrace(TrieNode cur) {
            return cur.parent;
        }
    }

    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        // initialize Trie
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insertWord(words[i]);
        }

        // dfs starting from each point of board
        Set<String> resSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, trie.root.children[board[i][j] - 'a'], i, j, new HashSet<>(), resSet);
            }
        }

        List<String> res = new ArrayList<>();
        for (String s : resSet) {
            res.add(s);
        }
        return res;
    }

    private void dfs(char[][] boards, Trie trie, TrieNode cur, int i, int j, Set<Integer> visited, Set<String> res) {
        if (i < 0 || i >= boards.length || j < 0 || j >= boards[0].length || visited.contains(hashKey(i, j)) || cur == null) {
            return;
        }

        visited.add(hashKey(i, j));

        if (cur.isWord) {
            StringBuilder sb = new StringBuilder();
            TrieNode backtracer = cur;
            while (backtracer != trie.root) {
                sb.append(0, backtracer.ch);
                backtracer = backtracer.parent;
            }
            res.add(sb.toString());
        }

        for (int[] direction : DIRECTIONS) {
            int ii = i + direction[0];
            int jj = j + direction[1];
            dfs(boards, trie, trie.find(cur, boards[ii][jj]), ii, jj, visited, res);
        }
        // backtrace(cur);
        visited.remove(hashKey(i, j));
    }

    private int hashKey(int i, int j) {
        return i * 11 + j;
    }
     */
}
