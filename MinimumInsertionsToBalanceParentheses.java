/*
    1541. Minimum Insertions to Balance a Parentheses String
    
    Given a parentheses string s containing only the characters '(' and ')'. 
    A parentheses string is balanced if:
    
    - Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
    - Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
    
    In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
    
    For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
    You can insert the characters '(' and ')' at any position of the string to balance it if needed.

    Return the minimum number of insertions needed to make s balanced.

    Example 1:
    Input: s = "(()))"
    Output: 1
    Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. 
    We need to add one more ')' at the end of the string to be "(())))" which is balanced.

    Example 2:
    Input: s = "())"
    Output: 0
    Explanation: The string is already balanced.

    Example 3:
    Input: s = "))())("
    Output: 3
    Explanation: Add '(' to match the first '))', Add '))' to match the last '('.

    Constraints:
    1 <= s.length <= 105
    s consists of '(' and ')' only.
*/

public class MinimumInsertionsToBalanceParentheses {
    
    // Method to calculate the minimum number of insertions
    public int minInsertions(String s) {
        int open = 0;  // To track unmatched '('
        int insertions = 0;  // To track the number of insertions needed

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;  // Found an unmatched '('
            } else {
                // We found a ')', check if we need two ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // We have a pair of ')'
                    i++;  // Skip the next ')'
                } else {
                    // Single ')' found, one more ')' is required
                    insertions++;
                }
                // Now try to match this ')' with a previous '('
                if (open > 0) {
                    open--;  // A pair '(' and '))' is matched
                } else {
                    // No unmatched '(', so we need to insert one '(' before the '))'
                    insertions++;
                }
            }
        }

        // After processing, every unmatched '(' needs to be closed with '))'
        insertions += open * 2;

        return insertions;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        MinimumInsertionsToBalanceParentheses solution = new MinimumInsertionsToBalanceParentheses();

        // Test case 1
        String s1 = "(()))";
        System.out.println("Minimum insertions for '" + s1 + "': " + solution.minInsertions(s1));  // Output: 1

        // Test case 2
        String s2 = "())";
        System.out.println("Minimum insertions for '" + s2 + "': " + solution.minInsertions(s2));  // Output: 0

        // Test case 3
        String s3 = "))())(";
        System.out.println("Minimum insertions for '" + s3 + "': " + solution.minInsertions(s3));  // Output: 3
    }
}
