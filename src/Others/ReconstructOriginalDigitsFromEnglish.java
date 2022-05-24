package Others;

/**
 * 423. Reconstruct Original Digits from English: https://leetcode.com/problems/reconstruct-original-digits-from-english/
 * Medium
 *
 * Given a string s containing an out-of-order English representation of digits 0-9, return the digits in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "owoztneoer"
 * Output: "012"
 * Example 2:
 *
 * Input: s = "fviefuro"
 * Output: "45"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * s is guaranteed to be valid.
 *
 */

/**
 * 比较Tricky的点在于这是一道规律观察题，而不是dfs。当然dfs也可以做，但无疑会exceeds time limit。只需要知道做法就行，没必要记住。
 */
public class ReconstructOriginalDigitsFromEnglish {
    public String originalDigits(String s) {
        // building hashmap letter -> its frequency
        char[] count = new char[26 + (int)'a'];
        for(char letter: s.toCharArray()) {
            count[letter]++;
        }

        // building hashmap digit -> its frequency
        int[] out = new int[10];
        // letter "z" is present only in "zero"
        out[0] = count['z'];
        // letter "w" is present only in "two"
        out[2] = count['w'];
        // letter "u" is present only in "four"
        out[4] = count['u'];
        // letter "x" is present only in "six"
        out[6] = count['x'];
        // letter "g" is present only in "eight"
        out[8] = count['g'];
        // letter "h" is present only in "three" and "eight"
        out[3] = count['h'] - out[8];
        // letter "f" is present only in "five" and "four"
        out[5] = count['f'] - out[4];
        // letter "s" is present only in "seven" and "six"
        out[7] = count['s'] - out[6];
        // letter "i" is present in "nine", "five", "six", and "eight"
        out[9] = count['i'] - out[5] - out[6] - out[8];
        // letter "n" is present in "one", "nine", and "seven"
        out[1] = count['n'] - out[7] - 2 * out[9];

        // building output string
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < 10; i++)
            for (int j = 0; j < out[i]; j++)
                output.append(i);
        return output.toString();
    }
}
