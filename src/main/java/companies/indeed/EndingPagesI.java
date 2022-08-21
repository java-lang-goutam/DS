/**
 * Given a book with a finite number of pages. At each page, it can be either empty or contain 2 options, each option represent the page number that readers must jump to to continue reading, if the page does not have any option, simply go to the next page. if the options is available at a page, readers have to pick one. The book has multiple ending pages, if an ending page is reached, no further reading is needed.

Question 1:
Given endingPages, pageOptions, optionToPick, write a function that take in all these arguments and return the ending page. if the pageOption somehow lead to an infinite loop, return -1. Assume that readers can always reach one ending of infinite loop does not happen.

Example: happy path

    endingPages - [9,15, 20] -> array of integer
    pageOption - [ [3,5,11] ] -> An array that contains multple ingeter array with format of [this page number has options, option 1, option 2]
    [3,5,11] means that at page 3, there are 2 options, option 1 is to jump to page 5, option 2 is to jump to page 11.
    optionToPick - 1 -> an integer that tells readers which option they have to pick. with optionToPick as 1 and with pageOption [ [3,5,11], [21, 30, 39] ], this means that at page 3 readers have to jump to page 5, at page 21 readers have to jump to page 30.

1-2-3-5-6-7-8-9 -> at page 1 go to 2 because page 1 has no option. do the same for page 2. at page 3 go to page 5 because readers have to pick option 1, continue to page 9 which is an ending, we stop here and return 9 as result

Example: infinite loop

    endingPages - [9,15, 20]
    pageOption - [ [3,5,11], [7, 1 , 20] ]
    optionToPick - 1

1-2-3-5-6-7-1...
return -1 as result because we hit infinite loop here.
**/

import java.util.*;

public class EndingPagesI {

	public List<Integer> findGoodEndings(int[] goodEndingPages, int[] badEndingPages, int[][] pageOption) {
		final List<Integer> routes = new ArrayList<>();
		final Set<Integer> seen = new HashSet<>();
		final Set<Integer> badEndingPagesSet = new HashSet<>();
		final Set<Integer> goodEndingPagesSet = new HashSet<>();
		final Map<Integer, List<Integer>> options = new HashMap<>();

		for (int page : badEndingPages) badEndingPagesSet.add(page);
		for (int page : goodEndingPages) goodEndingPagesSet.add(page);

		//System.out.println(goodEndingPagesSet);
		//System.out.println(badEndingPagesSet);

		for (int[] option : pageOption) {
			List<Integer> optionList = new ArrayList<>();
			for (int i=1; i<option.length; i++) {
				optionList.add(option[i]);
			}
			options.put(option[0], optionList);
		}

		Arrays.sort(pageOption, Comparator.comparingInt(p -> p[0]));

		getRoutes(routes, seen, badEndingPagesSet, goodEndingPagesSet, options, 0);
		return routes;
	}

	private void getRoutes(final List<Integer> routes, final Set<Integer> seen, final Set<Integer> badEndingPagesSet,
		final Set<Integer> goodEndingPagesSet, final Map<Integer, List<Integer>> options, int i) {

		if (seen.contains(i) || badEndingPagesSet.contains(i)) return;
		if (goodEndingPagesSet.contains(i)) {
			routes.add(i);
			return;
		}

		seen.add(i);

		if (options.containsKey(i)) {
			// visit selected option
			for (Integer nextRoute : options.get(i)) {
				getRoutes(routes, seen, badEndingPagesSet, goodEndingPagesSet, options, nextRoute);
			}
		} else {
			// iterate
			getRoutes(routes, seen, badEndingPagesSet, goodEndingPagesSet, options, i+1);
		}
	}

	public int find(int[] endingPages, int[][] pageOption, int optionToPick) {
		final Set<Integer> endings = new HashSet<>();
		final Set<Integer> seen = new HashSet<>();

		int maxEnding = Integer.MIN_VALUE;
		for (int page : endingPages) {
			endings.add(page);
			maxEnding = Math.max(maxEnding, page);
		}
		Arrays.sort(pageOption, Comparator.comparingInt(p -> p[0]));

		int i=0, next = 0;
		while (true) {
			if (endings.contains(i)) return i;
			if (seen.contains(i) || i > maxEnding) return -1;
			if (next < pageOption.length && pageOption[next][0] == i) {
				i = pageOption[next][optionToPick];
				next++;
			} else {
				seen.add(i);
				i++;
			}
		}
	}

	public static void main(String... args) {
		EndingPagesI ep = new EndingPagesI();
		int[] endingPages = {9,15, 20};
		int[][] pageOptions = {{3,5,11}, {7, 1 , 20}};
		int optionToPick = 1;
		System.out.println(ep.find(endingPages, pageOptions, optionToPick));

		int[] goodEndingPages = {9,15,30};
		int[] badEndingPages = {17, 25};
		int[][] pageOptions1 = {{3,5,11}, {13, 15, 17}};
		System.out.println(ep.findGoodEndings(goodEndingPages, badEndingPages, pageOptions1));
	}
}
