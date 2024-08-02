// 16. 3Sum Closest
// Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
// Return the sum of the three integers.
// You may assume that each input would have exactly one solution.

// Example 1:
// Input: nums = [-1,2,1,-4], target = 1
// Output: 2
// Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

// Example 2:
// Input: nums = [0,0,0], target = 1
// Output: 0
// Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

// Constraints:
// 3 <= nums.length <= 500
// -1000 <= nums[i] <= 1000
// -104 <= target <= 104

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // Sort the array to use the two-pointer technique
        Arrays.sort(nums);
        // Initialize resultSum with the sum of the first three elements
        int resultSum = nums[0] + nums[1] + nums[2];
        // Initialize minDiff to a large value
        int minDiff = Integer.MAX_VALUE;

        // Iterate through each element, treating it as the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            // Use two-pointer technique to find the closest sum
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;  // If the sum is exactly the target, return it
                }

                if (sum > target) {
                    right--;  // Decrease the right pointer to get a smaller sum
                } else {
                    left++;  // Increase the left pointer to get a larger sum
                }

                // Calculate the absolute difference between the current sum and the target
                int difference = Math.abs(sum - target);
                // If the current difference is smaller than the minimum difference, update resultSum and minDiff
                if (difference < minDiff) {
                    resultSum = sum;
                    minDiff = difference;
                }
            }
        }
        return resultSum;
    }

    public static void main(String[] args) {
        ThreeSumClosest solver = new ThreeSumClosest();
        
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Closest sum to target " + target1 + " is: " + solver.threeSumClosest(nums1, target1)); // Output: 2
        
        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Closest sum to target " + target2 + " is: " + solver.threeSumClosest(nums2, target2)); // Output: 0
    }
}


