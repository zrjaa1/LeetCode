package BFS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 752. Open the Lock: https://leetcode.com/problems/open-the-lock/
 * Medium
 *
 * Example 1:
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * Example 2:
 *
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 * Example 3:
 *
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation: We cannot reach the target without getting stuck.
 *
 *
 * Constraints:
 *
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 */

/**
 * 类似 LC 286. Walls and Gates. 如果没有障碍的话，有数学解。BFS
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        final String begin = "0000";
        if (target.equals(begin)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        for (String str: deadends) {
            if (str.equals(begin)) {
                return -1;
            }
            visited.add(str);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);

        int minLen = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> neighbors = convert(cur);
                for (String neighbor: neighbors) {
                    if (neighbor.equals(target)) {
                        return minLen;
                    }
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            minLen++;
        }

        return -1;
    }

    private List<String> convert(String cur) {
        if (cur == null || cur.length() != 4) {
            throw new IllegalArgumentException();
        }

        Map<Character, List<Character>> convertMap = new HashMap<>();
        convertMap.put('0', Arrays.asList('1', '9'));
        convertMap.put('9', Arrays.asList('0', '8'));

        List<String> res = new LinkedList<>();

        char[] array = cur.toCharArray();
        for (int i = 0; i < cur.length(); i++) {
            char c = array[i];
            if (convertMap.containsKey(c)) {
                List<Character> converted = convertMap.get(c);
                for (Character cc: converted) {
                    array[i] = cc;
                    res.add(String.valueOf(array));
                }
            } else {
                for (int j: new int[] {1, -1}) {
                    char cc = (char)(c + j);
                    array[i] = cc;
                    res.add(String.valueOf(array));
                }
            }
            array[i] = c;
        }

        return res;
    }
}
