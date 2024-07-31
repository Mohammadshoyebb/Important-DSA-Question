/**
 * 15. 3Sum
 * 
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that 
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation: 
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * 
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * 
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * 
 * Constraints:
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */

 import java.util.*;

 public class ThreeSum {
     // Function to find all unique triplets in the array which gives the sum of zero
     public List<List<Integer>> threeSum(int[] nums) {
         List<List<Integer>> result = new ArrayList<>();
 
         if (nums == null || nums.length < 3) {
             return result;  // If array is null or has less than 3 elements, return empty list
         }
 
         Arrays.sort(nums);  // Sort the array to use two-pointer technique
 
         for (int i = 0; i < nums.length - 2; i++) {
             if (i > 0 && nums[i] == nums[i - 1]) {
                 continue;  // Skip the same element to avoid duplicate triplets
             }
 
             int left = i + 1;
             int right = nums.length - 1;
 
             while (left < right) {
                 int sum = nums[i] + nums[left] + nums[right];
 
                 if (sum == 0) {
                     result.add(Arrays.asList(nums[i], nums[left], nums[right]));
 
                     // Skip the same elements to avoid duplicate triplets
                     while (left < right && nums[left] == nums[left + 1]) {
                         left++;
                     }
                     while (left < right && nums[right] == nums[right - 1]) {
                         right--;
                     }
 
                     left++;
                     right--;
                 } else if (sum < 0) {
                     left++;  // Move the left pointer to the right to increase the sum
                 } else {
                     right--;  // Move the right pointer to the left to decrease the sum
                 }
             }
         }
 
         return result;
     }
 
     // Main method to test the threeSum function
     public static void main(String[] args) {
         ThreeSum solution = new ThreeSum();
 
         // Example 1
         int[] nums1 = {-1, 0, 1, 2, -1, -4};
         List<List<Integer>> result1 = solution.threeSum(nums1);
         System.out.println(result1);  // Output: [[-1, -1, 2], [-1, 0, 1]]
 
         // Example 2
         int[] nums2 = {0, 1, 1};
         List<List<Integer>> result2 = solution.threeSum(nums2);
         System.out.println(result2);  // Output: []
 
         // Example 3
         int[] nums3 = {0, 0, 0};
         List<List<Integer>> result3 = solution.threeSum(nums3);
         System.out.println(result3);  // Output: [[0, 0, 0]]
     }
 }
 