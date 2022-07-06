package Array;

/*
273. Integer to English Words
Hard

494

1306

Favorite

Share
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 123456789
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */

/*
主函数 handle >= 1000, helper handle < 1000
注意空格的地方
 */

public class IntegerToEnglishWords {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String word = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0)
                word = LessThanThousand(num%1000) + THOUSANDS[i] + " " + word;
            i++;
            num = num/1000;
        }
        return word.trim();
    }

    private String LessThanThousand(int num) {
        if (num == 0) return "";
        if (num < 20) return LESS_THAN_20[num] + " ";
        if (num < 100) return TENS[num/10] + " " + LessThanThousand(num%10);
        else return LESS_THAN_20[num/100] + " Hundred " + LessThanThousand(num%100);
    }
}
