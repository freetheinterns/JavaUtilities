package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import util.ArrayUtils;

// This class holds a List of Strings ordered by length.
public class SubstringDictionary implements Comparator<String> {
	private List<String> Q;

	public SubstringDictionary(List<String> dict) {
		Q = dict;
		Collections.sort(Q, this);
	}

	// Iterate through the List and return the length of the first string that contains the sub, or the first string
	// that sub contains.
	public int longestSubstring(String sub) {
		for (String str : Q) {
			int L = str.length();
			if (L >= sub.length()) {
				if (str.contains(sub))
					return L;
			} else if (sub.contains(str))
				return L;
		}
		return -1;
	}

	@Override
	public int compare(String A, String B) {
		return B.length() - A.length();
	}

	@Override
	public String toString() {
		return ArrayUtils.arrayToString(Q);
	}

	public static void main(String[] args) {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("Long");
		temp.add("Longer");
		temp.add("Longest");
		temp.add("Dog");
		temp.add("cat");
		SubstringDictionary D = new SubstringDictionary(temp);
		System.out.println(D);
		System.out.println(D.longestSubstring("at"));
		System.out.println(D.longestSubstring("ong"));
		System.out.println(D.longestSubstring("Longly"));
	}
}
