// 1838. Frequency of the Most Frequent Element
// Solved
// Medium
// Topics
// Companies
// Hint
// The frequency of an element is the number of times it occurs in an array.
// You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
// Return the maximum possible frequency of an element after performing at most k operations.

// Example 1:
// Input: nums = [1,2,4], k = 5
// Output: 3
// Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4]. 4 has a frequency of 3.

// Example 2:
// Input: nums = [1,4,8,13], k = 5
// Output: 2
// Explanation: There are multiple optimal solutions:
// - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
// - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
// - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.

// Example 3:
// Input: nums = [3,9,6], k = 2
// Output: 1

// Constraints:
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105
// 1 <= k <= 105

import java.util.Arrays;

public class FrequencyOfMostFrequentElement {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array

        int left = 0; // Left pointer for the sliding window
        long sum = 0; // Variable to store the sum of elements in the current window
        int maxFrequency = 0; // Variable to store the maximum frequency

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // Add the current element to the sum

            // Check if the current window is valid
            while (nums[right] * (right - left + 1L) > sum + k) {
                sum -= nums[left]; // Remove the leftmost element from the sum
                left++; // Move the left pointer to the right
            }

            // Update the maximum frequency
            maxFrequency = Math.max(maxFrequency, right - left + 1);
        }

        return maxFrequency;
    }

    public static void main(String[] args) {
        FrequencyOfMostFrequentElement solution = new FrequencyOfMostFrequentElement();
        int[] nums1 = {1, 2, 4};
        int k1 = 5;
        System.out.println(solution.maxFrequency(nums1, k1)); // Output: 3

        int[] nums2 = {1, 4, 8, 13};
        int k2 = 5;
        System.out.println(solution.maxFrequency(nums2, k2)); // Output: 2

        int[] nums3 = {3, 9, 6};
        int k3 = 2;
        System.out.println(solution.maxFrequency(nums3, k3)); // Output: 1
    }
}
