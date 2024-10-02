/**
 * 2419. Longest Subarray With Maximum Bitwise AND
 * 
 * Problem:
 * Given an integer array `nums`, we need to find the longest subarray where the bitwise AND
 * of all elements is equal to the maximum possible bitwise AND of any subarray in the array.
 * 
 * The bitwise AND of a subarray can only be maximized if all the elements in that subarray are equal
 * to the maximum element in the array because any bitwise AND operation with smaller numbers will reduce the result.
 * 
 * Steps:
 * 1. Find the maximum element in the array.
 * 2. Find the longest contiguous subarray consisting of this maximum element.
 * 
 * Example 1:
 * Input: nums = [1, 2, 3, 3, 2, 2]
 * Output: 2
 * Explanation: 
 * The maximum possible bitwise AND is 3. The longest subarray with this value is [3, 3].
 * 
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: 1
 * Explanation:
 * The maximum possible bitwise AND is 4. The longest subarray with that value is [4].
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^6
 */
public class  LongestSubarrayWithMaximumBitwiseAND{
    /**
     * This method finds the length of the longest subarray with the maximum possible
     * bitwise AND value.
     * 
     * Time Complexity: O(n), where n is the length of the input array `nums`.
     * Space Complexity: O(1) (only constant extra space is used).
     * 
     * @param nums The input array of integers.
     * @return The length of the longest subarray with the maximum bitwise AND value.
     */
    public int longestSubarray(int[] nums) {
        // Step 1: Find the maximum value in the array
        int maxVal = nums[0];
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Step 2: Find the longest contiguous subarray where all elements are equal to maxVal
        int longest = 0;
        int currentLength = 0;
        
        for (int num : nums) {
            if (num == maxVal) {
                // If the current number is equal to the maximum value, increment the length
                currentLength++;
                longest = Math.max(longest, currentLength);
            } else {
                // If the current number is not equal to maxVal, reset the length counter
                currentLength = 0;
            }
        }
        
        // Step 3: Return the longest subarray length
        return longest;
    }

    // Main method for testing
    public static void main(String[] args) {
        LongestSubarrayWithMaximumBitwiseAND solution = new LongestSubarrayWithMaximumBitwiseAND();
        
        // Example 1
        int[] nums1 = {1, 2, 3, 3, 2, 2};
        System.out.println(solution.longestSubarray(nums1));  // Output: 2
        
        // Example 2
        int[] nums2 = {1, 2, 3, 4};
        System.out.println(solution.longestSubarray(nums2));  // Output: 1
        
        // Additional test case
        int[] nums3 = {5, 5, 5, 5, 5};
        System.out.println(solution.longestSubarray(nums3));  // Output: 5
    }
}

//========================================================================================================

/*
 
class Solution {
    public int longestSubarray(int[] nums) {
       int max = 0, count = 0, res = 0;
       for(int i : nums){
        if(i == max){
            count++;
        }else if(i > max){
            res = count = 1;
            max = i;
        }else{
            count = 0;
        }
        res = Math.max(res, count);
       } 
       return res;
    }
}

 */