package Graph.Topology;

import java.util.*;

/**
 * 207. Course Schedule: https://leetcode.com/problems/course-schedule/
 * Medium
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * Accepted
 * 834,000
 * Submissions
 * 1,853,139
 */

/**
 * Sol1: Topology sort
 * Sol2: Indegree + BFS
 */
public class CourseSchedule {
    /**
     * Sol1: Topology sort
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // corner case
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // initialization
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            visited.put(i, 0); // Initial state
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(graph, i, visited)) {
                return false;
            }
        }

        return true;
    }

    // if we could find a loop in the graph
    // 因为这里course number是连续的integer，因此可以用List<Integer>[]来替代
    private boolean dfs(Map<Integer, List<Integer>> graph, int cur, Map<Integer, Integer> visited) {
        Integer state = visited.get(cur);
        if (state == 1) { // visiting
            return true;
        }

        if (state == 2) { // visited
            return false;
        }

        visited.put(cur, 1);

        if (graph.containsKey(cur)) {
            for (Integer next: graph.get(cur)) {
                if(dfs(graph, next, visited)) {
                    return true;
                }
            }
        }

        visited.put(cur, 2);
        return false;
    }
    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] prerequisite: prerequisites) {
            List<Integer> requirements = graph.getOrDefault(prerequisite[0], new LinkedList<Integer>());
            requirements.add(prerequisite[1]);
            graph.put(prerequisite[0], requirements);
        }
        return graph;
    }

    /**
     * Sol2: Indegree + BFS
     */

    public boolean canFinishSol2(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            indegree.put(i, 0);
        }

        Map<Integer, List<Integer>> graph = buildGraph(prerequisites, indegree);

        // start with courses that has indegree == 0
        Set<Integer> coursesCanTake = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
                coursesCanTake.add(entry.getKey());
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (graph.containsKey(cur)) {
                for (int neighbor : graph.get(cur)) {
                    int curIndegree = indegree.get(neighbor) - 1;
                    if (curIndegree == 0) {
                        queue.offer(neighbor);
                        coursesCanTake.add(neighbor);
                    }
                    indegree.put(neighbor, curIndegree);
                }
            }

        }

        return coursesCanTake.size() == numCourses;

    }

    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites, Map<Integer, Integer> indegree) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] prerequisite: prerequisites) {
            List<Integer> nextCourses = graph.getOrDefault(prerequisite[1], new LinkedList<Integer>());
            nextCourses.add(prerequisite[0]);
            graph.put(prerequisite[1], nextCourses);
            indegree.put(prerequisite[0], indegree.getOrDefault(prerequisite[0], 0) + 1);
        }
        return graph;
    }
}
