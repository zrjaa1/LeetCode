package Graph.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 721. Accounts Merge: https://leetcode.com/problems/accounts-merge/
 * Medium
 *
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Example 2:
 *
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 *
 * Constraints:
 *
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j] <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 */

/**
 * Union Find一道非常经典的题。本题最巧妙的点是：
 * 1. UF中存的是userId而非单个email，因为同一个userId中的所有email一定是属于同一个user的。这样做避免了同一个user还需要merge email的麻烦。
 * 2. Email -> UserId的map，2 purposes: 1. detect if an email has been recorded before; 2. If yes, what is its index in the UF, we need it for merging.
 */
public class AccountsMerge {
    class UnionFind {
        int[] parents;
        int[] size;
        int numOfCollections;

        public UnionFind(int n) {
            parents = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = -1;
                size[i] = 1;
            }
            numOfCollections = 0;
        }

        public boolean find(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public void union(int p, int q) {
            int rootP = getRoot(p);
            int rootQ = getRoot(q);
            if (rootP < rootQ) { // P -> Q
                parents[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else { // Q -> P
                parents[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            numOfCollections--;
        }

        public int getRoot(int p) {
            int cur = p;
            while (parents[cur] != cur) {
                parents[cur] = parents[parents[cur]];
                size[parents[cur]] += size[cur];
                cur = parents[cur];
            }
            parents[p] = cur;
            return cur;
        }

        public void insert(int p) {
            parents[p] = p;
            size[p] = 1;
            numOfCollections++;
        }

        public boolean exist(int p) {
            return parents[p] != -1;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new LinkedList<>();
        if (accounts == null || accounts.size() == 0) {
            return res;
        }

        UnionFind uf = new UnionFind(accounts.size()); // 存在的userId而非单个的email
        HashMap<String, Integer> emailToUserMap = new HashMap<>(); // 2 purposes: 1. detect if an email has been recorded before; 2. If yes, what is its index in the UF, we need it for merging.

        // construct UF
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!uf.exist(i)) {
                    uf.insert(i);
                }
                if (!emailToUserMap.containsKey(email)) {
                    emailToUserMap.put(email, i);
                } else { // if we encountered this email before.
                    Integer userInUF = emailToUserMap.get(email);
                    if (!uf.find(i, userInUF)) {
                        uf.union(i, userInUF);
                    }
                }
            }
        }

        // construct result
        HashMap<Integer, Set<String>> userToEmailMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int root = uf.getRoot(i);
            List<String> account = accounts.get(i);
            Set<String> userEmailSet = userToEmailMap.getOrDefault(root, new HashSet<>());
            for (int j = 1; j < account.size(); j++) {
                userEmailSet.add(account.get(j));
            }
            userToEmailMap.put(root, userEmailSet);
        }

        for (Map.Entry<Integer, Set<String>> entry: userToEmailMap.entrySet()) {
            List<String> list = new LinkedList<>();
            list.addAll(entry.getValue());
            Collections.sort(list);
            list.add(0, accounts.get(entry.getKey()).get(0));
            res.add(list);
        }

        return res;
    }
}
