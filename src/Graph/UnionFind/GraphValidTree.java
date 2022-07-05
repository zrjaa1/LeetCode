package Graph.UnionFind;

import java.util.Arrays;

/**
 * 261. Graph Valid Tree: https://leetcode.com/problems/graph-valid-tree/
 * Medium
 *
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * Example 2:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no self-loops or repeated edges.
 */

/**
 * 1. 树的 E = V - 1，如果不是，可以直接return false，这样handle了绝大多数edge case
 * 2. Union Find, 2 vertices should not be already connected when we are trying to union them.
 * 3. 在每个node都需要初始化insert到UnionFind object当中，这样才能够handle edge case: graph is not connected.
 */
public class GraphValidTree {
    /**
     * 一种比较generic的UnionFind写法
     */
    class UnionFind {
        int[] parents;
        int[] size;
        int numOfCollections;

        public UnionFind(int n) {
            parents = new int[n];
            Arrays.fill(parents, -1);
            size = new int[n];
            numOfCollections = 0;
        }

        boolean find(int i, int j) {
            return getRoot(getIndex(i)) == getRoot(getIndex(j));
        }

        void union(int i, int j) {
            int rootP = getRoot(getIndex(i));
            int rootQ = getRoot(getIndex(j));
            if (rootP == rootQ) return;
            if (size[rootP] > size[rootQ]) {
                union(j, i);
            } else {
                parents[rootP] = rootQ;
                size[rootQ] += rootP;
                numOfCollections--;
            }
        }

        int getRoot(int i) {
            int cur = getIndex(i);
            while (cur != parents[cur]) {
                parents[cur] = parents[parents[cur]];
                cur = parents[cur];
            }
            parents[getIndex(i)] = cur;
            return cur;
        }

        void insert(int i) {
            if (parents[getIndex(i)] != -1) {
                return;
            }

            parents[getIndex(i)] = i;
            size[getIndex(i)] = 1;
            numOfCollections++;
        }

        boolean isNode(int i) {
            return parents[getIndex(i)] != -1;
        }

        private int getIndex(int i) {
            return i;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        // initialization
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            uf.insert(i);
        }

        for (int[] edge: edges) {
            if (uf.find(edge[0], edge[1])) { // if the 2 vertices are already in the same union (means they are already connected)
                return false;
            }

            uf.union(edge[0], edge[1]);
        }

        return uf.numOfCollections == 1;
    }
}
