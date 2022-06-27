package OOD;

import java.util.*;

public class LoggerRateLimiter{
    Map<String, Integer> map;

    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer prevTimestamp = map.get(message);
        boolean res = prevTimestamp == null ? true : timestamp - prevTimestamp >= 10;
        if (res) {
            map.put(message, timestamp); // Note that only put when printing.
        }
        return res;
    }
}
