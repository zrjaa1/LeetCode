package Array;

// "-(a+b)+c
public class RemoveBracket {
    public String formula(String s) {
        StringBuilder sb = new StringBuilder();
        helper(s, true, sb);
        return sb.toString();
    }

    private void helper(String s, boolean positive, StringBuilder sb) {
        if (s == null || s.length() == 0) return;

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '+') {
                i++;
                if (positive) sb.append('+');
                else sb.append('-');
                continue;
            }
            else if (s.charAt(i) == '-') {
                if (positive) sb.append('-');
                else sb.append('+');
                if (s.charAt(i+1) == '(') {
                    int leftCount = 1;
                    int j = i + 2;
                    while (leftCount != 0 && j < s.length()) {
                        if (s.charAt(j) == '(') leftCount++;
                        else if (s.charAt(j) == ')') leftCount--;
                        j++;
                    }
                    helper(s.substring(i + 2, j - 1), !positive, sb);
                    i = j;
                    continue;
                }
            }

            else if (s.charAt(i) == '(') {
                int leftCount = 1;
                int j = i+1;
                while (leftCount != 0 && j < s.length()) {
                    if (s.charAt(j) == '(') leftCount++;
                    else if (s.charAt(j) == ')') leftCount--;
                    j++;
                }
                helper(s.substring(i+1, j-1), positive, sb);
                i = j;
                continue;
            }
            else {
                sb.append(s.charAt(i));
            }
            i++;
        }
    }

    public static void main(String[] args) {
        RemoveBracket tester = new RemoveBracket();
        String s = "-((a+b)+c-(d+e)+g-h)";
        System.out.print(tester.formula(s));
    }
}
