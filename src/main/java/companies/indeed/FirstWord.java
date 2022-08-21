/*
Given a string and an array of words, find the first word that can be created by using letters of the string. For eg. if string is "balloons" and words are ["son", "ball", "friends"] return "son" as it is the first word that can be constructed using letters from "balloons".
*/

import java.util.*;

public class FirstWord {

	public String find(String[] words, String str) {
		Map<Character, Integer> freq = new HashMap<>();
		for (int i=0, n = str.length(); i < n ; i++) {
			freq.compute(str.charAt(i), (c, f) -> f == null ? 1 : 1 + f);
		}

		outer : for (String word : words) {
			final Map<Character, Integer> currFreq = new HashMap<>();
			for (Character ch : word.toCharArray()) {
				int originalCount = freq.getOrDefault(ch, 0);
				int count = currFreq.compute(ch, (k, v) -> v == null ? 1 : 1 + v);
				if (count > originalCount) continue outer;
			}
			return word;
		}

		return null;
	}

	public static void main(String... args) {
		final String[] words = {"sond", "friends","son", "ball"};
		final String str = "balloons";

		FirstWord fw = new FirstWord();
		System.out.println(fw.find(words, str));
	}
}