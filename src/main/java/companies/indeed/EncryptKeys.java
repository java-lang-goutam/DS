/**
 You decide to create a substitution cipher. The cipher alphabet is based on a key shared amongst those of your friends who don't mind spoilers.

Suppose the key is:
"The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!".

We use only the unique letters in this key to set the order of the characters in the substitution table.

T H E Q U I C K O N Y X G B L R A S W D J M P V Z F

(spaces added for readability)

We then align it with the regular alphabet:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
T H E Q U I C K O N Y X G B L R A S W D J M P V Z F

Which gives us the substitution mapping: A becomes T, B becomes H, C becomes E, etc.

Write a function that takes a key and a string and encrypts the string with the key.

Example:
key = "The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!"
encrypt("It was all a dream.", key) -> "Od ptw txx t qsutg."
encrypt("Would you kindly?", key) -> "Pljxq zlj yobqxz?"

Complexity analysis:

m: The length of the message
k: The length of the key
 */
 
import java.util.*;

public class EncryptKeys {

	final char[] keymap;
	final int offset = 'a' - 'A';

	public EncryptKeys(final String key) {
		keymap = new char[26];
		mapKeys(key);
	}

	private void mapKeys(final String key) {
		int count = 0;
		final boolean[] visited = new boolean[26];

		for (int i=0, n = key.length(); i < n && count < 26; i++) {
			final char currChar = toLowerCase(key.charAt(i));
			if (currChar >= 'a' && currChar <= 'z' && !visited[currChar - 'a']) {
				visited[currChar-'a'] = true;
				keymap[count++] = currChar;
			}
		}
	}

	private char toLowerCase(char c) {
		if (c >= 'A' && c <= 'Z') c += offset;
		return c;
	}

	private char toUpperCase(char c) {
		if (c >= 'a' && c <= 'z') c -= offset;
		return c;
	}

	private String encrypt(final String str) {
		StringBuilder encryptedString = new StringBuilder();
		for (int i=0, n = str.length(); i < n; i++) {
			char currChar = str.charAt(i);
			if (currChar >= 'A' && currChar <= 'Z') {
				encryptedString.append(toUpperCase(keymap[toLowerCase(currChar) - 'a']));
			} else if (currChar >= 'a' && currChar <= 'z') {
				encryptedString.append(keymap[currChar - 'a']);
			} else {
				encryptedString.append(currChar);
			}
		}

		return encryptedString.toString();
	}

	public static void main(String... args) {
		final String key = "The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!";
		EncryptKeys ek = new EncryptKeys(key);
		System.out.println(ek.encrypt("It was all a dream."));
		System.out.println(ek.encrypt("Would you kindly?"));
	}
}