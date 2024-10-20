/*
1106. Parsing A Boolean Expression
A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:

- 't' that evaluates to true.
- 'f' that evaluates to false.
- '!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
- '&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
- '|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.

Given a string expression that represents a boolean expression, return the evaluation of that expression.

Example 1:
Input: expression = "&(|(f))"
Output: false

Example 2:
Input: expression = "|(f,f,f,t)"
Output: true

Example 3:
Input: expression = "!(&(f,t))"
Output: true

Constraints:
1 <= expression.length <= 2 * 10^4
expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.
*/

import java.util.Stack;

public class ParsingBooleanExpression {
    // Stack-based Approach
    public boolean parseBoolExprUsingStack(String expression) {
        Stack<Character> st = new Stack<>();

        // Iterating through each character in the expression
        for (char currChar : expression.toCharArray()) {
            if (currChar == ',' || currChar == '(') continue; // Skip ',' and '('
            
            // If the character is t, f, !, &, |, push it to the stack
            if (currChar == 't' || currChar == 'f' || currChar == '!' || currChar == '&' || currChar == '|') {
                st.push(currChar);
            } 
            // On encountering a closing parenthesis, evaluate the current expression
            else if (currChar == ')') {
                boolean hasTrue = false, hasFalse = false;

                // Process the expression till we hit an operator (!, &, |)
                while (st.peek() != '!' && st.peek() != '&' && st.peek() != '|') {
                    char topValue = st.pop();
                    if (topValue == 't') hasTrue = true;
                    if (topValue == 'f') hasFalse = true;
                }

                // Pop the operator and apply the respective logic
                char op = st.pop();
                if (op == '!') {
                    st.push(hasTrue ? 'f' : 't');  // NOT operator
                } else if (op == '&') {
                    st.push(hasFalse ? 'f' : 't');  // AND operator
                } else {
                    st.push(hasTrue ? 't' : 'f');   // OR operator
                }
            }
        }
        // The final result will be on the top of the stack
        return st.peek() == 't';
    }

    // Recursive Approach
    private int idx = 0;

    public boolean parseBoolExpr(String expression) {
        this.idx = 0;

        if (expression.length() == 1) {
            return expression.charAt(0) == 't';
        }

        return this.helper(expression);
    }

    private boolean helper(final String s) {
        final char operator = s.charAt(this.idx);

        this.idx += 2;
        
        char c = s.charAt(this.idx);

        boolean result = false;

        if (c == 't') {
            result = true;
            this.idx++;
        } else if (c == 'f') {
            result = false;
            this.idx++;
        } else {
            result = this.helper(s);
        }

        c = s.charAt(this.idx);

        while (c != ')') {
            if (c == ',') {
                c = s.charAt(++this.idx);
                continue;
            }

            boolean curr = false;

            if (c == 't') {
                curr = true;
                this.idx++;
            } else if (c == 'f') {
                curr = false;
                this.idx++;
            } else {
                curr = helper(s);
            }

            if (operator == '&') {
                result &= curr;
            } else if (operator == '|') {
                result |= curr;
            }

            c = s.charAt(this.idx);
        }

        this.idx++;

        return operator == '!' ? !result : result;
    }

    public static void main(String[] args) {
        ParsingBooleanExpression solution = new ParsingBooleanExpression();

        // Test both approaches

        // Using Stack approach
        System.out.println(solution.parseBoolExprUsingStack("&(|(f))")); // Output: false
        System.out.println(solution.parseBoolExprUsingStack("|(f,f,f,t)")); // Output: true
        System.out.println(solution.parseBoolExprUsingStack("!(&(f,t))")); // Output: true

        // Using Recursive approach
        System.out.println(solution.parseBoolExpr("&(|(f))")); // Output: false
        System.out.println(solution.parseBoolExpr("|(f,f,f,t)")); // Output: true
        System.out.println(solution.parseBoolExpr("!(&(f,t))")); // Output: true
    }
}
