package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import util.ArrayUtils;

public class UniquePairs {
	
	// This function takes a list of ints and returns the count of unique pairs that sum to < T. O(N log N)
	public static int pairsUnderThreashold(List<Integer> lst, Integer T) {
		
		// Generate the unique ordered set of integers to avoid duplication.
		TreeSet<Integer> set = new TreeSet<Integer>(lst);
		Integer[] uniques = (set).toArray(new Integer[set.size()]);
		ArrayUtils.printArray(uniques);
		int count = 0;
		for (int i = 0; i < uniques.length - 1; ++i)
			for (int j = i + 1; uniques[i] + uniques[j] < T; ++j)
				++count;
		return count;
	}

	public static void main(String[] args) {
		ArrayList<Integer> ary = new ArrayList<Integer>();
		ary.add(1);
		ary.add(1);
		ary.add(2);
		ary.add(3);
		ary.add(4);
		ary.add(0);
		System.out.println(pairsUnderThreashold(ary, 3));
	}
}
