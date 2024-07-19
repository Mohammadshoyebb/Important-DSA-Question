/**
 * 485. Max Consecutive Ones
 * Solved
 * Easy
 * Topics
 * Companies
 * 
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 * Example 1:
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 *
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */

 public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0; // To keep track of the maximum number of consecutive 1's
        int currentCount = 0; // To count the current streak of consecutive 1's

        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                maxCount = Math.max(maxCount, currentCount);
                currentCount = 0; // Reset the current count when encountering a 0
            }
        }
        
        // Final check in case the array ends with the longest streak of 1's
        maxCount = Math.max(maxCount, currentCount);

        return maxCount;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes solution = new MaxConsecutiveOnes();
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 1, 0, 1};
        System.out.println(solution.findMaxConsecutiveOnes(nums1)); // Output: 3
        System.out.println(solution.findMaxConsecutiveOnes(nums2)); // Output: 2
    }
}

/*
 * class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int ans = -1;
        int window = 0;

        for(int right=0;right<nums.length; right++){
            window += nums[right];

            while(right-left + 1 != window){
                window -= nums[left];
                left++;
            }
            ans = Math.max(ans, right-left+1);
        }
        return ans;
    }
}
 */