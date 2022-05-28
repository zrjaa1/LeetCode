package Others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        List<List<Integer>> starts = new ArrayList<>(n); // list.get(i) - start times of ith function
        List<List<Integer>> ends = new ArrayList<>(n); // list.get(i) - end times of ith function
        for (int i = 0; i < n; i++) {
            starts.add(new ArrayList<>());
            ends.add(new ArrayList<>());
        }
        Collections.fill(starts, new ArrayList<>());
        Collections.fill(ends, new ArrayList<>());
        for (int i = 0; i < logs.size(); i++) {
            processLog(logs.get(i), starts, ends);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (Integer start : starts.get(i)) {
                sum -= start;
            }
            for (Integer end : ends.get(i)) {
                sum += end;
            }
            res[i] = sum;
        }
        return res;
    }

    private void processLog(String log, List<List<Integer>> start, List<List<Integer>> end) {
        String[] messages = log.split(":");
        if (messages[1].equals("end")) {
            end.get(Integer.parseInt(messages[0])).add(Integer.valueOf(messages[2]));
        } else {
            start.get(Integer.parseInt(messages[0])).add(Integer.valueOf(messages[2]));
        }
    }
}
