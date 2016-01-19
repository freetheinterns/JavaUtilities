package problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import datastructures.BinarySearchTree;

public class TopN {
	public static BinarySearchTree<String> bst;

	// Reads through an arbitrarily big text file while storing only the top 1000 strings found (alphabetically)
	public static String[] readLargeFileWithConstraints(File file) {
		bst = new BinarySearchTree<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				if (line.isEmpty()) {
					line = br.readLine();
					continue;
				}
				// Binary search trees are pre ordered, add time is O(log N), where N <= 1000
				bst.add(line);
				if (bst.size() > 1000) {
					// Also O(log N)
					bst.delete(bst.getMaximum());
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Provide the output array for the BST to dump into.
		String[] output = new String[bst.size()];
		return bst.toArray(output);
	}

	public static void shuffleFileContents(File file) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				if (line.isEmpty()) {
					line = br.readLine();
					continue;
				}
				lines.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.shuffle(lines);
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			for (String line : lines) {
				writer.println(line);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		shuffleFileContents(new File("src/problems/wordsEn.txt"));
		String[] words = readLargeFileWithConstraints(new File("src/problems/wordsEn.txt"));
		System.out.println(Arrays.toString(words));
	}
}
