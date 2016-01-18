package problems;

import java.util.LinkedList;
import java.util.Queue;

import util.ArrayUtils;

public class QueuedQueues {
	// This function takes a nested generic Queue and returns a flattened Queue in preserved order.
	public static <T> Queue<T> flatten(Queue<Queue<T>> data) {
		Queue<T> Q = new LinkedList<T>();
		while (!data.isEmpty()) {
			Queue<T> temp = data.poll();
			while (!temp.isEmpty()) {
				Q.add(temp.poll());
			}
		}
		return Q;
	}

	public static void main(String[] args) {
		Queue<String> A = new LinkedList<String>();
		Queue<String> B = new LinkedList<String>();
		Queue<String> C = new LinkedList<String>();
		Queue<Queue<String>> X = new LinkedList<Queue<String>>();

		A.add("Cat");
		A.add("Dog");
		A.add("Wolf");

		B.add("Apple");
		B.add("Banana");
		B.add("Pear");

		C.add("Bird");
		C.add("Car");
		C.add("Plane");

		X.add(A);
		X.add(B);
		X.add(C);

		ArrayUtils.printCollection(X);
		ArrayUtils.printCollection(flatten(X));
	}
}
