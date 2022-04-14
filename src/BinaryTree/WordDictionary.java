package BinaryTree;

/**
 * 211. Design Add and Search Words Data Structure: https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * Medium

 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 3 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 */

/**
 * 1. Remember to clarify the frequency of each API being used. In this case, we assume that they are being used with the same frequency
 *    If addWord() is used more often, and we could ignore search(), then HashSet is a good choice
 *    If search() is used more often, and we could ignore addWord(), then we can use HashSet with storing words containing '.' in the set.
 *    If these 2 methods are used with the same frequency, then we could use Trie and we don't store '.'
 */
public class WordDictionary {
    class TrieNode {
        char ch;
        boolean isWord;
        TrieNode[] children;

        public TrieNode(char ch) {
            this.ch = ch;
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;
    public WordDictionary() {
        this.root = new TrieNode('\0');
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch: word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode cur, String word, int idx) {
        if (cur == null) {
            return false;
        }

        if (idx == word.length()) {
            return cur.isWord;
        }

        char ch = word.charAt(idx);
        if (ch != '.') {
            return dfs(cur.children[ch - 'a'], word, idx + 1);
        } else { // handle '.' case
            for (TrieNode node: cur.children) {
                if (dfs(node, word, idx + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}
