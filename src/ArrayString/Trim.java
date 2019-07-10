package ArrayString;

public class Trim {
    // sol1: Always keep the space before the character
    public String trim(String s) {
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

    public static void main(String[] args) {
        Trim tester = new Trim();
        String s = " 123  456  ";
        String res = tester.trim(s);
        System.out.print(res);

    }
}
