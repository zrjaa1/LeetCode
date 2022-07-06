package String;

public class Trim {
    // sol1: slow fast pointers, Always keep the space before the character, note this solution also reduces the # of whitespace in string to only 1 if they are multiple consecutively.
    public String trimSol1(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            if (chars[fast] != ' ' || (fast != s.length()-1 && chars[fast+1] != ' '))
                chars[slow++] = chars[fast];
        }

        if (slow == 0) return "";
        return (chars[0] == ' ') ? new String(chars, 1, slow-1) : new String(chars, 0, slow);
    }

    // sol2: find start and end index, keep multiple whitespaces in the middle.
    public String trimSol2(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        int start = findEndpoint(s, true);
        int end = findEndpoint(s, false);
        if (start > end) {
            return "";
        }

        return s.substring(start, end + 1);
    }

    private int findEndpoint(String s, boolean forward) {
        if (forward) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ') {
                    return i;
                }
            }
            return s.length() - 1;
        } else {
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) != ' ') {
                    return i;
                }
            }
            return 0;
        }
    }

    // sol3: use StringBuilder
    public String trimWithStringBuilder(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = helper(new StringBuilder(s));
        sb = helper(sb.reverse());
        return sb.reverse().toString();
    }

    private StringBuilder helper(StringBuilder sb) {
        int cur = -1;
        while (sb.charAt(++cur) == ' ') {}
        return new StringBuilder(sb.substring(cur, sb.length()));
    }

    public static void main(String[] args) {
        Trim tester = new Trim();
        String s = "  slfkj ddd  ";
        String res = tester.trimSol2(s);
        System.out.println(res);
    }
}
