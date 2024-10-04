/**
 * 1590. Make Sum Divisible by P
 * 
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) 
 * such that the sum of the remaining elements is divisible by p. It is not allowed to 
 * remove the whole array.
 * 
 * Return the length of the smallest subarray that you need to remove, or -1 if it's 
 * impossible.
 * 
 * A subarray is defined as a contiguous block of elements in the array.
 * 
 * Example 1:
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. 
 * We can remove the subarray [4], and the sum of the remaining elements is 6, 
 * which is divisible by 6.
 * 
 * Example 2:
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best 
 * way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * 
 * Example 3:
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need 
 * to remove anything.
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * - 1 <= p <= 10^9
 */

 import java.util.HashMap;
 import java.util.Map;
 
 public class MakeSumDivisibleByP {
     
     public int minSubarray(int[] nums, int p) {
         long totalSum = 0;
         // Calculate total sum of the array
         for (int num : nums) {
             totalSum += num;
         }
         
         // We need to find the remainder of totalSum % p
         long remainder = totalSum % p;
         if (remainder == 0) {
             return 0; // Total sum is already divisible by p
         }
         
         // Map to store prefix sum remainders
         Map<Long, Integer> prefixRemainderMap = new HashMap<>();
         prefixRemainderMap.put(0L, -1); // Initialize the map to handle cases with prefix sum
         long currentPrefixSum = 0;
         int minLength = nums.length;
         
         for (int i = 0; i < nums.length; i++) {
             currentPrefixSum += nums[i];
             long currentRemainder = currentPrefixSum % p;
             
             // Calculate what we need to remove to make the sum divisible by p
             long target = (currentRemainder - remainder + p) % p;
             
             if (prefixRemainderMap.containsKey(target)) {
                 minLength = Math.min(minLength, i - prefixRemainderMap.get(target));
             }
             
             // Store current prefix remainder with its index
             prefixRemainderMap.put(currentRemainder, i);
         }
         
         return minLength == nums.length ? -1 : minLength;
     }
     
     // Main method to test the solution
     public static void main(String[] args) {
         MakeSumDivisibleByP solution = new MakeSumDivisibleByP();
         
         // Example 1
         int[] nums1 = {3, 1, 4, 2};
         int p1 = 6;
         System.out.println(solution.minSubarray(nums1, p1)); // Output: 1
         
         // Example 2
         int[] nums2 = {6, 3, 5, 2};
         int p2 = 9;
         System.out.println(solution.minSubarray(nums2, p2)); // Output: 2
         
         // Example 3
         int[] nums3 = {1, 2, 3};
         int p3 = 3;
         System.out.println(solution.minSubarray(nums3, p3)); // Output: 0
     }
 }
 