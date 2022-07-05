package Graph.UnionFind;

public class UnionFind {
    public int[] parents;
    public int[] size;
    int numUnions;
    int cols;

    public UnionFind(int m, int n) {
        parents = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                parents[i * n + j] = -1;
            }
        }
        cols = n;
        numUnions = 0;
    }

    // O(h), which is O(logn) with our implementation: 1. small tree points to larger tree 2. parent points to its parent's parent
    public void union(int m, int n, int i, int j) {
        int root1 = getRoot(m, n);
        int root2 = getRoot(i, j);
        if (size[root1] < size[root2]) { // make sure smaller union points to larger one, root2 -> root1
            int temp = root1;
            root1 = root2;
            root2 = temp;
        }

        parents[root2] = root1;
        size[root1] += size[root2];
        numUnions--;
    }

    // O(h)
    public boolean find(int m, int n, int i, int j) {
        return getRoot(m, n) == getRoot(i, j);
    }

    public void insert(int m, int n) {
        int idx = getIndex(m, n);
        if (parents[idx] != -1) return;
        parents[idx] = idx; // point to itself when initialization
        size[idx] = 1;
        numUnions++;
    }

    // Have to call this before calling find(), otherwise will cause out of bound exception when getRoot()
    public boolean exist(int m, int n) {
        return parents[getIndex(m, n)] != -1;
    }

    // O(h)
    private int getRoot(int m, int n) {
        int idx = getIndex(m, n);
        int cur = idx;
        while (parents[cur] != cur) {
            parents[cur] = parents[parents[cur]]; // point to its parent's parent
            cur = parents[cur];
        }
        parents[idx] = cur; // make a shortcut so that we can find it quickly in the future
        return cur;
    }

    private int getIndex(int m, int n) {
        return m * cols + n;
    }
}
