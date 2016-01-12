package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import util.ArrayUtils;

public class MyHashTable<T> {
	// Static prime number used in hashing. Must be < sqrt(Integer.MAX_INT).
	private static final int LARGE_PRIME = 40009;
	// Random numbers are used to generate a valid hash.
	private static final Random rand = new Random(System.currentTimeMillis());
	private final int Ahash = rand.nextInt(LARGE_PRIME);
	private final int Bhash = rand.nextInt(LARGE_PRIME);

	// The size of the object[] used in hashtable
	private int capacity;
	// The acceptable ratio of elements to hash slots.
	private float acceptable_ratio = 3;
	// The current number of elements in hashtable
	private int size = 0;
	// Underlying data structure.
	private Object[] storage;

	public MyHashTable() {
		capacity = 100;
		storage = new Object[capacity];
	}

	public MyHashTable(int size) {
		capacity = size;
		storage = new Object[capacity];
	}

	// Adds an element to the hashtable.
	public void add(T element) {
		// If we have reached the limit of our acceptable ratio, expand the capacity.
		if (size + 1 > capacity * acceptable_ratio) {
			resize(size * 2);
		}
		++size;
		int idx = hash(element);
		if (storage[idx] == null) {
			storage[idx] = element;
		} else if (storage[idx] instanceof LinkedList<?>) {
			@SuppressWarnings("unchecked")
			LinkedList<T> temp = ((LinkedList<T>) storage[idx]);
			temp.add(element);
		} else {
			LinkedList<T> lst = new LinkedList<T>();
			@SuppressWarnings("unchecked")
			T temp = (T) storage[idx];
			lst.add(temp);
			lst.add(element);
			storage[idx] = lst;
		}
	}

	// Removes (ONLY) the first instance of element found in the hashtable.
	public void remove(T element) {
		int idx = hash(element);
		if (storage[idx] == null) {
		} else if (storage[idx] instanceof LinkedList<?>) {
			@SuppressWarnings("unchecked")
			LinkedList<T> temp = ((LinkedList<T>) storage[idx]);
			temp.remove(element);

			// If there is only one element left in the list, deconstruct the
			// list;
			if (temp.size() == 1) {
				storage[idx] = temp.getFirst();
			} else if (temp.size() == 0)
				storage[idx] = null;
			--size;
		} else {
			storage[idx] = null;
			--size;
		}
	}

	// Returns true if an instance of element is stored in the hashtable.
	public boolean contains(T element) {
		int idx = hash(element);
		if (storage[idx] == null) {
			return false;
		} else if (storage[idx] instanceof LinkedList<?>) {
			@SuppressWarnings("unchecked")
			LinkedList<T> temp = ((LinkedList<T>) storage[idx]);
			return temp.contains(element);
		} else {
			return storage[idx] == element;
		}
	}

	public int size() {
		return size;
	}

	// Returns an arraylist of every element stored in the hashtable.
	public ArrayList<T> toArray() {
		ArrayList<T> out = new ArrayList<T>(size);
		for (Object x : storage) {
			if (x == null)
				continue;
			else if (x instanceof LinkedList<?>) {
				@SuppressWarnings("unchecked")
				LinkedList<T> temp = ((LinkedList<T>) x);
				for (T obj : temp)
					out.add(obj);
			} else {
				@SuppressWarnings("unchecked")
				T temp = (T) x;
				out.add(temp);
			}
		}
		return out;
	}

	// Resizes the Object array backbone and reinserts all of the old elements.
	public void resize(int newsize) {
		assert (newsize > 0);
		ArrayList<T> data = toArray();
		capacity = newsize;
		storage = new Object[capacity];
		size = 0;
		for (T obj : data)
			add(obj);
	}

	public void setResizeRatio(float ratio) {
		assert (ratio > .4);
		acceptable_ratio = ratio;
		if (size > capacity * ratio)
			resize((int) (size * 2));
	}

	// Provides a consistent randomized uniform hash for an item.
	// Always returns a hash value in the range [0 : capacity].
	private int hash(T item) {
		int hc = item.hashCode() % LARGE_PRIME;
		return ((Ahash * hc + Bhash) % LARGE_PRIME) % capacity;
	}

	@Override
	public String toString() {
		return ArrayUtils.arrayToString(toArray());
	}

	// Prints the structure of the hashtable as is
	public void prettyPrint() {
		for (Object x : storage) {
			if (x == null)
				System.out.println("[]");
			else if (x instanceof LinkedList<?>) {
				@SuppressWarnings("unchecked")
				LinkedList<T> temp = ((LinkedList<T>) x);
				ArrayUtils.printArray(temp);
			} else {
				@SuppressWarnings("unchecked")
				T temp = (T) x;
				System.out.println("[" + temp + "]");
			}
		}
	}

	// Run test cases
	public static void main(String[] args) {
		MyHashTable<String> test = new MyHashTable<String>(10);
		test.add("Cat");
		test.add("cat");
		test.add("Cat");
		test.add("DOG");
		test.add("dog");
		test.add("Dogg");
		test.add("Wolf");
		// System.out.println(test);
		test.prettyPrint();
	}
}
