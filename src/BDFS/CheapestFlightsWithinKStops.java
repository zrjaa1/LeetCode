package BDFS;

import javafx.util.Pair;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops: https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * Medium
 *
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 * Example 3:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */

/**
 * This is a very classic graph shortest path problem, with an additional condition that at most K stops should be made. The solution here could be adopted to many problems.
 * We discussed following solutions:
 * 1. DFS with memorization
 * 2. Dijkstra
 * 3. Bellman-Ford
 * 4. BFS
 *
 * A very good point is that we could store the current optimal distance and its stops to prune Dijkstra or BFS, see Solution 2 comments for details.
 *
 */

public class CheapestFlightsWithinKStops {


    /**
     * Solution 1: DFS with memorization. During the interview, I was thinking that [src, dst, k] could not be pruned. This is wrong:
     * 1. as long as the value is a single value (String, Integer and so on), we could prune.
     * 2. we should not use "dst" as part of the memorization variable, since our destination is fixed, there is no meaning of record the shortest path between any 2 nodes in the graph.
     *
     * O(T) = at most O(V^2 * K) recursions, each recursion takes O(V) time
     * O(S) = O(V * K + V^2) where O(V * K) is memo, O(V^2) is the adjacent matrix.
     */
    private int[][] adjMatrix;
    private HashMap<Pair<Integer, Integer>, Long> memo;

    public int findCheapestPriceDFS(int n, int[][] flights, int src, int dst, int K) {

        this.adjMatrix = new int[n][n];
        this.memo = new HashMap<Pair<Integer, Integer>, Long>(); // Pair[0] - src, Pair[1] - remaining of stops, Long - distance

        for (int[] flight: flights) {
            this.adjMatrix[flight[0]][flight[1]] = flight[2];
        }

        long ans = this.findShortest(src, K, dst, n);
        return ans >= Integer.MAX_VALUE ? -1 : (int)ans;
    }

    // the min distance from node -> dst, within n stops.
    public long findShortest(int node, int stops, int dst, int n) {

        // No need to go any further if the destination is reached
        if (node == dst) {
            return 0;
        }


        // Can't go any further if no stops left
        if (stops < 0) {
            return Integer.MAX_VALUE;
        }

        Pair<Integer, Integer> key = new Pair<Integer, Integer>(node, stops);


        // If the result of this state is already cached, return it
        if (this.memo.containsKey(key)) {
            return this.memo.get(key);
        }

        // Recursive calls over all the neighbors
        long ans = Integer.MAX_VALUE;
        for (int neighbor = 0; neighbor < n; ++neighbor) {

            int weight = this.adjMatrix[node][neighbor];

            // 0 value means no edge
            if (weight > 0) {
                ans = Math.min(ans, this.findShortest(neighbor, stops - 1, dst, n) + weight);
            }
        }

        // Cache the result
        this.memo.put(key, ans);
        return ans;
    }

    /**
     * Solution 2: Dijkstra. No need to avoid circle as long as there is no negative circle and the path guarantee to exist(otherwise it might go into indefinite loops)
     * Note that we need 2 arrays to store the current optimal distance and # of stops, otherwise the algorithm times out.
     * Two conditions that a path could be put in the minHeap:
     * 1. The expected distance is lower than the current optimal distance, or
     * 2. The number of stops it takes is smaller than the stops that it takes to achieve the current optimal distance.
     *
     * O(T) = O(V^2 * logV). Assume there are O(V) number of cities in the minHeap. (not O(V * K) like we assumed in DFS, a little bit weird here)
     * O(S) = O(V^2) for adjacent matrix, although I personally think that we have another O(V * K) for minHeap, the solution thinks it's implementation details and we can regard it as O(V) for minHeap.
     */
    class Wrapper {
        int name;
        int distance;
        int steps;
        List<String> path;

        public Wrapper(int name, int distance, int steps, List<String> path) {
            this.name = name;
            this.distance = distance;
            this.steps = steps;
            this.path = path;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }

        // Build the adjacency matrix
        int adjMatrix[][] = new int[n][n];
        for (int[] flight: flights) {
            adjMatrix[flight[0]][flight[1]] = flight[2];
        }

        int[] distances = new int[n];         // Shortest distances array
        int[] currentStops = new int[n];      // Shortest steps array
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(currentStops, Integer.MAX_VALUE);
        distances[src] = 0;
        currentStops[src] = 0;

        PriorityQueue<Wrapper> minHeap = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        minHeap.offer(new Wrapper(src, 0, 0, new ArrayList<>()));
        while (!minHeap.isEmpty()) { // O(V)
            Wrapper cur = minHeap.poll(); // O(logV)
            if (cur.name == dst) {
                System.out.println(cur.path);
                return cur.distance;
            }
            if (cur.steps >= K + 1) {
                continue;
            }

            for (int i = 0; i < n; i++) { // O(V)
                int cost = adjMatrix[cur.name][i];
                if (cost != 0) {
                    List<String> nextPath = new ArrayList<>(cur.path);
                    nextPath.add(String.valueOf(i));
                    if (cur.distance + cost < distances[i]) {
                        minHeap.offer(new Wrapper(i, cur.distance + cost, cur.steps + 1, nextPath)); // O(logV)
                        distances[i] = cur.distance + cost; // remember to update this value
                        currentStops[i] = cur.steps + 1; // remember to update stop as well
                    } else if (cur.steps + 1 < currentStops[i]) {
                        minHeap.offer(new Wrapper(i, cur.distance + cost, cur.steps + 1, nextPath));
                        // we don't update stops here, recall its definition is to store the # of stops it takes to achieve the optimal distance
                    }
                }
            }
        }

        return -1;
    }

    /**
     * Solution 3: Bellman-Ford. The best solution. Use this problem as an example of Bellman-Ford use case. And the implementation is quite simple as well.
     * O(T) = O(K * E), O(S) = O(V)
     */

    class Pair2 {
        int city;
        int dist;
        public Pair2(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }
    }

    public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }

        // Build the adjacency matrix
        int adjMatrix[][] = new int[n][n];
        for (int[] flight: flights) {
            adjMatrix[flight[0]][flight[1]] = flight[2];
        }

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        Queue<Pair2> queue = new LinkedList<>();
        queue.offer(new Pair2(src, 0));
        int numOfLoops = K + 1;
        while (numOfLoops-- > 0) {
            int size = queue.size();
            while (size-- > 0) {
                Pair2 cur = queue.poll();
                for (int i = 0; i < n; i++) {
                    int cost = adjMatrix[cur.city][i];
                    if (cost != 0 && cur.dist + cost < distances[i]) {
                        queue.offer(new Pair2(i, cur.dist + cost));
                        distances[i] = cur.dist + cost;
                    }
                }
            }
        }

        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }

    /**
     * Solution 4: BFS. Try to use the same way to enqueue items, like memo in the DFS. If you really think about it, it's just a less-efficient way of doing Bellman-Ford
     */
}
