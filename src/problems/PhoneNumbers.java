package problems;

import java.util.HashMap;
import java.util.Random;

import util.ArrayUtils;

public class PhoneNumbers {
	// This Map stores all of the phone numbers that have already been parsed, as well as their potential outputs.
	public static HashMap<String, String[]> preParsedPhoneNumbers = new HashMap<String, String[]>();

	// This helper function cleanses a string before starting the recursive parsing process.
	public static String[] parsePhoneNumber(String phoneNumber) {
		// Cleanse phone number of unnecessary characters.
		phoneNumber = removeNonDigits(phoneNumber);

		return parseFirstDigit(phoneNumber);
	}

	// This function takes a phone number as a String and returns all possible corresponding String permutations using a
	// function for mapping digits to possible characters.
	// Maximum recursive depth is no greater than the length of the phone number.
	private static String[] parseFirstDigit(String phoneNumber) {
		// Base case: empty String
		if (phoneNumber.isEmpty()) return new String[] {""};

		// If we have already parsed this string, return the saved result.
		if (preParsedPhoneNumbers.containsKey(phoneNumber)) {
			return preParsedPhoneNumbers.get(phoneNumber);
		}

		// Convert first digit in phone number to possible characters
		String letterOptions = numberToLetter(Character.getNumericValue(phoneNumber.charAt(0)));

		// Get the possible permutations for all numbers after the first one.
		String[] currentStringPermutations = parseFirstDigit(phoneNumber.substring(1));

		// Allocate and build the next list of String permutations from the current list, and the letter options.
		String[] nextStringPermutations = new String[letterOptions.length() * currentStringPermutations.length];
		int index = 0;
		for (String existingPermutation : currentStringPermutations) {
			for (char possibleLetter : letterOptions.toCharArray()) {
				nextStringPermutations[index++] = existingPermutation + possibleLetter;
			}
		}

		// Store result for future use.
		preParsedPhoneNumbers.put(phoneNumber, nextStringPermutations);
		return nextStringPermutations;
	}

	// Removes all non-digit characters from the provided String.
	// Also removes non-mapped digits 0 & 1.
	public static String removeNonDigits(String str) {
		String output = "";
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c) && c != '0' && c != '1') {
				output += c;
			}
		}
		return output;
	}

	// This function generates and parses 1000 randomly generated possible seven digit phone numbers.
	public static void preParseSomePossiblePhoneNumbers() {
		preParseSomePossiblePhoneNumbers(1000);
	}

	public static void preParseSomePossiblePhoneNumbers(int randomPhoneNumberCount) {
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < randomPhoneNumberCount; ++i) {
			parsePhoneNumber(String.format("%07d", rand.nextInt(7999999) + 2000000));
		}
	}

	// WARNING!!!
	// This function requires > 200 GBs of working memory to run...
	// Generates ALL possible north American 7-digit phone numbers. (no area codes)
	public static void preParseAllPossiblePhoneNumbers() {
		for (int i = 2000000; i < 10000000; ++i) {
			parsePhoneNumber("" + i);
		}
	}

	// This function will use the static number to char mapping that is shown on the iphone as well as below:
	// '' = > 0 : '' = > 1 : 'a,b,c' = > 2 : 'd,e,f' = > 3
	// 'g,h,i' = > 4 : 'j,k,l' = > 5 : 'm,n,o' = > 6
	// 'p,q,r,s' = > 7 : 't,u,v' = > 8 : 'w,x,y,z' = > 9
	public static String numberToLetter(int d) {
		if (d <= 1 || d >= 10) {
			throw new IllegalArgumentException();
		}
		if (d == 2) return "abc";
		if (d == 3) return "def";
		if (d == 4) return "ghi";
		if (d == 5) return "jkl";
		if (d == 6) return "mno";
		if (d == 7) return "pqrs";
		if (d == 8) return "tuv";
		return "wxyz";
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		preParseSomePossiblePhoneNumbers(10000);

		long endTime = System.currentTimeMillis();
		System.out.println("Pre-Parsing took " + (endTime - startTime) / 1000.0 + " seconds");

		System.out.println("\nTest Case 1:");
		String[] ans = parsePhoneNumber("253");
		ArrayUtils.printArray(ans);

		System.out.println("\nTest Case 2:");
		String phoneNumber = "348 - 5928";
		String[] a2 = parsePhoneNumber(phoneNumber);
		System.out.println(a2.length + " possible permutations for [" + phoneNumber + "]\n");
	}
}
