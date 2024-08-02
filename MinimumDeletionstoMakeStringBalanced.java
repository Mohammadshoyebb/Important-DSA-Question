/**
 * 1653. Minimum Deletions to Make String Balanced
 * 
 * You are given a string s consisting only of characters 'a' and 'b'.
 * You can delete any number of characters in s to make s balanced. 
 * s is balanced if there is no pair of indices (i,j) such that i < j 
 * and s[i] = 'b' and s[j]= 'a'.
 * 
 * Return the minimum number of deletions needed to make s balanced.
 * 
 * Example 1:
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * 
 * Example 2:
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 * 
 * Constraints:
 * 1 <= s.length <= 105
 * s[i] is 'a' or 'b'.
 */

public class MinimumDeletionstoMakeStringBalanced {

    /**
     * Approach 1: Using two auxiliary arrays to count 'a's and 'b's.
     * Time complexity: O(n)
     * Space complexity: O(2n)
     */
    public int minimumDeletionsApproach1(String s) {
        int n = s.length();
        int[] aCount = new int[n];
        int[] bCount = new int[n];

        // bCount
        for (int i = 1; i < n; i++) {
            char prevChar = s.charAt(i - 1);
            bCount[i] = bCount[i - 1];
            if (prevChar == 'b') {
                bCount[i] += 1;
            }
        }

        // aCount
        for (int i = n - 2; i >= 0; i--) {
            char nextChar = s.charAt(i + 1);
            aCount[i] = aCount[i + 1];
            if (nextChar == 'a') {
                aCount[i] += 1;
            }
        }

        int result = Integer.MAX_VALUE;

        // Calculate minimum deletions
        for (int i = 0; i < n; i++) {
            result = Math.min(result, aCount[i] + bCount[i]);
        }

        return result;
    }

    /**
     * Approach 2: Using one auxiliary array to count 'a's and a variable to count 'b's.
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int minimumDeletionsApproach2(String s) {
        int n = s.length();
        int[] aCount = new int[n];

        // aCount
        for (int i = n - 2; i >= 0; i--) {
            char nextChar = s.charAt(i + 1);
            aCount[i] = aCount[i + 1];
            if (nextChar == 'a') {
                aCount[i] += 1;
            }
        }

        int result = Integer.MAX_VALUE;
        int bCount = 0;

        // Calculate minimum deletions
        for (int i = 0; i < n; i++) {
            result = Math.min(result, aCount[i] + bCount);
            if (s.charAt(i) == 'b') {
                bCount += 1;
            }
        }

        return result;
    }

    /**
     * Approach 3: Using variables to track 'a's and 'b's.
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int minimumDeletionsApproach3(String s) {
        int n = s.length();
        int aCount = 0;

        // Count total number of 'a's
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                aCount++;
            }
        }

        int seenA = 0;
        int result = Integer.MAX_VALUE;
        int bCount = 0;

        // Calculate minimum deletions
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                seenA++;
            }
            result = Math.min(result, (aCount - seenA) + bCount);
            if (s.charAt(i) == 'b') {
                bCount++;
            }
        }

        return result;
    }

    /**
     * Optimized Approach: Using a variable to count 'b's and a variable for the result.
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int minimumDeletionsOptimized(String s) {
        int b = 0;
        int ans = 0;

        for (char c : s.toCharArray()) {
            if (c == 'b') {
                b++;
            } else {
                if (b > 0) {
                    ans += 1;
                    b--;
                } else {
                    b = 0;
                }
            }
        }
        return ans;
    }

    // Main method to test the approaches
    public static void main(String[] args) {
        MinimumDeletionstoMakeStringBalanced solution = new MinimumDeletionstoMakeStringBalanced();

        // Example 1
        String s1 = "aababbab";
        System.out.println("Approach 1: " + solution.minimumDeletionsApproach1(s1)); // Output: 2
        System.out.println("Approach 2: " + solution.minimumDeletionsApproach2(s1)); // Output: 2
        System.out.println("Approach 3: " + solution.minimumDeletionsApproach3(s1)); // Output: 2
        System.out.println("Optimized Approach: " + solution.minimumDeletionsOptimized(s1)); // Output: 2

        // Example 2
        String s2 = "bbaaaaabb";
        System.out.println("Approach 1: " + solution.minimumDeletionsApproach1(s2)); // Output: 2
        System.out.println("Approach 2: " + solution.minimumDeletionsApproach2(s2)); // Output: 2
        System.out.println("Approach 3: " + solution.minimumDeletionsApproach3(s2)); // Output: 2
        System.out.println("Optimized Approach: " + solution.minimumDeletionsOptimized(s2)); // Output: 2
    }
}
