/*
Given a string and an array of words, find the first word that can be created by using letters of the string. For eg. if string is "balloons" and words are ["son", "ball", "friends"] return "son" as it is the first word that can be constructed using letters from "balloons".
*/

import java.util.*;

public class FirstWord {

	public String find(String[] words, String str) {
		final Map<Character, Integer> freq = getFrequency(str);

		outer : for (String word : words) {
			final Map<Character, Integer> currFreq = getFrequency(word);
			for (Character ch : currFreq.keySet()) {
				int originalCount = freq.getOrDefault(ch, 0);
				int count = currFreq.get(ch);
				if (count > originalCount) continue outer;
			}
			return word;
		}

		return null;
	}

	private Map<Character, Integer> getFrequency(String str) {
		Map<Character, Integer> freq = new HashMap<>();
		for (int i=0, n = str.length(); i < n ; i++) {
			freq.compute(str.charAt(i), (c, f) -> f == null ? 1 : 1 + f);
		}
		return freq;
	}

	public static void main(String... args) {
		final String[] words = {"sond", "friends","son", "ball"};
		final String str = "balloons";

		FirstWord fw = new FirstWord();
		System.out.println(fw.find(words, str));
	}
}