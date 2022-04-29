package BDFS;

import java.util.LinkedList;
import java.util.List;

/**
 * 93. Restore IP Addresses: https://leetcode.com/problems/restore-ip-addresses/
 * Medium
 *
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of digits only.
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        if (s == null ||s.length() > 32) {
            return res;
        }

        dfs(s, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(String s, int idx, int num, StringBuilder path, List<String> res) {
        if (num == 4) {
            if (idx == s.length()) {
                path.setLength(path.length() - 1);
                res.add(path.toString());
            }
            return;
        }

        int len = path.length();
        for (int i = 0; i < 3; i++) {
            if (idx + i + 1 > s.length()) break;
            int val = Integer.parseInt(s.substring(idx, idx + i + 1));
            if (val >= 0 && val <= 255) {
                path.append(val);
                path.append('.');
                dfs(s, idx + i + 1, num + 1, path, res);
                path.setLength(len);
            }
            if (val == 0) { // avoid 00.  only 1 0 can be present
                break;
            }
        }
    }
}
