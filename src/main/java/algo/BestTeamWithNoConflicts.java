package algo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1626. Best Team With No Conflicts
 * Medium
 *
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest
 * overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly
 * higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player,
 * respectively, return the highest overall score of all possible basketball teams.
 *
 *
 *
 * Example 1:
 *
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 *
 * Example 2:
 *
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the
 * same age.
 *
 * Example 3:
 *
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 *
 *
 *
 * Constraints:
 *
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 106
 * 1 <= ages[i] <= 1000
 */
public class BestTeamWithNoConflicts {

    public int bestTeamScore(int[] scores, int[] ages) {
        final int n = scores.length;
        final int[][] scoreages = new int[n][];

        for (int i = 0; i < n; i++) {
            final int[] arr = {ages[i], scores[i]};
            scoreages[i] = arr;
        }

        Arrays.sort(scoreages, (arr1, arr2) -> arr1[0] != arr2[0] ? arr1[0] - arr2[0] : arr1[1] - arr2[1]);
        return lis(scoreages, n);
    }

    private int lis(int[][] arr, int n) {
        int maxScore = 0;
        for (int i = 0; i < n; i++) {
            arr[i][0] = arr[i][1];
            for (int j = 0; j < i; j++) {
                if (arr[i][1] >= arr[j][1]) {
                    arr[i][0] = Math.max(arr[i][0], arr[j][0] + arr[i][1]);
                }
            }
            maxScore = Math.max(maxScore, arr[i][0]);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        int[] scores = {319776, 611683, 835240, 602298, 430007, 574, 142444, 858606, 734364, 896074},
                ages = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(new BestTeamWithNoConflicts().bestTeamScore(scores, ages));
    }
}
