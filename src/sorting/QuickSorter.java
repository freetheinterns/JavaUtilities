package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.ArrayUtils;

public class QuickSorter {
	// This PRNG is used to avoid worst case run-times.
	private static final Random rand = new Random(System.currentTimeMillis());

	// These qsort functions are used to run quicksort on a whole list.
	public static <T extends Comparable<T>> void qsort(List<T> ary) {
		qhelp(ary, 0, ary.size() - 1);
	}

	public static <T extends Comparable<T>> void qsort(T[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(double[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(float[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(long[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(int[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(short[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(byte[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	public static void qsort(char[] ary) {
		qhelp(ary, 0, ary.length - 1);
	}

	// These qhelp functions are the recursive backbones of the quicksort algorithm.
	public static <T extends Comparable<T>> void qhelp(List<T> ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static <T extends Comparable<T>> void qhelp(T[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(float[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(double[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(long[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(int[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(short[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(byte[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	public static void qhelp(char[] ary, int start, int end) {
		if (start < end) {
			int pivot = partition(ary, start, end);
			qhelp(ary, start, pivot - 1);
			qhelp(ary, pivot + 1, end);
		}
	}

	// These partition functions choose a random piviot, then partition their segment around that piviot value.
	private static <T extends Comparable<T>> int partition(List<T> ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		T pivot = ary.get(end);
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary.get(above).compareTo(pivot) <= 0) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static <T extends Comparable<T>> int partition(T[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		T pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above].compareTo(pivot) <= 0) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(double[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		double pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(float[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		float pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(long[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		long pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(int[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		int pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(short[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		short pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(byte[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		byte pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	private static int partition(char[] ary, int start, int end) {
		ArrayUtils.swap(ary, rand.nextInt(end - start) + start, end);
		char pivot = ary[end];
		int below = start - 1;
		for (int above = start; above < end; ++above) {
			if (ary[above] <= pivot) {
				ArrayUtils.swap(ary, ++below, above);
			}
		}
		ArrayUtils.swap(ary, ++below, end);
		return below;
	}

	// This main function test to make sure that all of the types of lists can be sorted.
	public static void main(String[] args) {
		// Initialize lists of every type
		byte[] a = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		short[] b = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		int[] c = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		long[] d = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		float[] e = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		double[] f = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		char[] g = {'D', 'J', '9', '5', 'C', '8', '7', '4', 'L', '2', '6', 'B'};
		ArrayList<Byte> h = new ArrayList<Byte>();
		for (byte x : a) {
			h.add(x);
		}

		// Print unsorted lists
		System.out.println("UNSORTED:");
		ArrayUtils.printArray(a);
		ArrayUtils.printArray(b);
		ArrayUtils.printArray(c);
		ArrayUtils.printArray(d);
		ArrayUtils.printArray(e);
		ArrayUtils.printArray(f);
		ArrayUtils.printArray(g);
		ArrayUtils.printArray(h);

		// Sort all of the lists
		qsort(a);
		qsort(b);
		qsort(c);
		qsort(d);
		qsort(e);
		qsort(f);
		qsort(g);
		qsort(h);

		// Print sorted results
		System.out.println();
		System.out.println("SORTED:");
		ArrayUtils.printArray(a);
		ArrayUtils.printArray(b);
		ArrayUtils.printArray(c);
		ArrayUtils.printArray(d);
		ArrayUtils.printArray(e);
		ArrayUtils.printArray(f);
		ArrayUtils.printArray(g);
		ArrayUtils.printArray(h);
	}
}
