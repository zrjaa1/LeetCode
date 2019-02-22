package BinarySearch;

// Leetcode #74
//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//Integers in each row are sorted from left to right.
//The first integer of each row is greater than the last integer of the previous row.
//Example 1:
//
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//Output: true
//Example 2:
//
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//Output: false

// Thoughts
//2 solutions, binary search for twice or use index reflextion 
//sol1: Binary search for twice
//Time Complexity: o(logn+logm)
//Space Complexity: O(1)

//solution 2: index reflextion
class Search2DMatrix {
 public boolean search(int[][] matrix, int target) {
     // corner case
     if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
     
     int row = matrix.length;
     int col = matrix[0].length;
     int left = 0;
     int right = row * col - 1;
     int mid = 0;    
     while (left < right - 1) {
         mid = left + (right - left) / 2;
         int midRow = mid/col;
         int midCol = mid%col;
         if (matrix[midRow][midCol] == target) return true;
         else if (matrix[midRow][midCol] < target) left = mid;
         else right = mid;
     }
     if (matrix[left/col][left%col] == target || matrix[right/col][right%col] == target) return true;
     return false;
 }
 
 	public static void main(String[] args) {
 		Search2DMatrix test = new Search2DMatrix();
 		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
 		int target = 3;
 		boolean res = test.search(matrix, target);
 		System.out.println(res);
 	}
}
