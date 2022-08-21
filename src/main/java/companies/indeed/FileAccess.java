/**
 *
 *
 * Q2. Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.

The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.

Example:
logs1 = [
["58523", "user_1", "resource_1"],
["62314", "user_2", "resource_2"],
["54001", "user_1", "resource_3"],
["200", "user_6", "resource_5"],
["215", "user_6", "resource_4"],
["54060", "user_2", "resource_3"],
["53760", "user_3", "resource_3"],
["58522", "user_22", "resource_1"],
["53651", "user_5", "resource_3"],
["2", "user_6", "resource_1"],
["100", "user_6", "resource_6"],
["400", "user_7", "resource_2"],
["100", "user_8", "resource_6"],
["54359", "user_1", "resource_3"],
]

Example 2:
logs2 = [
["300", "user_1", "resource_3"],
["599", "user_1", "resource_3"],
["900", "user_1", "resource_3"],
["1199", "user_1", "resource_3"],
["1200", "user_1", "resource_3"],
["1201", "user_1", "resource_3"],
["1202", "user_1", "resource_3"]
]
resource_3 = [300-300, 300-599, ]
[]

Example 3:
logs3 = [
["300", "user_10", "resource_5"]
]

Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.

Expected Output:
most_requested_resource(logs1) # => ('resource_3', 3)
Reason: resource_3 is accessed at 53760, 54001, and 54060

most_requested_resource(logs2) # => ('resource_3', 4)
Reason: resource_3 is accessed at 1199, 1200, 1201, and 1202

most_requested_resource(logs3) # => ('resource_5', 1)
Reason: resource_5 is accessed at 300

Complexity analysis variables:

n: number of logs in the input

 *
 *
 *
 * */

import java.util.*;

public class FileAccess {

	final Map<String, Integer> mostRequestedResource(String[][] logs) {
		final Map<String, List<Integer>> resourceTimes = new HashMap<>();
		for (String[] log : logs) {
			resourceTimes.computeIfAbsent(log[2], k -> new ArrayList<Integer>()).add(Integer.parseInt(log[0]));
		}

		int maxCount = 0;
		String maxResource = "";
		Map<String, Integer> maxAccess = new HashMap<>();

		for (Map.Entry<String, List<Integer>> resourceTime: resourceTimes.entrySet()) {
			final String resource = resourceTime.getKey();
			final List<Integer> times = resourceTime.getValue();
			Collections.sort(times);

			for (int i=0, n = times.size(), j = 0; i < n;) {
				while (i < n && times.get(i) - times.get(j) <= 300) i++;
				if (maxCount < i-j) {
					maxCount = i-j;
					maxResource = resource;
				}
				if (i != n ) while (j <= i && times.get(i) - times.get(j) > 300) j++;
			}

		}

		return Collections.singletonMap(maxResource, maxCount);
	}

	public static void main(String... args) {
		FileAccess fa = new FileAccess();

		String[][] logs1 = {
			{"58523", "user_1", "resource_1"},
			{"62314", "user_2", "resource_2"},
			{"54001", "user_1", "resource_3"},
			{"200", "user_6", "resource_5"},
			{"215", "user_6", "resource_4"},
			{"54060", "user_2", "resource_3"},
			{"53760", "user_3", "resource_3"},
			{"58522", "user_22", "resource_1"},
			{"53651", "user_5", "resource_3"},
			{"2", "user_6", "resource_1"},
			{"100", "user_6", "resource_6"},
			{"400", "user_7", "resource_2"},
			{"100", "user_8", "resource_6"},
			{"54359", "user_1", "resource_3"},
		};

		String[][] logs2 = {
			{"300", "user_1", "resource_3"},
			{"599", "user_1", "resource_3"},
			{"900", "user_1", "resource_3"},
			{"1199", "user_1", "resource_3"},
			{"1200", "user_1", "resource_3"},
			{"1201", "user_1", "resource_3"},
			{"1202", "user_1", "resource_3"}
		};

		String[][] logs3 = {
			{"300", "user_10", "resource_5"}
		};


		System.out.println(fa.mostRequestedResource(logs1));
		System.out.println(fa.mostRequestedResource(logs2));
		System.out.println(fa.mostRequestedResource(logs3));
	}
}
