/**
 * 135. Candy
 * 
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * 
 * You are giving candies to these children subjected to the following requirements:
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * 
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * 
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * 
 * Constraints:
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 * 
 */

 import java.util.Arrays;

 public class Candy {
     // Approach 1: Brute Force
     public int candyBruteForce(int[] ratings) {
         int n = ratings.length;
         int[] resLeft = new int[n];
         int[] resRight = new int[n];
         int sum = 0;
         resLeft[0] = 1;
         resRight[n - 1] = 1;
 
         // Left to right
         for (int i = 1; i < n; i++) {
             if (ratings[i] > ratings[i - 1]) {
                 resLeft[i] = resLeft[i - 1] + 1;
             } else {
                 resLeft[i] = 1;
             }
         }
 
         // Right to left
         for (int i = n - 2; i >= 0; i--) {
             if (ratings[i] > ratings[i + 1]) {
                 resRight[i] = resRight[i + 1] + 1;
             } else {
                 resRight[i] = 1;
             }
         }
 
         // Sum max of both traversals
         for (int i = 0; i < n; i++) {
             sum += Math.max(resLeft[i], resRight[i]);
         }
 
         return sum;
     }
 
     // Approach 2: Better
     public int candyBetter(int[] ratings) {
         int n = ratings.length;
         int[] resLeft = new int[n];
         int sum = 0;
         resLeft[0] = 1;
 
         // Left to right
         for (int i = 1; i < n; i++) {
             if (ratings[i] > ratings[i - 1]) {
                 resLeft[i] = resLeft[i - 1] + 1;
             } else {
                 resLeft[i] = 1;
             }
         }
 
         // Right to left
         int current = 1;
         sum += Math.max(resLeft[n - 1], current);
         for (int i = n - 2; i >= 0; i--) {
             if (ratings[i] > ratings[i + 1]) {
                 current++;
             } else {
                 current = 1;
             }
             sum += Math.max(resLeft[i], current);
         }
 
         return sum;
     }
 
     // Approach 3: Optimal
     public int candyOptimal(int[] ratings) {
         int n = ratings.length; // Total number of children
         int increasingCount = 0; // Counter for increasing ratings
         int decreasingCount = 0; // Counter for decreasing ratings
         int peakCandy = 0; // Number of candies at the peak (highest point in the current iteration)
         int totalCandies = 1; // Start with one candy for the first child
 
         // Iterate through each child starting from the second one
         for (int i = 1; i < n; i++) {
             if (ratings[i - 1] < ratings[i]) {
                 // Rating is higher than the previous child's rating
                 increasingCount++;
                 peakCandy = increasingCount + 1; // The current child gets one more candy than the increasing count
                 decreasingCount = 0; // Reset decreasing count
                 totalCandies += peakCandy; // Update total candies with the current child's candies
             } else if (ratings[i] == ratings[i - 1]) {
                 // Rating is equal to the previous child's rating
                 // Reset the peak, increasing count, and decreasing count
                 peakCandy = 0;
                 increasingCount = 0;
                 decreasingCount = 0;
                 totalCandies++; // Each child gets at least one candy
             } else {
                 // Rating is lower than the previous child's rating
                 decreasingCount++;
                 increasingCount = 0; // Reset increasing count
                 // Add the number of candies for decreasing sequence.
                 // If peak candy count is not enough to cover the decreasing sequence, an extra candy is given to the peak
                 totalCandies += decreasingCount + (peakCandy > decreasingCount ? 0 : 1);
             }
         }
 
         // Return the computed total number of candies needed
         return totalCandies;
     }
 
     public static void main(String[] args) {
         Candy candy = new Candy();
         
         int[] ratings1 = {1, 0, 2};
         System.out.println("Brute Force Result: " + candy.candyBruteForce(ratings1));
         System.out.println("Better Result: " + candy.candyBetter(ratings1));
         System.out.println("Optimal Result: " + candy.candyOptimal(ratings1));
         
         int[] ratings2 = {1, 2, 2};
         System.out.println("Brute Force Result: " + candy.candyBruteForce(ratings2));
         System.out.println("Better Result: " + candy.candyBetter(ratings2));
         System.out.println("Optimal Result: " + candy.candyOptimal(ratings2));
     }
 }
 