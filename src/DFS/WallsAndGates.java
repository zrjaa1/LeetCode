package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 286. Walls and Gates: https://leetcode.com/problems/walls-and-gates/
 * Medium
 *
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * Example 2:
 *
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 *
 *
 * Constraints:
 *
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 231 - 1.
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        // corner cases
        if (rooms.length == 0 || rooms[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int m = rooms.length, n = rooms[0].length;

        // BFS
        Queue<Position> queue = new LinkedList<Position>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new Position(i, j, m, n));
                }
            }
        }
        bfsAndNote(rooms, queue);
    }

    public void wallAndGates_startFromSuperGate(int[][] rooms) {
        // corner cases

        // BFS
    }

    private static void bfsAndNote(int[][] rooms, Queue<Position> queue) {
        // corner case
        if (queue == null || queue.size() == 0) {
            return;
        }

        // bfs
        int level = 0;

        while (queue.size() != 0) {
            int size = queue.size();
            while (size-- > 0) {
                Position current = queue.poll();
                if (rooms[current.x][current.y] == 0 || rooms[current.x][current.y] == Integer.MAX_VALUE) {
                    for (Position neighbor: current.reachablePositions()) {
                        if (rooms[neighbor.x][neighbor.y] == Integer.MAX_VALUE) {
                            queue.add(neighbor);
                        }
                    }
                    rooms[current.x][current.y] = level;
                }
            }
            level++;
        }
    }

    private class Position {
        public int x;
        public int y;
        public int m;
        public int n;

        public Position(int x, int y, int m, int n) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.n = n;
        }

        public Position left() {
            if (this.x <= 0) {
                return null;
            }
            return new Position(this.x-1, this.y, this.m, this.n);
        }

        public Position right() {
            if (this.x >= m-1) {
                return null;
            }
            return new Position(this.x+1, this.y, this.m, this.n);
        }

        public Position up() {
            if (this.y <= 0) {
                return null;
            }
            return new Position(this.x, this.y-1, this.m, this.n);
        }

        public Position down() {
            if (this.y >= n-1) {
                return null;
            }
            return new Position(this.x, this.y+1, this.m, this.n);
        }

        public List<Position> reachablePositions() {
            List<Position> neighbors = new ArrayList<>();

            if (this.left() != null) {
                neighbors.add(this.left());
            }

            if (this.right() != null) {
                neighbors.add(this.right());
            }

            if (this.up() != null) {
                neighbors.add(this.up());
            }

            if (this.down() != null) {
                neighbors.add(this.down());
            }

            return neighbors;
        }
    }
}
