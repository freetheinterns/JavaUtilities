package datastructures;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

// This class in an implementation of a classic Binary Search Tree.
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>, Collection<T> {
	// This class provides the necessary structure for nodes in a BST.
	public static class Node<K> {
		public Node<K> parent, left, right;
		public K value;

		public Node(K value) {
			this.value = value;
		}

		public String toString() {
			return "" + value;
		}
	}

	// This class implements a classic iterator for this BST, while also implementing reverse iteration.
	public static class BSTIterator<K extends Comparable<K>> implements Iterator<K> {
		// An instance of the tree to iterate is necessary for accessing generic private methods without casting.
		public BinarySearchTree<K> tree;
		public Node<K> current;

		public BSTIterator(BinarySearchTree<K> bst) {
			tree = bst;
		}

		@Override
		// Must check tree maximum. O(log N)
		public boolean hasNext() {
			if (current == null) {
				return tree.root != null;
			}
			if (current == tree.getMaximum()) return false;
			return true;
		}

		@Override
		public K next() {
			if (current == null) {
				if (tree.root == null) {
					throw new NoSuchElementException();
				} else {
					current = tree.getMinimum();
					return current.value;
				}
			}
			Node<K> nextNode = tree.getSuccessor(current);
			if (nextNode == null) throw new NoSuchElementException();
			current = nextNode;
			return current.value;
		}

		public K prev() {
			if (current == null) throw new NoSuchElementException();
			Node<K> previousNode = tree.getPredecessor(current);
			if (previousNode == null) throw new NoSuchElementException();
			current = previousNode;
			return current.value;
		}
	}

	private Node<T> root;
	private int size;

	public BinarySearchTree() {}

	public BinarySearchTree(T item) {
		add(item);
	}

	public BinarySearchTree(Collection<T> items) {
		addAll(items);
	}

	// Binary search algorithm (iterative). O(log N)
	public Node<T> search(T item) {
		Node<T> current = root;
		while (current != null && item != current.value) {
			if (item.compareTo(current.value) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return current;
	}

	@Override
	@SuppressWarnings("unchecked")
	// Assumes object passed is of type T.
	// Returns true if a search for a node with such a value is successful.
	public boolean contains(Object o) {
		T itemtoremove = (T) o;
		Node<T> nodetoremove = search(itemtoremove);
		return nodetoremove == null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) return false;
		}
		return true;
	}

	@Override
	// Similar to search algorithm with minimal additional overhead for corner cases. O(log N)
	public boolean add(T item) {
		Node<T> newNode = new Node<T>(item), newLocationProbe = root, previousNode = null;
		while (newLocationProbe != null) {
			previousNode = newLocationProbe;
			if (item.compareTo(newLocationProbe.value) < 0) {
				newLocationProbe = newLocationProbe.left;
			} else {
				newLocationProbe = newLocationProbe.right;
			}
		}
		newNode.parent = previousNode;
		if (previousNode == null) {
			root = newNode;
		} else if (item.compareTo(previousNode.value) < 0) {
			previousNode.left = newNode;
		} else {
			previousNode.right = newNode;
		}
		++size;
		return true;
	}

	@Override
	// Simple collection addition. O(M log N) for collection of size M
	public boolean addAll(Collection<? extends T> itemsToAdd) {
		for (T item : itemsToAdd) {
			add(item);
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	// Assumes you are trying to remove something of type T.
	// Returns true if that item was found in the BST and removes it.
	// Returns false if no node was found with such value.
	public boolean remove(Object o) {
		T itemtoremove = (T) o;
		Node<T> nodetoremove = search(itemtoremove);
		if (nodetoremove == null) return false;
		delete(nodetoremove);
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean allremoved = true;
		for (Object o : c) {
			if (!remove(o)) allremoved = false;
		}
		return allremoved;
	}

	// Removes node from tree. Runtime varies depending on case. Simple cases O(1).
	// Complex case (node has two children) requires a search for replacement. O(log N)
	public void delete(Node<T> deadNode) {
		if (deadNode.left == null) {
			transplant(deadNode, deadNode.right);
		} else if (deadNode.right == null) {
			transplant(deadNode, deadNode.left);
		} else {
			Node<T> replacementNode = treeMinimum(deadNode.right);
			if (replacementNode.parent != deadNode) {
				transplant(replacementNode, replacementNode.right);
				replacementNode.right = deadNode.right;
				replacementNode.right.parent = replacementNode;
			}
			transplant(deadNode, replacementNode);
			replacementNode.left = deadNode.left;
			replacementNode.left.parent = replacementNode;
		}
		--size;
	}

	// Another corner case of search. O(log N)
	public Node<T> getSuccessor(Node<T> current) {
		if (current.right != null) return treeMinimum(current.right);

		Node<T> previousNode = current;
		current = current.parent;
		while (current != null && current.right == previousNode) {
			previousNode = current;
			current = current.parent;
		}
		return current;
	}

	// Mirror of getSuccessor. O(log N)
	public Node<T> getPredecessor(Node<T> current) {
		if (current.left != null) return treeMaximum(current.left);

		Node<T> previousNode = current;
		current = current.parent;
		while (current != null && current.left == previousNode) {
			previousNode = current;
			current = current.parent;
		}
		return current;
	}

	// Min and Max are corner cases of search. O(log N)
	public Node<T> getMinimum() {
		return treeMinimum(root);
	}

	public Node<T> getMaximum() {
		return treeMaximum(root);
	}

	private Node<T> treeMinimum(Node<T> n) {
		while (n != null && n.left != null) {
			n = n.left;
		}
		return n;
	}

	private Node<T> treeMaximum(Node<T> n) {
		while (n != null && n.right != null) {
			n = n.right;
		}
		return n;
	}

	private static <T> String nodeWalk(Node<T> n) {
		if (n == null) return "";
		return nodeWalk(n.left) + n + nodeWalk(n.right);
	}

	// Transplant operation swaps two nodes within the tree. O(1)
	// Is not responsible for maintaining node integrity.
	private void transplant(Node<T> originalNode, Node<T> replacementNode) {
		if (originalNode.parent == null) {
			root = replacementNode;
		} else if (originalNode == originalNode.parent.left) {
			originalNode.parent.left = replacementNode;
		} else {
			originalNode.parent.right = replacementNode;
		}
		if (replacementNode != null) {
			replacementNode.parent = originalNode.parent;
		}
	}

	@Override
	// Print method must touch all elements. O(N)
	public String toString() {
		return "[" + nodeWalk(root) + "]";
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
		System.gc();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	// Creates Iterator instance. O(1)
	public Iterator<T> iterator() {
		return new BSTIterator<T>(this);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Node<T> currentNode = getMinimum();
		boolean anyremoved = false;
		while (currentNode != null) {
			Node<T> nextNode = getSuccessor(currentNode);
			if (c.contains(currentNode.value)) {
				delete(currentNode);
				anyremoved = true;
			}
			currentNode = nextNode;
		}
		return anyremoved;
	}

	@Override
	public Object[] toArray() {
		Object[] output = new Object[size];
		int index = 0;
		for (T item : this) {
			output[index++] = item;
		}
		return output;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K> K[] toArray(K[] ary) {
		int index = 0;
		for (T item : this) {
			if (index >= ary.length) return ary;
			ary[index++] = (K) item;
		}
		return ary;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(4);
		tree.addAll(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7}));
		System.out.println(tree);
		Node<Integer> four = tree.search(4);
		Node<Integer> prev = tree.getPredecessor(four), succ = tree.getSuccessor(four);
		tree.delete(prev);
		System.out.println("Removed node " + prev);
		tree.delete(succ);
		System.out.println("Removed node " + succ);
		tree.delete(four);
		System.out.println("Removed node " + four);
		System.out.println(tree);
		System.out.println("Tree Maximum:" + tree.getMaximum());
		System.out.println("Tree Minimum:" + tree.getMinimum());
		String iteratortest = "Foreach iterator test: [";
		for (Integer n : tree) {
			iteratortest += n;
		}
		System.out.println(iteratortest + "]");
	}
}
