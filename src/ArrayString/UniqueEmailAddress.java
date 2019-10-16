package ArrayString;
import java.util.*;
/*
929. Unique Email Addresses
Easy

375

108

Favorite

Share
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?



Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails


Note:

1 <= emails[i].length <= 100
1 <= emails.length <= 100
Each emails[i] contains exactly one '@' character.
 */

/*
Note: @之后的'.'和'+'都不生效了
 */
public class UniqueEmailAddress {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < emails.length; i++) {
            boolean ignore = false;
            boolean at = false;
            for (int j = 0; j < emails[i].length(); j++) {
                char c = emails[i].charAt(j);
                if (c == '+' && !at) ignore = true;
                else if (c == '.' && !at) continue;
                else if (c == '@') {
                    ignore = false;
                    at = true;
                } else {
                    if (ignore) continue;
                    else sb.append(c);
                }
            }
            set.add(sb.toString());
            sb.setLength(0);
        }
        return set.size();
    }
}
