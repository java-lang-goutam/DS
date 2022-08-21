/**
 * Given a collection of actions and userIds an error occurs when a userId takes a specific action in order for example

A => B => => C gives an errror
B => A => C no error and etc

Write a function that takes in a list of (Actions, UserIds) pairs and returns the user Id that ecounters the error

Sample Input:

action_user_1 = [
["A", "1"],
["B", "1"],
["B", "2"],
["C", "1"],
["C", "2"],
["C", "3"],
["A", "2],
["A", "3"],
["A", "2"],
["B", "2],
["C", "2"],
]

Expected output 1,2

action_user_2 = [
["A", "1"],
["A", "1"]
["A", "1"]
["B", "1"],
["B", "2"],
["C", "2"],
["C", "2"],
["C", "3"],
["A", "2],
["A", "3"],
["A", "2"],
["B", "2],
["C", "2"],
]

Expected output 2

Can someone provide some insight on how to solve this

List result = getUserErrorList(action_user_1,"ABC");
List result2 = getUserErrorList(action_user_2,"BAC");

*/

import java.util.*;
import java.util.stream.*;

class UserErrorAction {

	private static List<String> getUserErrorList(String[][] actionsUsers, String errorAction) {
		Map<String, String> actions = new HashMap<>();
		for (String[] actionUser : actionsUsers) {
			String userAction = actions.getOrDefault(actionUser[1], "") + actionUser[0];
			if (errorAction.startsWith(userAction))
				actions.put(actionUser[1], userAction);
		}

		return actions.entrySet()
		       .stream()
		       .filter(e -> e.getValue().equals(errorAction))
		       .map(e -> e.getKey())
		       .collect(Collectors.toList());
	}

	public static void main(String... args) {
		String[][] action_user_1 = {
			{"A", "1"},
			{"B", "1"},
			{"B", "2"},
			{"C", "1"},
			{"C", "2"},
			{"C", "3"},
			{"A", "2"},
			{"A", "3"},
			{"A", "2"},
			{"B", "2"},
			{"C", "2"},
		};

		String[][] action_user_2 = {
			{"A", "1"},
			{"A", "1"},
			{"A", "1"},
			{"B", "1"},
			{"B", "2"},
			{"C", "2"},
			{"C", "2"},
			{"C", "3"},
			{"A", "2"},
			{"A", "3"},
			{"A", "2"},
			{"B", "2"},
			{"C", "2"},
		};

		System.out.println(getUserErrorList(action_user_1, "ABC"));
		System.out.println(getUserErrorList(action_user_2, "BAC"));
	}
}
