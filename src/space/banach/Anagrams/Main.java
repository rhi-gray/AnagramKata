package space.banach.Anagrams;

import java.util.ArrayList;

public class Main {
	final static String DICTFILE = "/usr/share/dict/words";
	public static void main(String[] args) {
		// For each argument, show a list of anagrams for that argument.
		WordList words = new WordList(DICTFILE);
		System.out.printf("Loaded %d words from %s\n\n", words.validWords.size(), DICTFILE);

		for (String word: args) {
			word = word.toLowerCase();

			// Print anagrams, if any exist.
			ArrayList<String> anagrams = words.getAnagrams(word);
			if (anagrams.size() == 1) {
				System.out.printf("No anagrams found for \"%s\"\n", word);
			}
			else {
				System.out.printf("Anagrams for \"%s\":\t ", word);
				System.out.println(String.join(", ", Util.except(word, anagrams)));
			}
		}
	}
}
