import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * Solved
 * Medium
 * Topics
 * Companies
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * Example 1:
 * 
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * 
 * Input: n = 1
 * Output: ["()"]
 * 
 * Constraints:
 * 
 * 1 <= n <= 8
 */
public class GenerateParentheses {

    // Method to generate all combinations of well-formed parentheses
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(result, "", 0, 0, n);
        return result;
    }

    // Helper method for backtracking
    private void generateParenthesis(List<String> result, String current, int open, int close, int max) {
        // If the current string has the maximum number of parentheses, add it to the result
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // If the number of open parentheses is less than the maximum, add an open parenthesis
        if (open < max) {
            generateParenthesis(result, current + "(", open + 1, close, max);
        }

        // If the number of close parentheses is less than the number of open parentheses, add a close parenthesis
        if (close < open) {
            generateParenthesis(result, current + ")", open, close + 1, max);
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        System.out.println(gp.generateParenthesis(3)); // Output: ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println(gp.generateParenthesis(1)); // Output: ["()"]
    }
}

