/*
    2696. Minimum String Length After Removing Substrings
    
    You are given a string s consisting only of uppercase English letters.
    
    You can apply some operations to this string where, in one operation, you can remove 
    any occurrence of one of the substrings "AB" or "CD" from s.

    Return the minimum possible length of the resulting string that you can obtain.

    Note that the string concatenates after removing the substring and could produce new 
    "AB" or "CD" substrings.

    Example 1:
    Input: s = "ABFCACDB"
    Output: 2
    Explanation: We can do the following operations:
    - Remove the substring "ABFCACDB", so s = "FCACDB".
    - Remove the substring "FCACDB", so s = "FCAB".
    - Remove the substring "FCAB", so s = "FC".
    So the resulting length of the string is 2.
    It can be shown that it is the minimum length that we can obtain.

    Example 2:
    Input: s = "ACBBD"
    Output: 5
    Explanation: We cannot do any operations on the string so the length remains the same.

    Constraints:
    - 1 <= s.length <= 100
    - s consists only of uppercase English letters.
*/

import java.util.Stack;

public class MinimumStringLength {

    // Approach 1: Using a Stack to remove "AB" and "CD"
    public int minLengthUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            
            // If the stack is empty, push the current character
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                // Check if the current character with the top of the stack forms "AB" or "CD"
                char top = stack.peek();
                if ((top == 'A' && c == 'B') || (top == 'C' && c == 'D')) {
                    // Pop the top element if a pair is found (removing "AB" or "CD")
                    stack.pop();
                } else {
                    // Push the current character if no matching pair is found
                    stack.push(c);
                }
            }
        }
        
        // The remaining length of the stack is the final length of the string after all reductions
        return stack.size();
    }

    // Approach 2: Optimized with character array, two-pointer method
    public int minLengthUsingTwoPointers(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0;
        int j = 1;

        // Iterate through the string using two pointers
        while (j < n) {
            if (i < 0) {
                i++;
                arr[i] = arr[j];  // Assign character from j to i if i goes negative
            } else if ((arr[i] == 'A' && arr[j] == 'B') || (arr[i] == 'C' && arr[j] == 'D')) {
                i--;  // Reduce i to simulate removal of "AB" or "CD"
            } else {
                i++;
                arr[i] = arr[j];  // Assign character from j to i if no removal
            }
            j++;
        }

        return i + 1;  // Return the length of the remaining string
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        MinimumStringLength solution = new MinimumStringLength();

        // Test case 1
        String s1 = "ABFCACDB";
        System.out.println("Using Stack - Result for '" + s1 + "': " + solution.minLengthUsingStack(s1)); // Output: 2
        System.out.println("Using Two Pointers - Result for '" + s1 + "': " + solution.minLengthUsingTwoPointers(s1)); // Output: 2

        // Test case 2
        String s2 = "ACBBD";
        System.out.println("Using Stack - Result for '" + s2 + "': " + solution.minLengthUsingStack(s2)); // Output: 5
        System.out.println("Using Two Pointers - Result for '" + s2 + "': " + solution.minLengthUsingTwoPointers(s2)); // Output: 5
    }
}
