package problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import util.ArrayUtils;

// This class holds a List of Strings ordered by length.
public class SubstringDictionary implements Comparator<String> {
	private List<String> stringsSortedByLength;

	// This constructor takes a list of strings and creates the properly ordered list.
	public SubstringDictionary(List<String> dict) {
		stringsSortedByLength = dict;

		// Use the comparator in this class to order the strings by length. (longer to shorter)
		Collections.sort(stringsSortedByLength, this);
	}

	// Iterate through the List and return the length of the first string that sub contains, which is also the longest.
	public int longestSubstring(String sub) {
		for (String str : stringsSortedByLength) {
			if (sub.contains(str)) {
				return str.length();
			}
		}
		return -1;
	}

	@Override
	public int compare(String A, String B) {
		// Compares length, not alphabetical ordering
		return B.length() - A.length();
	}

	@Override
	public String toString() {
		return ArrayUtils.collectionToString(stringsSortedByLength);
	}

	public static void main(String[] args) {
		List<String> unorderedList = Arrays.asList(new String[] {"Long", "Longer", "Longest", "Dog", "Cat"});
		SubstringDictionary dic = new SubstringDictionary(unorderedList);
		System.out.println(dic);
		System.out.println(dic.longestSubstring("Alphabet"));
		System.out.println(dic.longestSubstring("ThisIsAMuchLongerSentenceThanSomeOthers"));
	}
}
