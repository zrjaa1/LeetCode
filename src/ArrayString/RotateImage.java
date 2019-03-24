package ArrayString;

/*
48. Rotate Image
Medium

1350

130

Favorite

Share
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
 */

/*
similar to Spiral Print Matrix， 坐标对应关系。把四次assign放到同一个for loop里
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
        int size = matrix.length;
        int offset = 0, temp = 0;
        while (size > 1) {
            for (int i = 0; i < size-1; i++) {
                temp = matrix[offset][offset+i];
                matrix[offset][offset+i] = matrix[offset+size-1-i][offset];
                matrix[offset+size-1-i][offset] = matrix[offset+size-1][offset+size-1-i];
                matrix[offset+size-1][offset+size-1-i] = matrix[offset+i][offset+size-1];
                matrix[offset+i][offset+size-1] = temp;
            }
            size-=2;
            offset++;
        }
    }
}
