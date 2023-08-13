package space.banach.Anagrams;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordList {
	public ArrayList<String> validWords;
	public HashMap<String, ArrayList<String>> anagrams;

	public WordList(String sourceFile) {
		validWords = new ArrayList<String>();
		anagrams = new HashMap<String, ArrayList<String>>();
		this.loadWords(sourceFile);
	}
	
	private void loadWords(String source) {
		BufferedReader buf = null;
		try {
			buf = new BufferedReader(new FileReader(source));

			// Read lines, adding each new word to the list of valid words, and caching any anagrams.
			for (String line = buf.readLine(); line != null; line = buf.readLine()) {
				String word = line.strip().toLowerCase();
				String sorted = Util.sortedLetters(word);

				validWords.add(word);

				if (anagrams.containsKey(sorted)) {
					// Add it to the list of anagrams for this letter sequence.
					ArrayList<String> lst = anagrams.get(sorted);
					lst.add(word);
				}
				else {
					// New key
					anagrams.put(sorted, 
							new ArrayList<String>(Arrays.asList(word)));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + source + " not found!");
			return;
		} catch (IOException e) {
			System.out.println("Error while reading " + source);
			e.printStackTrace();
		}
		finally {
			try {
				buf.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Return the known anagrams for a word, or just that word itself if there are none. 
	public ArrayList<String> getAnagrams(String word) {
		String key = Util.sortedLetters(word.toLowerCase());
		return this.anagrams.getOrDefault(key, new ArrayList<String>(Arrays.asList(word)));
	}
}