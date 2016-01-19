package problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

// Assumptions:
// 1: Reading arbitrarily large file
// 2: 1 GB working memory (Storage only)
// 3: 1 byte per character
public class LargeFileSorting {
	// Data cap is 50 MB for test. Can be increased to 900 MB or 900000000 later
	public static final int DATA_CAP = 50000000;
	// The currently cached list of words read
	public static ArrayList<String> wordlist;
	// The set of files containing sorted subsets of the larger file.
	public static ArrayList<File> sortedFiles;

	// Reads through an arbitrarily big text file while storing only the top 1000 strings found (alphabetically)
	// Basic Approach:
	// 1: Read large files in batches
	// 2: Sort batches and store as files
	// 3: Mergesort files into new file.
	public static void orderArbitrarilyLargeFile(File file) {
		wordlist = new ArrayList<String>();
		sortedFiles = new ArrayList<File>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			int byte_count = 0;
			while (line != null) {
				if (line.isEmpty()) { // Skip empty lines
					line = br.readLine();
					continue;
				}
				wordlist.add(line);
				byte_count += line.length(); // track byte count
				line = br.readLine();

				// When buffer limit reached, sort words and write to temp file
				if (byte_count >= DATA_CAP) {
					writeBSTtoFile();
					byte_count = 0;
					wordlist.clear(); // Clear buffer
				}
			}
			writeBSTtoFile(); // Write remaining words to file
			wordlist.clear();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Merge temp files into one large file.
		mergeOutputFiles();
	}

	// Merge all temp files into one file.
	public static void mergeOutputFiles() {
		PrintWriter writer;
		try {
			// Make static buffers for the readers and current words.
			String[] nextWords = new String[sortedFiles.size()];
			BufferedReader[] buffers = new BufferedReader[sortedFiles.size()];
			File[] allfiles = new File[sortedFiles.size()];
			allfiles = sortedFiles.toArray(allfiles);
			for (int i = 0; i < allfiles.length; ++i) {
				buffers[i] = new BufferedReader(new FileReader(allfiles[i]));
				nextWords[i] = buffers[i].readLine();
			}
			writer = new PrintWriter(new File("src/problems/output.txt"));

			// Identify the file which has the next word to insert.
			int index = smallestNonNullIndex(nextWords);
			while (index >= 0) { // repeat until all buffers are empty
				writer.println(nextWords[index]);
				nextWords[index] = buffers[index].readLine(); // increment specific buffer.
				index = smallestNonNullIndex(nextWords); // Identify next word
			}

			writer.close();
			for (int i = 0; i < buffers.length; ++i) {
				buffers[i].close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Returns the index of the (alphabetically) first word in an array, or -1 if all indicies are null.
	public static int smallestNonNullIndex(String[] words) {
		String small = words[0];
		int index = 0;
		while (small == null) {
			if (index + 1 >= words.length) {
				return -1;
			}
			small = words[++index];
		}
		for (int i = index + 1; i < words.length; ++i) {
			if (words[i] == null) continue;
			if (words[i].compareTo(small) < 0) {
				small = words[i];
				index = i;
			}
		}
		return index;
	}

	// Writes a temp file from the current list of words.
	public static void writeBSTtoFile() {
		// create file
		File file = new File("src/problems/temp/tempfile_" + sortedFiles.size() + ".txt");
		sortedFiles.add(file);
		PrintWriter writer;
		try {
			// ensure file and directory exist
			file.getParentFile().mkdirs();
			file.createNewFile();
			writer = new PrintWriter(file);
			Collections.sort(wordlist); // sort before writing
			for (String line : wordlist) {
				writer.println(line);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// orderArbitrarilyLargeFile(new File("src/problems/wordsEn.txt"));
		orderArbitrarilyLargeFile(new File("src/problems/LargeWordFile.txt"));
	}
}
