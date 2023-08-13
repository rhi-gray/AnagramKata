package space.banach.Anagrams;

import java.util.*;
import java.util.stream.Collectors;

public class Util {
	public static String sortedLetters(String word) {
		List<Character> chars = word.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
		Collections.sort(chars);
		String[] charStrings = new String[chars.size()];
		for (int i = 0; i < chars.size(); i++) {
			charStrings[i] = chars.get(i).toString();
		}
		return String.join("", charStrings);
	}
	
	public static List<String> except(String exclusion, List<String> items) {
		return items.stream()
				.filter(x -> !x.equals(exclusion))
				.toList();
	}
}
