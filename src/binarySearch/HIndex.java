package binarySearch;

// *Leetcode* #275
//Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
//
//According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
//
//Example:
//
//Input: citations = [0,1,3,5,6]
//Output: 3 
//Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
//             received 0, 1, 3, 5, 6 citations respectively. 
//             Since the researcher has 3 papers with at least 3 citations each and the remaining 
//             two with no more than 3 citations each, her h-index is 3.
//Note:
//
//If there are several possible values for h, the maximum one is taken as the h-index.
//
//Follow up:
//
//This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
//Could you solve it in logarithmic time complexity?

// *Thoughts*
// since we are trying to find the h index, which means your return value should be located at the hth position to the right. so it's a comparison
// between the index and the value on that index.
// and we have to use 3rd solution of binary search. By this way, it is guaranteed that the right+1 is the one we  

class HIndex {
	public int getIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        
        int len = citations.length;
        int left = 0;
        int right = len-1;
        int mid = 0;
        
        while (left <= right) {
            mid = left + (right-left)/2;
            if (citations[mid] == len-mid) return citations[mid];
            if(citations[mid] < len-mid) left = mid+1;
            else right = mid-1;
        }
        
        return len - (right+1);
    }

    public static void main(String[] args) {
    	HIndex test = new HIndex();
    	int[] citations = {0,2};
    	int res = test.getIndex(citations);
    	System.out.println(res);
    }
}
