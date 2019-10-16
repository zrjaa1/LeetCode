package Others;
import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.length == 0 || tickets[0] == null || tickets[0].length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            if (!map.containsKey(from)) {
                List<String> list = new LinkedList<>();
                list.add(to);
                map.put(from, list);
            } else {
                List<String> list = map.get(from);
                list.add(to);
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }

        String cur = "JFK";
        while (map.size() != 0) {
            res.add(cur);
            List<String> dst = map.get(cur);
            String next = "";
            int i = 0;
            while (!map.containsKey(next)) {
                next = map.get(cur).get(i);
                i++;
            }
            dst.remove(i-1);
            if (dst.size() == 0) map.remove(cur);
            cur = next;
        }
        res.add(cur);
        return res;
    }

    public static void main(String[] args) {
        ReconstructItinerary tester = new ReconstructItinerary();
        String[][] tickets = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
        tester.findItinerary(tickets);
    }
}
