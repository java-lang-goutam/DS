/**
/*

We have some clickstream data that we gathered on our client's website. Using cookies, we collected snippets of users' anonymized URL histories while they browsed the site. The histories are in chronological order, and no URL was visited more than once per person.

Write a function that takes two users' browsing histories as input and returns the longest contiguous sequence of URLs that appears in both.

Sample input:

user0 = ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
user1 = ["/start", "/pink", "/register", "/orange", "/red", "a"]
user2 = ["a", "/one", "/two"]
user3 = ["/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"]
user4 = ["/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"]
user5 = ["a"]
user6 = ["/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber"]

Sample output:

findContiguousHistory(user0, user1) => ["/pink", "/register", "/orange"]
findContiguousHistory(user0, user2) => [] (empty)
findContiguousHistory(user0, user0) => ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
findContiguousHistory(user2, user1) => ["a"]
findContiguousHistory(user5, user2) => ["a"]
findContiguousHistory(user3, user4) => ["/plum", "/blue", "/tan", "/red"]
findContiguousHistory(user4, user3) => ["/plum", "/blue", "/tan", "/red"]
findContiguousHistory(user3, user6) => ["/tan", "/red", "/amber"]

n: length of the first user's browsing history
m: length of the second user's browsing history

*/

import java.util.*;

public class UrlVisit {

	private static final Map<String, List<String>> userUrlVisit = new HashMap<>();

	public static List<String> findContiguousHistory(final String userA, final String userB) {
		if (userA == null || userB == null || !userUrlVisit.containsKey(userA) || !userUrlVisit.containsKey(userB)) 
			return Collections.emptyList();
		if (userA.equals(userB)) 
			return userUrlVisit.get(userA);

		final List<String> historyA = userUrlVisit.get(userA);
		final List<String> historyB = userUrlVisit.get(userB);

		final Map<String, Integer> historyAIndex = new HashMap<>();
		for (int i=0; i<historyA.size(); i++) {
			historyAIndex.put(historyA.get(i), i);
		}

		List<String> contiguosHistory = new ArrayList<>();
		for (int i=0, n = historyB.size(); i < n; i++) {
			if (historyAIndex.containsKey(historyB.get(i))) {
				//System.out.println(historyB.get(i));
				final List<String> currentHistory = new ArrayList<>();
				int j = historyAIndex.get(historyB.get(i));
				while (i < historyB.size() && j < historyA.size() && historyB.get(i).equals(historyA.get(j))) {
					currentHistory.add(historyB.get(i));
					i++; j++;
				}
				i--;
				if (currentHistory.size() > contiguosHistory.size()) contiguosHistory = currentHistory;
			}
		}

		return contiguosHistory;
	}

	public static void main(String... args) {

		userUrlVisit.put("user0", Arrays.asList("/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"));
		userUrlVisit.put("user1", Arrays.asList("/start", "/pink", "/register", "/orange", "/red", "a"));
		userUrlVisit.put("user2", Arrays.asList("a", "/one", "/two"));
		userUrlVisit.put("user3", Arrays.asList("/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"));
		userUrlVisit.put("user4", Arrays.asList("/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"));
		userUrlVisit.put("user5", Arrays.asList("a"));
		userUrlVisit.put("user6", Arrays.asList("/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber"));

		System.out.println(findContiguousHistory("user0", "user1"));
		System.out.println(findContiguousHistory("user0", "user2"));
		System.out.println(findContiguousHistory("user0", "user0"));
		System.out.println(findContiguousHistory("user2", "user1"));
		System.out.println(findContiguousHistory("user5", "user2"));
		System.out.println(findContiguousHistory("user3", "user4"));
		System.out.println(findContiguousHistory("user4", "user3"));
		System.out.println(findContiguousHistory("user3", "user6"));
	}
}

