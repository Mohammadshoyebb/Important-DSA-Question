/**
 * 1331. Rank Transform of an Array
 * 
 * Given an array of integers arr, replace each element with its rank.
 * 
 * The rank represents how large the element is. The rank has the following rules:
 * - Rank is an integer starting from 1.
 * - The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * - Rank should be as small as possible.
 * 
 * Example 1:
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * 
 * Example 2:
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * 
 * Example 3:
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 * 
 * Constraints:
 * - 0 <= arr.length <= 10^5
 * - -10^9 <= arr[i] <= 10^9
 */

 import java.util.*;

 public class RankTransformArray {
     // Method to get rank transform of the array
     public int[] arrayRankTransform(int[] arr) {
         // Copy the original array
         int[] sortedArr = arr.clone();
         // Sort the copied array
         Arrays.sort(sortedArr);
         
         // Map to store the rank of each element
         Map<Integer, Integer> rankMap = new HashMap<>();
         int rank = 1;
         
         // Assign ranks while skipping duplicates
         for (int num : sortedArr) {
             if (!rankMap.containsKey(num)) {
                 rankMap.put(num, rank++);
             }
         }
         
         // Create the result array
         int[] result = new int[arr.length];
         for (int i = 0; i < arr.length; i++) {
             result[i] = rankMap.get(arr[i]);
         }
         
         return result;
     }
     
     // Main method to test the solution
     public static void main(String[] args) {
         RankTransformArray solution = new RankTransformArray();
         
         // Example 1
         int[] arr1 = {40, 10, 20, 30};
         System.out.println(Arrays.toString(solution.arrayRankTransform(arr1))); // Output: [4, 1, 2, 3]
         
         // Example 2
         int[] arr2 = {100, 100, 100};
         System.out.println(Arrays.toString(solution.arrayRankTransform(arr2))); // Output: [1, 1, 1]
         
         // Example 3
         int[] arr3 = {37, 12, 28, 9, 100, 56, 80, 5, 12};
         System.out.println(Arrays.toString(solution.arrayRankTransform(arr3))); // Output: [5, 3, 4, 2, 8, 6, 7, 1, 3]
     }
 }
 