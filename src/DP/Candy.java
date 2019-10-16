package DP;

/*
135. Candy
Hard

416

106

Favorite

Share
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
 */


/* Thoughts
Two pass, the first one go from left to right, looking back to check (rating[i] > rating[i-1]) if we need to add candy to candy[i];
The second pass go from right to left, looking back to check (rating[i] > rating[i+1]) if we need to add candy to candy[i];
Two passes to achieve the desired situation.
Since we always look back, in each pass, we can make sure that in the direction we looked back, the relation is stable.
The only concern we may have is if the second pass make the relation in the first pass unstable.
In this case, we may wonder if is possible, that in the first pass, a lower one compared to its left neighbors, e.g.
2 in the [3, 2] is assigned to 1 candy and it is greater than its right neighbor so that in the second pass, it is granted more candies.
e.g. [3, 2, 1]. However, all the extra candies will be granted in the second pass in this case.
We construct the way "looking back" Such that, if one is lower than its neighbor in ratings, then the neighbor will be granted once
itself is determined.
*/
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length <= 1) return ratings.length;

        int n = ratings.length;
        int res = 0;
        int[] candy = new int[n];
        for (int i = 0; i < n; i++) {
            candy[i] = 1;
            res++;
        }

        for (int i = 1; i < n; i++) {
            while (ratings[i] > ratings[i-1] && candy[i] <= candy[i-1]) {
                candy[i]++;
                res++;
            }
        }

        for (int i = n-2; i >= 0; i--) {
            while (ratings[i] > ratings[i+1] && candy[i] <= candy[i+1]) {
                candy[i]++;
                res++;
            }
        }
        return res;
    }
}
