package Basic;

/*
计算x^n, 需要注意的是n == -2^31时的溢出问题
Note: -2^31 原码是10000...000, 计算机中取反的方式是所有位（含符号位）取反然后加1，成为新的数的原码
因此 -2^31取反是自身，不处理的话会stackoverflow
 */
public class MyPow {
    public double myPow(double x, int n) {
        if (n < 0 && n != Integer.MIN_VALUE) return 1/myPow(x, -n);
        if (n == Integer.MIN_VALUE) {
            if (x > 1) return 0;
            else return 1/(myPow(x, -n+1)*x);
        }
        if (n == 0) return 1;
        if (n == 1) return x;
        double res = myPow(x, n/2);
        if (n % 2 == 0) return res*res;
        else return res*res*x;
    }
}
