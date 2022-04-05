package algo;

import java.util.Stack;

/**
 * 856. Score of Parentheses
 * Medium
 *
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 *
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 *
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 *
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 50
 * s consists of only '(' and ')'.
 * s is a balanced parentheses string.
 */
public class ScoreofParentheses {

    public int scoreOfParentheses(String s) {
        final Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') stack.push(0);
            else {
                int prevLevel = stack.pop();
                int currentLevel = stack.pop();
                stack.push(currentLevel + Math.max(prevLevel * 2, 1)); // update current level based on prev level
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        final ScoreofParentheses sop = new ScoreofParentheses();
        final String s = "((()()))";
        System.out.println(sop.scoreOfParentheses(s));
    }
}
