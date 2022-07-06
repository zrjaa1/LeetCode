package String;

public class ReverseWordII {
    public void reverseWords(char[] str) {
        if (str == null || str.length == 0) return;
        int len = str.length;
        reverseByRange(str, 0, len-1);
        int fast = 0, slow = 0;
        while (fast < len) {
            while (slow < fast || slow < len && str[slow] == ' ') slow++;
            while (fast < slow || fast < len && str[fast] != ' ') fast++;
            swap(str, slow, fast-1);
        }
    }

    private void reverseByRange(char[] c, int l, int r) {
        while (l < r) {
            swap(c, l++,r--);
        }
    }

    private void swap(char[] c, int l, int r) {
        char temp = c[l];
        c[l] = c[r];
        c[r] = temp;
    }

    public static void main(String[] args) {
        ReverseWordII tester = new ReverseWordII();
        char[] str = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        tester.reverseWords(str);
    }
}
