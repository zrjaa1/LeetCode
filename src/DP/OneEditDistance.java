package DP;

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) >= 2) return false;

        int n1 = s.length();
        int n2 = t.length();
        if (n1 > n2) return isOneEditDistance(t, s);

        // n1 <= n2
        for (int i = 0; i < n1; i++) {
            // replace or delete
            if (s.charAt(i) != t.charAt(i)) return s.substring(i+1, n1).equals(s.substring(i+1, n2)) || s.substring(i+1, n1).equals(s.substring(i, n2));
        }
        return n1 != n2;
    }

    public static void main(String[] args) {
        OneEditDistance tester = new OneEditDistance();
        String s = "a";
        String t = "t";
        System.out.println(tester.isOneEditDistance(s, t));
    }
}
