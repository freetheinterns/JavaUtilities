package datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.BinarySearchTree.Node;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<Node<T>> {
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

	public static class BSTIterator<K extends Comparable<K>> implements Iterator<Node<K>> {
		public BinarySearchTree<K> tree;
		public Node<K> current;

		public BSTIterator(Node<K> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			if (current == null) return false;
			if (current == tree.getMaximum()) return false;
			return true;
		}

		@Override
		public Node<K> next() {
			if (current == null) throw new NoSuchElementException();
			Node<K> nextNode = tree.getSuccessor(current);
			if (nextNode == null) throw new NoSuchElementException();
			current = nextNode;
			return current;
		}

		public Node<K> prev() {
			if (current == null) throw new NoSuchElementException();
			Node<K> previousNode = tree.getPredecessor(current);
			if (previousNode == null) throw new NoSuchElementException();
			current = previousNode;
			return current;
		}
	}

	private Node<T> root;
	
	public BinarySearchTree() {}
	public BinarySearchTree(T item) {
		add(item);
	}
	public BinarySearchTree(Collection<T> items) {
		addAll(items);
	}

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

	public void add(T item) {
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
	}
	
	public void addAll(Collection<T> itemsToAdd) {
		for (T item : itemsToAdd){
			add(item);
		}
	}

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
	}

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

	public Node<T> getMinimum() {
		return treeMinimum(root);
	}

	public Node<T> getMaximum() {
		return treeMaximum(root);
	}

	private Node<T> treeMinimum(Node<T> n) {
		while (n.left != null) {
			n = n.left;
		}
		return n;
	}

	private Node<T> treeMaximum(Node<T> n) {
		while (n.right != null) {
			n = n.right;
		}
		return n;
	}

	private static <T> String nodeWalk(Node<T> n) {
		if (n == null) return "";
		return nodeWalk(n.left) + n + nodeWalk(n.right);
	}

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
	public String toString() {
		return nodeWalk(root);
	}

	@Override
	public Iterator<Node<T>> iterator() {
		return new BSTIterator<T>(getMinimum());
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(4);
	}
}
