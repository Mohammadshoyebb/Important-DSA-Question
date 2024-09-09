/**
 * 2028. Find Missing Observations
 * 
 * You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6. 
 * n of the observations went missing, and you only have the observations of m rolls. 
 * Fortunately, you have also calculated the average value of the n + m rolls.
 * 
 * You are given an integer array rolls of length m where rolls[i] is the value of the ith observation. 
 * You are also given the two integers mean and n.
 * 
 * Return an array of length n containing the missing observations such that the average value of the n + m rolls 
 * is exactly mean. If there are multiple valid answers, return any of them. If no such array exists, return an empty array.
 * 
 * Example 1:
 * Input: rolls = [3,2,4,3], mean = 4, n = 2
 * Output: [6,6]
 * 
 * Example 2:
 * Input: rolls = [1,5,6], mean = 3, n = 4
 * Output: [2,3,2,2]
 * 
 * Example 3:
 * Input: rolls = [1,2,3,4], mean = 6, n = 4
 * Output: []
 * 
 * Constraints:
 * 1 <= n, m <= 10^5
 * 1 <= rolls[i], mean <= 6
 */

 import java.util.Arrays;

 public class FindMissingObservations {
 
     public int[] missingRolls(int[] rolls, int mean, int n) {
         int m = rolls.length;
         int sum = mean * (m + n);
         
         // Subtract the sum of observed rolls from the total sum
         for (int v : rolls) {
             sum -= v;
         }
         
         // If the sum is not achievable within the bounds, return an empty array
         if (sum < n || sum > n * 6) {
             return new int[0];
         }
         
         // Prepare the missing observations array
         int[] missing = new int[n];
         
         // Calculate the base value and the remainder
         int num = sum / n;
         int remaining = sum % n;
         
         // Distribute the base value and handle the remainder
         for (int i = 0; i < n; i++) {
             missing[i] = num;
             if (remaining > 0) {
                 missing[i]++;
                 remaining--;
             }
         }
         
         return missing;
     }
 
     public static void main(String[] args) {
         FindMissingObservations solution = new FindMissingObservations();
 
         // Test case 1
         int[] rolls1 = {3, 2, 4, 3};
         int mean1 = 4, n1 = 2;
         System.out.println("Output 1: " + Arrays.toString(solution.missingRolls(rolls1, mean1, n1))); // Expected: [6, 6]
 
         // Test case 2
         int[] rolls2 = {1, 5, 6};
         int mean2 = 3, n2 = 4;
         System.out.println("Output 2: " + Arrays.toString(solution.missingRolls(rolls2, mean2, n2))); // Expected: [2, 3, 2, 2]
 
         // Test case 3
         int[] rolls3 = {1, 2, 3, 4};
         int mean3 = 6, n3 = 4;
         System.out.println("Output 3: " + Arrays.toString(solution.missingRolls(rolls3, mean3, n3))); // Expected: []
     }
 }

 


/* 
Alternate Approach:

Instead of using two separate loops for base value and remainder handling,the distribution of the missing
sum can be done more succinctly in a single loop by leveraging the index of the loop.

for (int i = 0; i < n; i++) {
    missing[i] = num + (i < remaining ? 1 : 0);
}
return missing;
*/