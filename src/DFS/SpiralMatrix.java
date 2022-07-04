package DFS;
import java.util.*;
/*
54. Spiral Matrix
Medium

925

360

Favorite

Share
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

/*
Sprial Print(螺旋打印). Use an offset to record the current location and rowSize & colSize to deal with the corner case
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return res;
        spiral(matrix, 0, matrix.length, matrix[0].length, res);
        return res;
    }
    private void spiral(int[][] matrix, int offset, int rowSize, int colSize, List<Integer> res) {
        if (rowSize <= 0 || colSize <= 0) return;
        if (rowSize == 1) {
            for (int i = 0; i < colSize; i++) res.add(matrix[offset][offset+i]);
            return;
        }
        if (colSize == 1) {
            for (int i = 0; i < rowSize; i++) res.add(matrix[offset+i][offset]);
            return;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < colSize-1; i++) {
            res.add(matrix[offset][offset+i]);
        }
        for (int i = 0; i < rowSize-1; i++) {
            res.add(matrix[offset+i][col-offset-1]);
        }
        for (int i = 0; i < colSize-1; i++) {
            res.add(matrix[row-offset-1][col-offset-1-i]);
        }
        for (int i = 0; i < rowSize-1; i++) {
            res.add(matrix[row-offset-1-i][offset]);
        }
        spiral(matrix, offset+1, rowSize-2, colSize-2, res);
    }
}
