/**
 * 169. Majority Element
 * Solved
 * Easy
 * 
 * Given an array nums of size n, return the majority element.
 * 
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * 
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * 
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * 
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * 
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */

 public class MajorityElementFinder {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        // Step 1: Find the candidate for majority element
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Since the problem guarantees a majority element, we return the candidate
        return candidate;
    }

    public static void main(String[] args) {
        MajorityElementFinder finder = new MajorityElementFinder();

        // Test cases
        int[] nums1 = {3, 2, 3};
        System.out.println(finder.majorityElement(nums1)); // Output: 3

        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(finder.majorityElement(nums2)); // Output: 2
    }
}

/*
 ========================  using only if statement (without if-else-if)  ==================================

 public int majorityElement(int[] nums) {
        int majority = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                // Choose a new candidate if the count is zero
                majority = nums[i];
                count = 1;
            } 
            if (nums[i] == majority) {
                // Increment the count if the current element matches the majority
                count++;
            } 
            if (nums[i] != majority && count > 0) {
                // Decrement the count if the current element does not match the majority
                count--;
            }
        }

        return majority;
    }
 */
