/*
 
2485. Find the Pivot Integer
Easy
Topics
Companies
Hint
Given a positive integer n, find the pivot integer x such that:

The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.

 

Example 1:

Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.
Example 3:

Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.
 

Constraints:

1 <= n <= 1000

 */

public class PivotInteger {

    // Approach 1: Brute Force
    public int bruteForce(int n) {
        for (int x = 1; x <= n; x++) {
            int leftSum = 0;
            int rightSum = 0;
            
            // Sum from 1 to x
            for (int i = 1; i <= x; i++) {
                leftSum += i;
            }
            
            // Sum from x to n
            for (int i = x; i <= n; i++) {
                rightSum += i;
            }
            
            // Check if both sums are equal
            if (leftSum == rightSum) {
                return x;  // x is the pivot integer
            }
        }
        return -1;  // No pivot integer found
    }

    // Approach 2: Cumulative Sum
    public int cumulativeSum(int n) {
        // Calculate the total sum from 1 to n
        int totalSum = n * (n + 1) / 2;
        int leftSum = 0;

        // Traverse through each number to find the pivot
        for (int x = 1; x <= n; x++) {
            leftSum += x;  // Sum from 1 to x
            int rightSum = totalSum - leftSum + x;

            // If left and right sums are equal
            if (leftSum == rightSum) {
                return x;
            }
        }
        return -1;  // No pivot integer found
    }

    // Approach 3: Mathematical Formula
    public int mathFormula(int n) {
        // Use a direct mathematical approach to solve for pivot
        int totalSum = n * (n + 1) / 2;

        // Find a potential pivot by solving (1 + 2 + ... + x) = (x + ... + n)
        int x = (int) Math.sqrt(totalSum);

        // Verify if the computed x is the actual pivot
        if (x * x == totalSum) {
            return x;
        }
        return -1;  // No pivot integer found
    }

    public static void main(String[] args) {
        PivotInteger obj = new PivotInteger();
        int n = 8;

        // Using Brute Force Approach
        System.out.println("Brute Force: " + obj.bruteForce(n));

        // Using Cumulative Sum Approach
        System.out.println("Cumulative Sum: " + obj.cumulativeSum(n));

        // Using Mathematical Formula Approach
        System.out.println("Math Formula: " + obj.mathFormula(n));


        n = 24;

        // Using Brute Force Approach
        System.out.println("Brute Force: " + obj.bruteForce(n));

        // Using Cumulative Sum Approach
        System.out.println("Cumulative Sum: " + obj.cumulativeSum(n));

        // Using Mathematical Formula Approach
        System.out.println("Math Formula: " + obj.mathFormula(n));
    }
}
