package OOD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1268. Search Suggestions System: https://leetcode.com/problems/search-suggestions-system/
 * Medium
 *
 * You are given an array of strings products and a string searchWord.
 *
 * Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 *
 *
 * Constraints:
 *
 * 1 <= products.length <= 1000
 * 1 <= products[i].length <= 3000
 * 1 <= sum(products[i].length) <= 2 * 104
 * All the strings of products are unique.
 * products[i] consists of lowercase English letters.
 * 1 <= searchWord.length <= 1000
 * searchWord consists of lowercase English letters.
 */
public class SearchSuggestSystem {
    class TrieNode {
        char ch;
        TrieNode[] children;
        List<String> recommendations;

        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[26];
            recommendations = new ArrayList<>();
        }
    }

    class Trie {
        TrieNode root;
        TrieNode cur;
        public Trie() {
            root = new TrieNode('\0');
            cur = root;
        }

        public void insertProduct(String product) {
            TrieNode cur = root;
            for (int i = 0; i < product.length(); i++) {
                char ch = product.charAt(i);
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode(ch);
                }
                cur = cur.children[ch - 'a'];
                cur.recommendations.add(product);
            }
        }

        public List<String> searchWord(char ch) {
            List<String> res = new ArrayList<>();
            cur = cur.children[ch - 'a'];
            if (cur == null) { // means we won't find any suggested word in the future
                return res;
            }

            Collections.sort(cur.recommendations);

            for (int i = 0; i < cur.recommendations.size(); i++) {
                if (i == 3) {
                    break;
                }
                res.add(cur.recommendations.get(i));
            }

            return res;
        }

        public void resetCur() {
            cur = root;
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        Trie trie = new Trie();
        for (String product : products) {
            trie.insertProduct(product);
        }

        for (int i = 0; i < searchWord.length(); i++) {
            List<String> suggested = trie.searchWord(searchWord.charAt(i));
            if (suggested.equals(Collections.emptyList())) { // once we get an empty list, it means we won't get any word in the future
                while (i++ < searchWord.length()) {
                    res.add(Collections.emptyList());
                }
                break;
            } else {
                res.add(suggested);
            }
        }

        trie.resetCur();

        return res;
    }
}
