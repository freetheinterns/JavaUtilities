package problems;

import util.ArrayUtils;

public class PhoneNumbers {

	// This function takes a phone number as a String and returns all possible corresponding String permutations given a
	// function for mapping digits to characters.
	public static String[] parsePhoneNumber(String pnum, String[] perms) {
		// Base case: empty String
		if (pnum.length() == 0)
			return perms;
		
		// Convert next (potential) digit to possible characters 
		String opts = numberToLetter(Character.getNumericValue(pnum.charAt(0)));
		// Decrement String
		pnum = pnum.substring(1);
		// Continue if no mapping exists for character
		if (opts == "")
			return parsePhoneNumber(pnum, perms);
		
		// Allocate and build next String Array.
		String[] next = new String[opts.length() * perms.length];
		int index = 0;
		for (String str : perms)
			for (char c : opts.toCharArray()) {
				next[index] = str + c;
				++index;
			}
		return parsePhoneNumber(pnum, next);
	}

	// These two functions will use the static number to char mapping that is shown on the iphone and below:
	// '' = > 0 : '' = > 1 : 'a,b,c' = > 2 : 'd,e,f' = > 3
	// 'g,h,i' = > 4 : 'j,k,l' = > 5 : 'm,n,o' = > 6
	// 'p,q,r,s' = > 7 : 't,u,v' = > 8 : 'w,x,y,z' = > 9
	public static String numberToLetter(int d) {
		if (d <= 1 || d >= 10)
			return "";
		if (d == 2)
			return "abc";
		if (d == 3)
			return "def";
		if (d == 4)
			return "ghi";
		if (d == 5)
			return "jkl";
		if (d == 6)
			return "mno";
		if (d == 7)
			return "pqrs";
		if (d == 8)
			return "tuv";
		return "wxyz";
	}

	public static void main(String[] args) {
		System.out.println("Test Case 1:");
		String[] ans = parsePhoneNumber("25", new String[] {""});
		ArrayUtils.printArray(ans);
		System.out.println();
		
		System.out.println("Test Case 2:");
		String pnum = "348 - 5928";
		String[] a2 = parsePhoneNumber(pnum, new String[] {""});
		System.out.println(a2.length + " possible permutations for [" + pnum + "]");
	}
}
