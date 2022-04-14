package BinaryTree;

/**
 * 208. Implement Trie (Prefix Tree): https://leetcode.com/problems/implement-trie-prefix-tree/
 * Medium
 *
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */

/**
 * 1. Remember to clarify the frequency of each API being used. In this case, we assume that they are being used with the same frequency
 * 2. Remember to clarify the possible type of character, like a-z, or ASCII
 */
public class Trie {
    class TrieNode {
        char ch;
        boolean isWord;
        TrieNode[] children;

        public TrieNode(char ch) {
            this.ch = ch;
            this.children = new TrieNode[256];
        }
    }

    private TrieNode root;
    public Trie() {
        root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (char ch: word.toCharArray()) {
            if (cur.children[ch] == null) {
                cur.children[ch] = new TrieNode(ch);
            }
            cur = cur.children[ch];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode end = checkPrefix(word);
        if (end != null) {
            return end.isWord;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        TrieNode end = checkPrefix(prefix);
        return end != null;
    }

    private TrieNode checkPrefix(String prefix) {
        TrieNode cur = root;
        for (char ch: prefix.toCharArray()) {
            if (cur.children[ch] == null) {
                return null;
            }
            cur = cur.children[ch];
        }
        return cur;
    }
}
