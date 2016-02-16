package sorting;

import java.util.Collections;
import java.util.LinkedList;

// This class is utterly useless
public class ThreadedQuickSorter implements Runnable {
	private int[] ary;
	private int start, end;

	public ThreadedQuickSorter(int[] ary, int start, int end) {
		this.ary = ary;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		if (start < end) {
			int pivot = QuickSorter.partition(ary, start, end);
			Thread left = null, right = null;
			if (start < pivot - 1) {
				left = new Thread(new ThreadedQuickSorter(ary, start, pivot - 1));
				left.start();
			}
			if (pivot + 1 < end) {
				right = new Thread(new ThreadedQuickSorter(ary, pivot + 1, end));
				right.start();
			}
			try {
				if (left != null) left.join();
				if (right != null) right.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void qsort(int[] ary) {
		ThreadedQuickSorter sorter = new ThreadedQuickSorter(ary, 0, ary.length - 1);
		sorter.run();
	}

	public static void main(String[] args) {
		class TimeTester implements Runnable {
			public long[] times;
			public int timeslot;
			public boolean option;
			public int[] ary;

			public TimeTester(long[] times, int timeslot, boolean option) {
				this.times = times;
				this.timeslot = timeslot;
				this.option = option;
				ary = randAry(1000);
			}

			public int[] randAry(int size) {
				LinkedList<Integer> L = new LinkedList<Integer>();
				for (int i = 0; i < size; ++i) {
					L.add(i);
				}
				Collections.shuffle(L);
				int[] ary = new int[size];
				int loc = 0;
				for (Integer val : L) {
					ary[loc++] = val;
				}
				return ary;
			}

			@Override
			public void run() {
				if (option) {
					long starttime = System.nanoTime();
					ThreadedQuickSorter.qsort(ary);
					times[timeslot] = System.nanoTime() - starttime;
				} else {
					long starttime = System.nanoTime();
					QuickSorter.qsort(ary);
					times[timeslot] = System.nanoTime() - starttime;
				}
			}

		}
		int size = 100;
		long[][] alltimes = new long[2][size];
		for (int i = 0; i < size; ++i) {
			TimeTester test = new TimeTester(alltimes[0], i, true);
			test.run();
		}
		for (int i = 0; i < size; ++i) {
			TimeTester test = new TimeTester(alltimes[1], i, false);
			test.run();
		}
		long A = 0, B = 0;
		for (int i = 0; i < size; ++i) {
			A += alltimes[0][i];
			B += alltimes[1][i];
		}
		A /= size;
		B /= size;
		System.out.println("Average Threaded Time: " + A);
		System.out.println("Average Linear Time: " + B);
	}

}
