package Util;

class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode newNode = cur.children[c-'a'] == null ? new TrieNode(c) : cur.children[c-'a'];
            cur.children[c-'a'] = newNode;
            cur = newNode;
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c-'a'] == null) return false;
            cur = cur.children[c-'a'];
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.children[c-'a'] == null) return false;
            cur = cur.children[c-'a'];
        }
        if (cur.isWord == true) return true;
        for (int i = 0; i < 26; i++) {
            if (cur.children[i] != null) return true;
        }
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class TrieNode {
    public char val;
    public TrieNode[] children;
    public boolean isWord;
    public TrieNode(char c) {
        val = c;
        children = new TrieNode[26];
        isWord = false;
    }
}
