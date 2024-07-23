/**
 * 46. Permutations
 * Given an array nums of distinct integers, return all the possible permutations. 
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * 
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 * 
 * Constraints:
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */

 import java.util.ArrayList;
 import java.util.List;
 
 public class Permutation {
     // Main method to test the permutations solution
     public static void main(String[] args) {
         Permutation solution = new Permutation();
 
         // Example 1
         int[] nums1 = {1, 2, 3};
         List<List<Integer>> permutations1 = solution.permute(nums1);
         System.out.println("Example 1 Permutations:");
         for (List<Integer> permutation : permutations1) {
             System.out.println(permutation);
         }
 
         // Example 2
         int[] nums2 = {0, 1};
         List<List<Integer>> permutations2 = solution.permute(nums2);
         System.out.println("Example 2 Permutations:");
         for (List<Integer> permutation : permutations2) {
             System.out.println(permutation);
         }
 
         // Example 3
         int[] nums3 = {1};
         List<List<Integer>> permutations3 = solution.permute(nums3);
         System.out.println("Example 3 Permutations:");
         for (List<Integer> permutation : permutations3) {
             System.out.println(permutation);
         }
     }
 
     // Method to return all possible permutations of the input array
     public List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> resultList = new ArrayList<>();
         List<Integer> tempAns = new ArrayList<>();
         backtrack(resultList, tempAns, nums);
         return resultList;
     }
 
     // Helper method for backtracking to find permutations
     public void backtrack(List<List<Integer>> resultList, List<Integer> tempAns, int[] nums) {
         // Base case: if the temporary list size matches the input array length, add it to the result list
         if (tempAns.size() == nums.length) {
             resultList.add(new ArrayList<>(tempAns));
             return;
         }
         // Iterate over each number in the input array
         for (int number : nums) {
             // Skip the number if it's already in the temporary list
             if (tempAns.contains(number)) {
                 continue;
             }
             // Add the number to the temporary list
             tempAns.add(number);
             // Recursively call backtrack to continue building the permutation
             backtrack(resultList, tempAns, nums);
             // Remove the last added number to backtrack
             tempAns.remove(tempAns.size() - 1);
         }
     }
 }
 