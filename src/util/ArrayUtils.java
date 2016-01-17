package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// The purpose of this class is to house static helper functions that run small jobs on any type of Array or List.
public class ArrayUtils {
	// These arrayToString functions parse any ordered list into a readable string format.
	public static <T> String arrayToString(Collection<T> ary) {
		if (ary == null) return "[]";
		String str = "[";
		for (T t : ary)
			if (t instanceof Collection<?>) {
				str += arrayToString((Collection<?>) t) + ", ";
			} else if (t instanceof Object[]) {
				str += arrayToString((Object[]) t) + ", ";
			} else if (t instanceof byte[]) {
				str += arrayToString((byte[]) t) + ", ";
			} else if (t instanceof short[]) {
				str += arrayToString((short[]) t) + ", ";
			} else if (t instanceof int[]) {
				str += arrayToString((int[]) t) + ", ";
			} else if (t instanceof long[]) {
				str += arrayToString((long[]) t) + ", ";
			} else if (t instanceof float[]) {
				str += arrayToString((float[]) t) + ", ";
			} else if (t instanceof double[]) {
				str += arrayToString((double[]) t) + ", ";
			} else if (t instanceof char[]) {
				str += arrayToString((char[]) t) + ", ";
			} else str += t.toString() + ", ";
		return str.substring(0, str.length() - 2) + "]";
	}

	public static <T> String arrayToString(T[] ary) {
		if (ary == null) return "[]";
		String str = "[";
		for (T t : ary)
			if (t instanceof Collection<?>) {
				str += arrayToString((Collection<?>) t) + ", ";
			} else if (t instanceof Object[]) {
				str += arrayToString((Object[]) t) + ", ";
			} else if (t instanceof byte[]) {
				str += arrayToString((byte[]) t) + ", ";
			} else if (t instanceof short[]) {
				str += arrayToString((short[]) t) + ", ";
			} else if (t instanceof int[]) {
				str += arrayToString((int[]) t) + ", ";
			} else if (t instanceof long[]) {
				str += arrayToString((long[]) t) + ", ";
			} else if (t instanceof float[]) {
				str += arrayToString((float[]) t) + ", ";
			} else if (t instanceof double[]) {
				str += arrayToString((double[]) t) + ", ";
			} else if (t instanceof char[]) {
				str += arrayToString((char[]) t) + ", ";
			} else str += t.toString() + ", ";
		return str.substring(0, str.length() - 2) + "]";
	}

	public static String arrayToString(byte[] ary) {
		String str = "[";
		for (int i = 0; i < ary.length; ++i) {
			str += ary[i] + "";
			if (i != ary.length - 1) {
				str += ", ";
			}
		}
		return str + "]";
	}

	public static String arrayToString(short[] ary) {
		String str = "[";
		for (int i = 0; i < ary.length; ++i) {
			str += ary[i] + "";
			if (i != ary.length - 1) {
				str += ", ";
			}
		}
		return str + "]";
	}

	public static String arrayToString(int[] ary) {
		String str = "[";
		for (int i = 0; i < ary.length; ++i) {
			str += ary[i] + "";
			if (i != ary.length - 1) {
				str += ", ";
			}
		}
		return str + "]";
	}

	public static String arrayToString(long[] ary) {
		String str = "[";
		for (int i = 0; i < ary.length; ++i) {
			str += ary[i] + "";
			if (i != ary.length - 1) {
				str += ", ";
			}
		}
		return str + "]";
	}

	public static String arrayToString(float[] ary) {
		String str = "[";
		for (int i = 0; i < ary.length; ++i) {
			str += ary[i] + "";
			if (i != ary.length - 1) {
				str += ", ";
			}
		}
		return str + "]";
	}

	public static String arrayToString(double[] ary) {
		String str = "[";
		for (int i = 0; i < ary.length; ++i) {
			str += ary[i] + "";
			if (i != ary.length - 1) {
				str += ", ";
			}
		}
		return str + "]";
	}

	public static String arrayToString(char[] ary) {
		return new String(ary);
	}

	// These print funcitons use the above toString definitions to print to system.out.
	public static <T> void printArray(Collection<T> ary) {
		System.out.println(arrayToString(ary));
	}

	public static <T> void printArray(T[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(byte[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(short[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(int[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(long[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(float[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(double[] ary) {
		System.out.println(arrayToString(ary));
	}

	public static void printArray(char[] ary) {
		System.out.println(arrayToString(ary));
	}

	// These swap functions swap any two elements in any ordered list.
	public static <T> void swap(List<T> ary, int a, int b) {
		T temp = ary.get(a);
		ary.set(a, ary.get(b));
		ary.set(b, temp);
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

	public static <T> List<T> createList(T[] ary) {
		List<T> lst = new ArrayList<T>();
		for (T t : ary) {
			lst.add(t);
		}
		return lst;
	}

	public static List<Byte> createList(byte[] ary) {
		List<Byte> lst = new ArrayList<Byte>();
		for (Byte x : ary) {
			lst.add(x);
		}
		return lst;
	}

	public static List<Short> createList(short[] ary) {
		List<Short> lst = new ArrayList<Short>();
		for (Short x : ary) {
			lst.add(x);
		}
		return lst;
	}

	public static List<Integer> createList(int[] ary) {
		List<Integer> lst = new ArrayList<Integer>();
		for (Integer x : ary) {
			lst.add(x);
		}
		return lst;
	}

	public static List<Long> createList(long[] ary) {
		List<Long> lst = new ArrayList<Long>();
		for (Long x : ary) {
			lst.add(x);
		}
		return lst;
	}

	public static List<Float> createList(float[] ary) {
		List<Float> lst = new ArrayList<Float>();
		for (Float x : ary) {
			lst.add(x);
		}
		return lst;
	}

	public static List<Double> createList(double[] ary) {
		List<Double> lst = new ArrayList<Double>();
		for (Double x : ary) {
			lst.add(x);
		}
		return lst;
	}

	public static List<Character> createList(char[] ary) {
		List<Character> lst = new ArrayList<Character>();
		for (Character x : ary) {
			lst.add(x);
		}
		return lst;
	}
}
