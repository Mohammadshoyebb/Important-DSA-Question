// 301. Remove Invalid Parentheses
// Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
// Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

// Example 1:
// Input: s = "()())()"
// Output: ["(())()","()()()"]

// Example 2:
// Input: s = "(a)())()"
// Output: ["(a())()","(a)()()"]

// Example 3:
// Input: s = ")("
// Output: [""]

// Constraints:
// 1 <= s.length <= 25
// s consists of lowercase English letters and parentheses '(' and ')'.
// There will be at most 20 parentheses in s.

import java.util.*;

public class RemoveInvalidParenthesesSolution {
    
    // First approach: Remove invalid parentheses using DFS
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int minRemoval(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() == ')') {
                    stack.push(ch);
                } else {
                    stack.pop();
                }
            }
        }
        return stack.size();
    }

    public void findValidString(String s, int mra, HashSet<String> res, HashSet<String> visited) {
        if (visited.contains(s)) {
            return;
        }
        visited.add(s);

        if (mra == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                String newStr = s.substring(0, i) + s.substring(i + 1);
                findValidString(newStr, mra - 1, res, visited);
            }
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> res = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        int minRemovalAllowed = minRemoval(s);
        findValidString(s, minRemovalAllowed, res, visited);

        return new ArrayList<>(res);
    }

    // Second approach: Remove invalid parentheses using BFS
    public List<String> removeInvalidParenthesesBFS(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    public static void main(String[] args) {
        RemoveInvalidParenthesesSolution sol = new RemoveInvalidParenthesesSolution();
        String s = "(a)())()";
        
        // Test the first approach
        List<String> result1 = sol.removeInvalidParentheses(s);
        System.out.println("DFS Approach: " + result1);

        // Test the second approach
        List<String> result2 = sol.removeInvalidParenthesesBFS(s);
        System.out.println("BFS Approach: " + result2);
    }
}
