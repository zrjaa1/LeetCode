package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 399. Evaluate Division: https://leetcode.com/problems/evaluate-division/
 * Medium
 *
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    class V {
        String name;
        Double val;
        V parent;
        int size;

        public V(String name) {
            this.name = name;
            this.parent = this;
            this.val = 1.0;
            this.size = 1;
        }
    }

    class UnionFind {
        Map<String, V> members;

        public UnionFind() {
            members = new HashMap<>();
        }

        public void union(V p, V q, double value) {
            V rootP = getRoot(p);
            V rootQ = getRoot(q);
            if (rootP.size < rootQ.size) { // P -> Q
                rootP.val = q.val / p.val / value;
                rootP.parent = rootQ;
                rootQ.size += rootP.size;
            } else {
                rootQ.val = p.val / q.val * value;
                rootQ.parent = rootP;
                rootP.size += rootQ.size;
            }
        }

        public boolean find(V p, V q) {
            return getRoot(p) == getRoot(q);
        }

        private V getRoot(V p) {
            V cur = p;
            double d = 1.0;
            while (cur.parent != cur) {
                cur.val *= cur.parent.val;
                d *= cur.val;
                cur.parent = cur.parent.parent;
                cur = cur.parent;
            }
            p.parent = cur;
            p.val = d;
            return cur;
        }

        private V getNode(String v) {
            return members.get(v);
        }

        public void insert(String name) {
            V node = new V(name);
            members.put(name, node);
        }

        public double calculate(V p, V q) {
            if (p == null || q == null || !find(p ,q)) {
                return -1.0;
            } else {
                return q.val / p.val;
            }
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (queries == null) {
            return new double[1];
        }
        double[] res = new double[queries.size()];

        // initialization
        UnionFind uf = new UnionFind();
        for (int i = 0; i < equations.size(); i++) {
            String p = equations.get(i).get(0);
            String q = equations.get(i).get(1);
            if (uf.getNode(p) == null) {
                uf.insert(p);
            }
            if (uf.getNode(q) == null) {
                uf.insert(q);
            }
            V nodeP = uf.getNode(p);
            V nodeQ = uf.getNode(q);
            if (!uf.find(nodeP, nodeQ)) {
                uf.union(nodeP, nodeQ, values[i]);
            }
        }

        // construct result
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            V p = uf.getNode(query.get(0));
            V q = uf.getNode(query.get(1));
            res[i] = uf.calculate(p, q);
        }

        return res;
    }

    public static void main(String[] args) {
        EvaluateDivision tester = new EvaluateDivision();
        List<List<String>> equations = new LinkedList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        List<List<String>> queries = new LinkedList<>();
        queries.add(Arrays.asList("a", "c"));
        double[] values = new double[] {2.0, 3.0};
        double[] res = tester.calcEquation(equations, values, queries);
    }
}
