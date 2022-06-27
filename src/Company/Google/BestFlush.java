package Company.Google;

import java.util.Collections;
import java.util.List;

/**
 * Given a list of card, find the best flush that you can get.
 * Follow up: There might be more numbers and card types.
 * From: https://www.1point3acres.com/bbs/thread-889010-1-1.html
 */
public class BestFlush {
    class Card {
        int number;
        String type; // RED, BLACK, so on
    }

    /**
     * Sol1: Use dp[] for each card type. For example, red[i] indicates the longest length of consecutive red cards,
     * ending with redi+1. If the input is RED8, RED9, then red[7] should be 1 and red[8] be 2. Record the max card number along the iteration.
     * O(T) = O(n) where n is the length of input, O(S) = O(k * t) where k is the possible card numbers and t is possible card type
     *
     * What if we have n numbers and card types? O(S) would be O(n * n) in total
     *
     * Sol2: We could split input into different list based on the type which takes O(n) time, sort each, which takes O(nlogn) worst case.
     * And iterate each list to find the biggest flush.
     * O(T) = O(nlogn), O(S) = O(n)
     */
    List<Card> getBestFlush(List<Card> cards) {
        return Collections.emptyList();
    }
}
