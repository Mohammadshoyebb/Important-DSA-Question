/**
 * 1395. Count Number of Teams
 * 
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 * 
 * You have to form a team of 3 soldiers amongst them under the following rules:
 * 
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) 
 * where (0 <= i < j < k < n).
 * 
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 * 
 * Example 1:
 * Input: rating = [2, 5, 3, 4, 1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2, 3, 4), (5, 4, 1), (5, 3, 1).
 * 
 * Example 2:
 * Input: rating = [2, 1, 3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 * 
 * Example 3:
 * Input: rating = [1, 2, 3, 4]
 * Output: 4
 * 
 * Constraints:
 * n == rating.length
 * 3 <= n <= 1000
 * 1 <= rating[i] <= 105
 * All the integers in rating are unique.
 */

 import java.util.*;

 public class CountNumberOfTeams {
     // Method to count the number of valid teams
     public int numTeams(int[] nums) {
         int n = nums.length;
         int count = 0;
 
         // Iterate over each element as a potential middle element
         for (int mid = 1; mid < n - 1; mid++) {
             // Counting in increasing order
             int leftSmaller = 0;
             for (int i = 0; i < mid; i++) {
                 if (nums[i] < nums[mid]) {
                     leftSmaller++;
                 }
             }
             int rightGreater = 0;
             for (int i = mid + 1; i < n; i++) {
                 if (nums[i] > nums[mid]) {
                     rightGreater++;
                 }
             }
             count += leftSmaller * rightGreater;
 
             // Counting in decreasing order
             int leftGreater = mid - leftSmaller;
             int rightSmaller = n - mid - 1 - rightGreater;
             count += leftGreater * rightSmaller;
         }
 
         return count;
     }
 
     // Main method for testing
     public static void main(String[] args) {
        CountNumberOfTeams solution = new CountNumberOfTeams();
 
         int[] rating1 = {2, 5, 3, 4, 1};
         System.out.println("Number of teams for rating1: " + solution.numTeams(rating1)); // Output: 3
 
         int[] rating2 = {2, 1, 3};
         System.out.println("Number of teams for rating2: " + solution.numTeams(rating2)); // Output: 0
 
         int[] rating3 = {1, 2, 3, 4};
         System.out.println("Number of teams for rating3: " + solution.numTeams(rating3)); // Output: 4
     }
 }
 
