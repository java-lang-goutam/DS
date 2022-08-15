/**
 *
 * We are working on a security system for a badged-access room in our company's building.

We want to find employees who badged into our secured room unusually often. We have an unordered list of names and entry times over a single day.
Access times are given as numbers up to four digits in length using 24-hour time, such as "800" or "2250".

Write a function that finds anyone who badged into the room three or more times in a one-hour period.
Your function should return each of the employees who fit that criteria, plus the times that they badged in during the one-hour period.
If there are multiple one-hour periods where this was true for an employee, just return the earliest one for that employee.

badge_times = {
{"Paul", "1355"},
{"Jennifer", "1910"},
{"John", "835"},
{"John", "830"},
{"Paul", "1315"},
{"John", "1615"},
{"John", "1640"},
{"Paul", "1405"},
{"John", "855"},
{"John", "930"},
{"John", "915"},
{"John", "730"},
{"John", "940"},
{"Jennifer", "1335"},
{"Jennifer", "730"},
{"John", "1630"},
{"Jennifer", "5"}
}

Expected output (in any order)
John: 830 835 855 915 930
Paul: 1315 1355 1405
 *
 */

import java.util.*;

public class EmployeeTime {

	private static Map<String, Set<Integer>> findEmployees(String[][] badgeTimes) {
		Map<String, Set<Integer>> employeeLoginTimes = new HashMap<>();
		for (String[] badgeTime : badgeTimes) {
			employeeLoginTimes.computeIfAbsent(badgeTime[0], name -> new TreeSet<>()).add(Integer.parseInt(badgeTime[1]));
		}

		Iterator<Map.Entry<String, Set<Integer>>> it = employeeLoginTimes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Set<Integer>> entry = it.next();
			TreeSet<Integer> times = (TreeSet) entry.getValue();
			//System.out.println(times);
			boolean remove = true;
			if (times.size() >= 3) {
				for (Integer time : times) {
					Set<Integer> windowTime = times.subSet(time, time + 101);
					//System.out.println(windowTime);
					if (windowTime.size() >= 3) {
						remove = false;
						employeeLoginTimes.put(entry.getKey(), windowTime);
						break;
					}
				}
			}
			if (remove) it.remove();
		}

		return employeeLoginTimes;
	}

	public static void main(String... args) {
		final String[][] badgeTimes = {
			{"Paul", "1355"},
			{"Jennifer", "1910"},
			{"John", "835"},
			{"John", "830"},
			{"Paul", "1315"},
			{"John", "1615"},
			{"John", "1640"},
			{"Paul", "1405"},
			{"John", "855"},
			{"John", "930"},
			{"John", "915"},
			{"John", "730"},
			{"John", "940"},
			{"Jennifer", "1335"},
			{"Jennifer", "730"},
			{"John", "1630"},
			{"Jennifer", "5"}
		};

		System.out.println(findEmployees(badgeTimes));
	}
}