package Bit;

public class ReverseInteger {

    // note: change the divisor if the number is not decimal
    public int reverseInteger(int n) {
        long res = 0;
        while (n!=0) {
            res = res*10 + n % 10;
            n /= 10;
        }
        return (int)res;
    }

    public static void main (String[] args) {
        ReverseInteger tester = new ReverseInteger();
        int n = -11;
        tester.reverseInteger(n);
    }
}
