package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 210. Course Schedule II: https://leetcode.com/problems/course-schedule-ii/
 * Medium
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */

/**
 * 1. 注意corner case - 在没有prerequisites的时候，是返回所有课程，而不是empty array
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        // initialization
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        int[] visited = new int[numCourses];
        List<Integer> res = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (dfs(graph, i, visited, res)) {
                return new int[0];
            }
        }

        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    // if we could find a loop in the graph
    private boolean dfs(List<List<Integer>> graph, int cur, int[] visited, List<Integer> res) {
        int state = visited[cur];
        if (state == 1) { // visiting
            return true;
        }

        if (state == 2) { // visited
            return false;
        }

        visited[cur] = 1;

        if (graph.get(cur) != null) {
            for (Integer next: graph.get(cur)) {
                if(dfs(graph, next, visited, res)) {
                    return true;
                }
            }
        }

        visited[cur] = 2;
        res.add(0, cur);
        return false;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<Integer>());
        }

        for (int[] prerequisite: prerequisites) {
            int size = graph.size();
            List<Integer> requirements = graph.get(prerequisite[1]);
            if (requirements == null) {
                requirements = new LinkedList<>();
            }
            requirements.add(prerequisite[0]);
            graph.set(prerequisite[1], requirements);
        }
        return graph;
    }
}
