package Company;
import java.util.*;

public class FindClosestPoints {
    List<List<Integer>> ClosestXdestinations(int numDestinations,
                                             List<List<Integer>> allLocations,
                                             int numDeliveries)
    {
        // WRITE YOUR CODE HERE
        List<List<Integer>> res = new ArrayList<>();

        if(numDestinations <= 0 || numDeliveries <=0 ||
                numDeliveries > numDestinations || numDestinations > allLocations.size()
                || numDeliveries > allLocations.size() || allLocations.get(0) == null || allLocations.get(0).size() == 0){

            throw new IllegalArgumentException("Invalid Input!");
        }

        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(numDestinations, new Comparator<List<Integer>>(){
            @Override
            public int compare(List<Integer> a, List<Integer> b){
                return (long) b.get(0) * (long) b.get(0) + (long) b.get(1) * (long) b.get(1) -
                        (long) a.get(0) * (long) a.get(0) - (long) a.get(1) * (long) a.get(1) < 0 ? 1 : -1;
            }
        });

        for(List<Integer> cur : allLocations){
            if(cur.size() != 2){
                throw new IllegalArgumentException("Invalid Input");
            }
            minHeap.offer(cur);
        }
        for(int i = 0; i < numDeliveries; i++){
            res.add(minHeap.poll());
        }
        return res;
    }
    // METHOD SIGNATURE ENDS

    public static void main(String[] args) {
        FindClosestPoints tester = new FindClosestPoints();
        List<List<Integer>> allLocations = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(1);
        List<Integer> row2 = new ArrayList<>();
        row2.add(1);
        row2.add(1);
        List<Integer> row3 = new ArrayList<>();
        row3.add(1);
        row3.add(1);
        allLocations.add(row1);
        allLocations.add(row2);
        allLocations.add(row3);
        List<List<Integer>> res = tester.ClosestXdestinations(3, allLocations, 2);
        System.out.print("finished");
    }
}
