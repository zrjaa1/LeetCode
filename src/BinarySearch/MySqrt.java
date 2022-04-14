package BinarySearch;

// Leetcode #69
//Implement int sqrt(int x).
//
//Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
//
//Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
//
//Example 1:
//
//Input: 4
//Output: 2
//Example 2:
//
//Input: 8
//Output: 2
//Explanation: The square root of 8 is 2.82842..., and since
//             the decimal part is truncated, 2 is returned.

// Thoughts:
//主要是溢出问题，不仅仅是temp，mid也必须要用long，不然在mid*mid那一步就已经错了

class MySqrt {
    // Using template 1
  public int mySqrt(int x) {
      if (x == 0 || x == 1) return x;

      int left = 0;
      int right = x/2;
      long mid = 0;
      long temp_low = 0;
      long temp_high = 0;
      while (left < right) {
          mid = left + (right-left)/2;
          temp_low = mid * mid;
          temp_high = (mid+1) * (mid+1);
          if (temp_low <= x && temp_high > x) return (int)mid;
          if (temp_low > x) right = (int)mid;
          else left = (int)mid+1;
      }
      return left;
  }

  // Using template 3
    public int mySqrtSol3(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int start = 1, end = x/2;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid > x/mid) {
                end = mid - 1;
            } else { // mid * mid < x
                start = mid + 1;
            }
        }
        return end;
    }

  public static void main(String[] args) {
	  MySqrt test = new MySqrt();
	  int target = 4;
	  int res = test.mySqrt(target);
	  System.out.println(res);
  }
}
