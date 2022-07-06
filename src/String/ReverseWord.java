package String;

public class ReverseWord {
    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] c = s.toCharArray();
        int len = c.length;

        // first, reverse by bit
        reverseStr(c, 0, len);

        // then figure out each word, reverse by word
        int slow = 0;
        int fast = 0;
        while (fast < len - 1) {
            while (slow < fast || slow < len -1 && c[slow] == ' ') slow++;
            while (fast < slow || fast < len -1 && c[fast] != ' ') fast++;
            if (fast == len - 1) reverseStr(c, slow ,fast+1);
            else reverseStr(c, slow ,fast);
        }
        String result = c.toString();
        return result;
    }

    private void reverseStr(char[] c, int l, int r) {
        int i = 0;
        while (i < (r-l)/2) {
            swap(c, l+i, r-i-1);
            i++;
        }
    }

    private void swap(char[] c, int l, int r) {
        char temp = c[l];
        c[l] = c[r];
        c[r] = temp;
    }

    public static void main(String[] args) {
        ReverseWord tester = new ReverseWord();
        String s = "the sky is blue";
        String result = tester.reverseWords(s);
        System.out.println(result);
    }
}
