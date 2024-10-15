/**
 * Problem: 2938. Separate Black and White Balls
 * 
 * There are n balls on a table, each ball has a color black or white.
 * You are given a 0-indexed binary string s of length n, where 1 and 0 
 * represent black and white balls, respectively.
 * 
 * In each step, you can choose two adjacent balls and swap them.
 * 
 * Return the minimum number of steps to group all the black balls to the 
 * right and all the white balls to the left.
 * 
 * Example:
 * Input: s = "101"
 * Output: 1
 * Explanation: We can group all the black balls to the right in the 
 * following way: Swap s[0] and s[1], s = "011". 
 * 
 * Constraints:
 * 1 <= n == s.length <= 10^5
 * s[i] is either '0' or '1'.
 */

 public class SeparateBlackAndWhiteBalls {
    public long minimumSteps(String s) {
        long ans = 0;
        long countOnes = 0;  // Counts the number of '1's encountered

        // Traverse through the string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                countOnes++;  // Count how many '1's have been seen
            } else {
                ans += countOnes;  // For every '0', add the count of '1's seen before it
            }
        }

        return ans;
    }

    // Main method to test the function
    public static void main(String[] args) {
        SeparateBlackAndWhiteBalls solution = new SeparateBlackAndWhiteBalls();
        System.out.println(solution.minimumSteps("101"));  // Output: 1
        System.out.println(solution.minimumSteps("100"));  // Output: 2
        System.out.println(solution.minimumSteps("0111")); // Output: 0
    }
}

//=============================================================================================================

/*
 
    class Solution {
    public long minimumSteps(String s) {
        int left = 0;
        long countOnes = 0;  // c variable in the original code

        // Traverse through the string
        for (int cur = 0; cur < s.length(); cur++) {
            if (s.charAt(cur) == '0') {
                countOnes += (cur-left);
                left++ ; // Count how many '1's have been seen
            }
        }

        return countOnes;
    }
}
    

 */