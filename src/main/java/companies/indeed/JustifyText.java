/**
 *
 * Coding challenge done with Karat (a 3rd party interviewing company).

Write a function to output fully justified text. The output must be an array of lines, and each line must have length equal to "lineLength" parameter - except if it's just one word. See Examples:

# Example 1 input
text = [ "Some modern typesetting programs",
          "offer four justification",
          "options" ]
lineLength = 24

# Your function, justify(text, lineLength)
# should return:
       [ "Some  modern typesetting",
         "programs    offer   four",
         "justification    options" ]
Within the same line, the amount of spaces between words should differ by no more than 1 space:

Not Allowed: "the      quick brown fox"
    Allowed: "the   quick   brown  fox"
Example 2:

# input
text = [ "The Earth is",
         "the only world",
         "known so far",
         "to harbor life" ]
lineLength = 18

# Return:
       [ "The  Earth  is the",
         "only  world  known",
         "so  far  to harbor",
         "life" ]
Example 3:

# input
text = [ "It underscores our responsibility",
         "to deal more kindly with one another" ]
lineLength = 15

# Return:
       [ "It  underscores",
         "our",
         "responsibility",
         "to   deal  more",
         "kindly with one",
         "another" ]
 */

import java.util.*;

public class JustifyText {

	public List<String> justify(String[] words, int maxWidth) {

		final List<String> wordList = new ArrayList<>();

		Arrays.stream(words)
				.map(word -> word.split("\\s+"))
				.forEach(wordArray -> Arrays.stream(wordArray).forEach(w -> wordList.add(w)));

        int from = 0;
        int lengthSoFar = wordList.get(0).length();
        int n = wordList.size();

        final List<String> sentences = new ArrayList<>();

        for (int i = 1; i < n; i++) {
        	int currWordLength = wordList.get(i).length();
            int newLength = lengthSoFar + 1 + currWordLength;
            if (newLength > maxWidth) {
                sentences.add(getSentence(wordList, from, i - 1, maxWidth - lengthSoFar));
                from = i;
                lengthSoFar = currWordLength;
            } else {
                lengthSoFar = newLength;
            }
        }

        sentences.add(getSentence(wordList, from, n - 1, maxWidth - lengthSoFar));
        return sentences;
    }

    private String getSentence(List<String> words, int fromIndex, int toIndex, int extraspaces) {
        if (fromIndex == toIndex) return words.get(fromIndex);

        int spaceWords = toIndex - fromIndex;
        int spaceBetweenWords = extraspaces / spaceWords + 1;
        int extraSpaceBetweenWords = extraspaces % spaceWords;

        final StringBuilder justifiedText = new StringBuilder();
        for (int i = fromIndex; i < toIndex; i++) {
            justifiedText.append(words.get(i))
            			.append(getSpaces(spaceBetweenWords + (extraSpaceBetweenWords-- > 0 ? 1 : 0)));
        }

        justifiedText.append(words.get(toIndex));
        return justifiedText.toString();
    }

    private String getSpaces(final int n) {
        if (n > 0) return String.format("%" + n + "s", "");
        return "";
    }

	public static void main(String... args) {
		final String[] text1 = {"Some modern typesetting programs",
		                        "offer four justification",
		                        "options"
		                       };
		final String[] text2 = { "The Earth is",
		                         "the only world",
		                         "known so far",
		                         "to harbor life"
		                       };
		final String[] text3 = {"It underscores our responsibility",
		                        "to deal more kindly with one another"
		                       };

		final JustifyText jt = new JustifyText();

		System.out.println("\nJustified Text 1 : ");
		jt.justify(text1, 24).forEach(System.out::println);

		System.out.println("\nJustified Text 2 : ");
		jt.justify(text2, 18).forEach(System.out::println);

		System.out.println("\nJustified Text 3 : ");
		jt.justify(text3, 15).forEach(System.out::println);
	}
}