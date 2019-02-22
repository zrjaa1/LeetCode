package binarySearch;

// *Leetcode* #278
//You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
//
//Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
//
//You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
//
//Example:
//
//Given n = 5, and version = 4 is the first bad version.
//
//call isBadVersion(3) -> false
//call isBadVersion(5) -> true
//call isBadVersion(4) -> true
//
//Then 4 is the first bad version. 

// Thoughts: Binary Search
/* The isBadVersion API is defined in the parent class VersionControl.
boolean isBadVersion(int version); */

public class FirstBadVersion {
	public int firstBadVersion(boolean[] versions) {
	  if (versions.length == 0) return -1;
	  int start = 0;
	  int end = versions.length-1;
	  int mid = 0;
	  while (start + 1 < end) {
	      mid = start + (end-start)/2;
	      if (versions[mid]) end = mid; // if it is bad version
	      else start = mid;
	  }
	  return end;
	}
	
	public static void main(String[] args) {
		FirstBadVersion test = new FirstBadVersion();
		boolean[] versions = {false, false, false, false, true};
		int res = test.firstBadVersion(versions);
		System.out.println(res);
	}
}

