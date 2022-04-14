package BinarySearch;

/**
 * 302. Smallest Rectangle Enclosing Black Pixels: https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 * Hard
 *
 * You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.
 *
 * The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.
 *
 * Given two integers x and y that represents the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * You must write an algorithm with less than O(mn) runtime complexity
 *
 *
 *
 * Example 1:
 *
 *
 * Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
 * Output: 6
 * Example 2:
 *
 * Input: image = [["1"]], x = 0, y = 0
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 100
 * image[i][j] is either '0' or '1'.
 * 1 <= x < m
 * 1 <= y < n
 * image[x][y] == '1'.
 * The black pixels in the image only form one component.
 *
 */

/**
 * O(T) = O(m * logn + n * logm), O(S) = O(1) not considering stack
 * 主要考察Binary Search，以及x、y坐标在search中的熟练运用
 */
public class SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
            return 0;
        }

        if (x < 0 || x >= image[0].length || y < 0 || y >= image.length) {
            throw new IllegalArgumentException("input is invalid");
        }

        int xMin = searchLeft(image, y);
        int xMax = searchRight(image, y);
        int yMin = searchTop(image, x);
        int yMax = searchBot(image, x);

        return (xMax - xMin + 1) * (yMax - yMin + 1);
    }

    private int searchLeft(char[][] image, int y) {
        int start = 0;
        int end = y;
        while (start <= end) { // O(T) = O(logm * n)
            int mid = start + (end - start) / 2;
            if (lookVertically(image, mid)) { // contains 1s, O(T) = O(n)
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int searchRight(char[][] image, int y) {
        int start = y;
        int end = image[0].length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (lookVertically(image, mid)) { // contains 1s
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    private int searchTop(char[][] image, int x) {
        int start = 0;
        int end = x;
        while (start <= end) { // O(T) = O(m * logn)
            int mid = start + (end - start) / 2;
            if (lookHorizontally(image, mid)) { // contains 1s
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int searchBot(char[][] image, int x) {
        int start = x;
        int end = image.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (lookHorizontally(image, mid)) { // contains 1s
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    private boolean lookVertically(char[][] image, int column) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][column] == '1') {
                return true;
            }
        }
        return false;
    }

    private boolean lookHorizontally(char[][] image, int row) {
        for (int i = 0; i < image[0].length; i++) {
            if (image[row][i] == '1') {
                return true;
            }
        }
        return false;
    }
}
