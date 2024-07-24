import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * Medium
 * 
 * Given an integer array nums, return the length of the longest strictly increasing 
 * subsequence.
 * 
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * 
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * 
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * 
 * Constraints:
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * 
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */

public class LongestIncreasingSubsequence {
    // Approach 1: O(n log n) using binary search
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        // dp will store the smallest end element for increasing subsequences of various lengths
        int[] dp = new int[n];
        int len = 0;

        for (int num : nums) {
            // Use binary search to find the position of num in dp array
            int i = Arrays.binarySearch(dp, 0, len, num);

            // If num is not found, binarySearch returns (-(insertion point) - 1)
            // Convert it back to the insertion point
            if (i < 0) {
                i = -i - 1; // Simplified form of -(i + 1)
            }

            // Update dp array with the current number at the found position
            dp[i] = num;

            // If num extends the longest subsequence found so far, increase len
            if (i == len) {
                len++;
            }
        }

        return len;
    }

    // Approach 2: O(n^2) using dynamic programming
    public int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dpArray = new int[n];
        Arrays.fill(dpArray, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dpArray[i] = Math.max(dpArray[i], dpArray[j] + 1);
                }
            }
        }

        int max = 0;
        for (int num : dpArray) {
            max = Math.max(max, num);
        }

        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();

        // Example 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Example 1 (O(n log n)): " + solution.lengthOfLIS(nums1)); // Output: 4
        System.out.println("Example 1 (O(n^2)): " + solution.lengthOfLIS_DP(nums1)); // Output: 4

        // Example 2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Example 2 (O(n log n)): " + solution.lengthOfLIS(nums2)); // Output: 4
        System.out.println("Example 2 (O(n^2)): " + solution.lengthOfLIS_DP(nums2)); // Output: 4

        // Example 3
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Example 3 (O(n log n)): " + solution.lengthOfLIS(nums3)); // Output: 1
        System.out.println("Example 3 (O(n^2)): " + solution.lengthOfLIS_DP(nums3)); // Output: 1
    }
}

