package Others;
import java.util.*;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        if (n == 0) return tasks.length;
        // Key - Task Type, Value - reamining tasks * n + cooldown time
        // cooldown time = value % n; reaming task = value / n;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            Character c = tasks[i];
            if (!map.containsKey(c)) {
                map.put(c, n+1);
            } else {
                map.put(c, map.get(c)+n+1);
            }
        }
        int count = 0;
        while (map.size() != 0) {
            boolean found = false;
            Character toBeRemoved = null;
            for (Character c : map.keySet()) {
                int time = map.get(c);

            }
            if (toBeRemoved != null) map.remove(toBeRemoved);
            count++;
        }
        return count;
    }
}
