/*
440. K-th Smallest in Lexicographical Order

Given two integers n and k, return the k-th lexicographically smallest integer in the range [1, n].

Example 1:
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

Example 2:
Input: n = 1, k = 1
Output: 1

Constraints:
1 <= k <= n <= 10^9
*/

import java.util.*;

public class KthLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--; // Start from 1st number, hence reduce k by 1

        while (k > 0) {
            long steps = calculateSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr++; // Move to the next number
                k -= steps;
            } else {
                curr *= 10; // Move down to the next level
                k--;
            }
        }

        return curr;
    }

    // This method calculates how many numbers are there between current and next in lexicographical order
    private long calculateSteps(int n, long curr, long next) {
        long steps = 0;
        while (curr <= n) {
            steps += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return steps;
    }

    public static void main(String[] args) {
        KthLexicographicalOrder solution = new KthLexicographicalOrder();
        System.out.println(solution.findKthNumber(13, 2)); // Output: 10
        System.out.println(solution.findKthNumber(1, 1)); // Output: 1
    }
}
