package problems;

import java.util.List;
import java.util.TreeSet;

import util.ArrayUtils;

public class UniquePairs {

	// This function takes a list of ints and returns the count of unique pairs that sum to < T. O(N log N)
	public static int pairsUnderMax(List<Integer> lst, Integer T) {

		// Generate the unique ordered set of integers to avoid duplication.
		TreeSet<Integer> set = new TreeSet<Integer>(lst);
		Integer[] uniques = (set).toArray(new Integer[set.size()]);
		ArrayUtils.printArray(uniques);
		int count = 0;
		for (int i = 0; i < uniques.length - 1; ++i) {
			for (int j = i + 1; uniques[i] + uniques[j] < T; ++j) {
				++count;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		List<Integer> ary = ArrayUtils.createList(new int[] {0, 1, 1, 2, 3, 4, 5});
		System.out.println(pairsUnderMax(ary, 3));
	}
}
