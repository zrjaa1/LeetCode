package slidingWindow;

public class LongestSubstringAtMost2 {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() <= 2) return s.length();
        
        int start = 0;
        int max = 2;
        int[] counts = new int[128];	// record the last apperance index
        
        int type = 0;
        
        for (int end = 0; end < s.length(); end++) {
        	char c = s.charAt(end);
            if (counts[c] == 0) {
                counts[c]++;
                type++;
            } 
                
            while (type > 2 && start <= end) {
                	char delete = s.charAt(start);
                	if (--counts[delete] == 0)
                		type--;
                	start++;
            }
            
            max = Math.max(end-start+1, max);
        }
        
        return max;
    }
        
    public static void main(String[] args) {
    	LongestSubstringAtMost2 test = new LongestSubstringAtMost2();
    	String s = "abaccc";
    	int res = test.lengthOfLongestSubstringTwoDistinct(s);
    	System.out.println(res);
    }
}
