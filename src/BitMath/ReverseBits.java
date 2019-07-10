package BitMath;
import java.util.*;

public class ReverseBits {
    /*
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            int temp = (n >> i) & 1;
            res |= temp;
        }
        return res;
    }
    */
    // sol2: xor

    public int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            int left = 1 & (n >> (31-i));
            int right = 1 & (n >> i);
            if (left != right) {
                n ^= 1 << (31-i);
                n ^= 1 << i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        ReverseBits tester = new ReverseBits();
        int n = 0b00000010100101000001111010011100;
        tester.reverseBits(n);
    }
}
