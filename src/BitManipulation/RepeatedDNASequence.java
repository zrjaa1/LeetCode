package BitManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// *Leetcode* #187
//All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
//
//Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
//
//Example:
//
//Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//
//Output: ["AAAAACCCCC", "CCCCCAAAAA"]

// *Thoughts* 
// Use the integer to represent the DNA sequence

public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
    	ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() < 10) return res;
         
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        int mask = 0b00000000000011111111111111111111;
        int recorder = 0;
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == 'A')
        		recorder = (recorder << 2) | 0;
        	else if (s.charAt(i) == 'C')
        		recorder = (recorder << 2) | 1;
        	else if (s.charAt(i) == 'G')
        		recorder = (recorder << 2) | 2;
        	else if (s.charAt(i) == 'T')
        		recorder = (recorder << 2) | 3;
        	else 
        		throw new IllegalArgumentException();
        	if (i >= 9) {
        		if (map.containsKey(recorder&mask))  {
        			Integer freq = map.get(recorder&mask);
        			if (freq <= 1) 
        				res.add(integerToString(recorder&mask));
        			map.put(recorder&mask, freq+1);
        		} else {
        			map.put(recorder&mask, 1);
        		}
        	}
        }
        return res;
    }
    
    public String integerToString(Integer value) {
    	if (value == null) return null;
    	
    	StringBuilder sb = new StringBuilder();
    	Integer mask = 3;
    	for (int i = 0; i < 10; i++) {
    		if ((mask & value) == 0) 
    			sb.insert(0, 'A');
    		else if ((mask & value) == 1) 
    			sb.insert(0, 'C');
    		else if ((mask & value) == 2) 
    			sb.insert(0, 'G');
    		else if ((mask & value) == 3) 
    			sb.insert(0, 'T');
    		value = value >> 2;
    	}
    	return sb.toString();
    }
    public static void main(String[] args) {
    	RepeatedDNASequence test = new RepeatedDNASequence();
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = test.findRepeatedDnaSequences(s);
        System.out.println(res);
    }
}