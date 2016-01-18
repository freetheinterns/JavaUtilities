package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import util.ArrayUtils;

public class MyHashTable<K, V> {
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

	private class KVPair {
		public K key;
		public V value;

		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "(" + key + ", " + value + ")";
		}
	}

	public MyHashTable() {
		capacity = 100;
		storage = new Object[capacity];
	}

	public MyHashTable(int size) {
		capacity = size;
		storage = new Object[capacity];
	}

	// Adds an element to the hashtable.
	public void add(K key, V value) {
		// If we have reached the limit of our acceptable ratio, expand the capacity.
		if (size + 1 > capacity * acceptable_ratio) {
			resize(size * 2);
		}
		++size;
		int idx = hash(key);
		KVPair P = new KVPair(key, value);
		if (storage[idx] == null) {
			storage[idx] = P;
		} else if (storage[idx] instanceof LinkedList<?>) {
			@SuppressWarnings("unchecked")
			LinkedList<KVPair> temp = ((LinkedList<KVPair>) storage[idx]);
			temp.add(P);
		} else {
			LinkedList<KVPair> lst = new LinkedList<KVPair>();
			@SuppressWarnings("unchecked")
			KVPair temp = (KVPair) storage[idx];
			lst.add(temp);
			lst.add(P);
			storage[idx] = lst;
		}
	}

	// Removes (ONLY) the first instance of element found in the hashtable.
	public void remove(K key) {
		int idx = hash(key);
		if (storage[idx] == null) {
		} else if (storage[idx] instanceof LinkedList<?>) {
			@SuppressWarnings("unchecked")
			LinkedList<KVPair> temp = ((LinkedList<KVPair>) storage[idx]);
			KVPair rm = null;
			for (KVPair P : temp)
				if (P.key == key)
					rm = P;
			if (rm != null)
				temp.remove(rm);

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
	public boolean contains(K element) {
		int idx = hash(element);
		if (storage[idx] == null) {
			return false;
		} else if (storage[idx] instanceof LinkedList<?>) {
			@SuppressWarnings("unchecked")
			LinkedList<KVPair> temp = ((LinkedList<KVPair>) storage[idx]);
			for (KVPair P : temp)
				if (P.key == element)
					return true;
			return false;
		} else {
			@SuppressWarnings("unchecked")
			KVPair temp = ((KVPair) storage[idx]);
			return temp.key == element;
		}
	}

	public int size() {
		return size;
	}

	// Returns an arraylist of every element stored in the hashtable.
	public ArrayList<KVPair> toArray() {
		ArrayList<KVPair> out = new ArrayList<KVPair>(size);
		for (Object x : storage) {
			if (x == null)
				continue;
			else if (x instanceof LinkedList<?>) {
				@SuppressWarnings("unchecked")
				LinkedList<KVPair> temp = ((LinkedList<KVPair>) x);
				for (KVPair obj : temp)
					out.add(obj);
			} else {
				@SuppressWarnings("unchecked")
				KVPair temp = (KVPair) x;
				out.add(temp);
			}
		}
		return out;
	}

	// Resizes the Object array backbone and reinserts all of the old elements.
	public void resize(int newsize) {
		assert (newsize > 0);
		ArrayList<KVPair> data = toArray();
		capacity = newsize;
		storage = new Object[capacity];
		size = 0;
		for (KVPair obj : data)
			add(obj.key, obj.value);
	}

	public void setResizeRatio(float ratio) {
		assert (ratio > .4);
		acceptable_ratio = ratio;
		if (size > capacity * ratio)
			resize((int) (size * 2));
	}

	// Provides a consistent randomized uniform hash for an item.
	// Always returns a hash value in the range [0 : capacity].
	private int hash(K item) {
		int hc = item.hashCode() % LARGE_PRIME;
		return ((Ahash * hc + Bhash) % LARGE_PRIME) % capacity;
	}

	@Override
	public String toString() {
		return ArrayUtils.collectionToString(toArray());
	}

	// Prints the structure of the hashtable as is
	public void prettyPrint() {
		for (Object x : storage) {
			if (x == null)
				System.out.println("[]");
			else if (x instanceof LinkedList<?>) {
				@SuppressWarnings("unchecked")
				LinkedList<KVPair> temp = ((LinkedList<KVPair>) x);
				ArrayUtils.printCollection(temp);
			} else {
				@SuppressWarnings("unchecked")
				KVPair temp = (KVPair) x;
				System.out.println("[" + temp + "]");
			}
		}
	}

	// Run test cases
	public static void main(String[] args) {
		MyHashTable<String, Integer> test = new MyHashTable<String, Integer>(10);
		test.add("Cat", 1);
		test.add("cat", 3);
		test.add("Cat", 2);
		test.add("DOG", 1);
		test.add("dog", 1);
		test.add("Dogg", 1);
		test.add("Wolf", 1);
		// System.out.println(test);
		test.prettyPrint();
	}
}
