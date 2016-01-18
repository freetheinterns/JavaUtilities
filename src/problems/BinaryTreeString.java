package problems;

import java.util.LinkedList;
import java.util.Queue;

import util.ArrayUtils;

// This class holds the root of a binary tree with one char in each node.
public class BinaryTreeString {
	// This helper class holds the char value of a node and its left and right children.
	public class Node {
		public Node right, left;
		public char value;

		public Node(char c) {
			value = c;
		}

		public String toString() {
			return "" + value;
		}

		// This recursive search function checks the first char in the String against its stored char, and recursively
		// checks its children until the tree bottoms out, the chars don't match, or the full string is found.
		public boolean contains(String searchString) {
			if (searchString.length() == 0) return true;
			char c = searchString.charAt(0);
			searchString = searchString.substring(1);
			
			// If this is the last character to check, return answer.
			if (searchString.length() == 0) return value == c;

			boolean L = false, R = false;
			if (left != null) L = left.contains(searchString);
			if (right != null) R = right.contains(searchString);

			return value == c && (L || R);
		}
	}

	public Node root;

	public BinaryTreeString() {}

	// This constructor builds a Binary Tree from a string from left to right.
	public BinaryTreeString(String nodes) {
		if (nodes.length() == 0) return;
		
		root = new Node(nodes.charAt(0));
		nodes = nodes.substring(1);
		Queue<Node> Q = new LinkedList<Node>();
		Node current = root;
		for (char c : nodes.toCharArray()) {
			Node temp = new Node(c);
			if (current.left == null) {
				current.left = temp;
			} else if (current.right == null)
				current.right = temp;
			else {
				current = Q.poll();
				current.left = temp;
			}
			Q.add(temp);
		}
	}

	public boolean contains(String searchString) {
		return root.contains(searchString);
	}

	// This function flattens the tree layer by layer and returns a multi line string representing the Node structure.
	public String toString() {
		String printString = "";
		Queue<Node> Q = new LinkedList<Node>(), T = new LinkedList<Node>();
		Q.add(root);
		while (!Q.isEmpty()) {
			printString += ArrayUtils.collectionToString(Q) + "\n";
			for (Node n : Q) {
				if (n.left != null) T.add(n.left);
				if (n.right != null) T.add(n.right);
			}
			Q = T;
			T = new LinkedList<Node>();
		}
		return printString;
	}

	public static void main(String[] args) {
		BinaryTreeString B = new BinaryTreeString("cactefgs");
		System.out.println(B);
		System.out.println(B.contains("cat"));
		System.out.println(B.contains("cats"));
		System.out.println(B.contains("catz"));
	}
}
