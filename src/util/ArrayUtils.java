package util;

import java.util.Arrays;
import java.util.Collection;

// The purpose of this class is to house static helper functions that run small jobs on any type of Array or List.
public class ArrayUtils {
	// These arrayToString functions parse any ordered list into a readable string format.
	public static <T> String collectionToString(Collection<T> ary) {
		if (ary == null) return "[]";
		String str = "[";
		for (T t : ary)
			if (t instanceof Collection<?>) {
				str += collectionToString((Collection<?>) t) + ", ";
			} else if (t instanceof Object[]) {
				str += Arrays.toString((Object[]) t) + ", ";
			} else if (t instanceof byte[]) {
				str += Arrays.toString((byte[]) t) + ", ";
			} else if (t instanceof short[]) {
				str += Arrays.toString((short[]) t) + ", ";
			} else if (t instanceof int[]) {
				str += Arrays.toString((int[]) t) + ", ";
			} else if (t instanceof long[]) {
				str += Arrays.toString((long[]) t) + ", ";
			} else if (t instanceof float[]) {
				str += Arrays.toString((float[]) t) + ", ";
			} else if (t instanceof double[]) {
				str += Arrays.toString((double[]) t) + ", ";
			} else if (t instanceof char[]) {
				str += Arrays.toString((char[]) t) + ", ";
			} else str += t.toString() + ", ";
		return str.substring(0, str.length() - 2) + "]";
	}

	// This print function use the above toString definitions to print to system.out.
	public static <T> void printCollection(Collection<T> ary) {
		System.out.println(collectionToString(ary));
	}

	public static <T> void swap(T[] ary, int a, int b) {
		T temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(byte[] ary, int a, int b) {
		byte temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(short[] ary, int a, int b) {
		short temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(int[] ary, int a, int b) {
		int temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(long[] ary, int a, int b) {
		long temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(float[] ary, int a, int b) {
		float temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(double[] ary, int a, int b) {
		double temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}

	public static void swap(char[] ary, int a, int b) {
		char temp = ary[a];
		ary[a] = ary[b];
		ary[b] = temp;
	}
}
