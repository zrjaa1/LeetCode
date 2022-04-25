package Bit;

public class DivideTwoInteger {
    public int divide(int dividend, int divisor) {
        try {
            if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
            if (divisor == 0) throw new IllegalArgumentException();
            long res = 0;
            int shift = 0;
            long newDividend = Math.abs((long)(dividend));
            long newDivisor = Math.abs((long)(divisor));
            while (newDividend >= newDivisor) {
                while (newDividend >= (newDivisor) << shift) {
                    shift++;
                }
                long temp = (long)1 << (shift - 1);
                res += temp;
                newDividend -= newDivisor*temp;
                shift = 0;
            }
            if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) return (int)-res;
            else return (int)res;
        }
        catch (IllegalArgumentException e) {
            System.out.print("IllegalArgument");
        }
        return 0;
    }

    public static void main(String[] args) {
        DivideTwoInteger tester = new DivideTwoInteger();
        tester.divide(-2147483648, 1);
    }
}
