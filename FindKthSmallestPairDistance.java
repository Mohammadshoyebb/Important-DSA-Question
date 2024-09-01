/*
 *719. Find K-th Smallest Pair Distance
Solved
Hard
Topics
Companies
Hint
The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

 

Example 1:

Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Example 2:

Input: nums = [1,1,1], k = 2
Output: 0
Example 3:

Input: nums = [1,6,1], k = 3
Output: 5
 

Constraints:

n == nums.length
2 <= n <= 104
0 <= nums[i] <= 106
1 <= k <= n * (n - 1) / 2
 */
import java.util.Arrays;

public class FindKthSmallestPairDistance {

    // Method to find the k-th smallest pair distance
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];

        // Binary search to find the k-th smallest distance
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countPairs(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // Helper method to count pairs with distance <= mid
    private int countPairs(int[] nums, int mid) {
        int count = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= mid) {
                j++;
            }
            count += j - i - 1;
        }
        return count;
    }

    // Main method to test the class
    public static void main(String[] args) {
        FindKthSmallestPairDistance solution = new FindKthSmallestPairDistance();
        int[] nums1 = {1, 3, 1};
        int k1 = 1;
        System.out.println(solution.smallestDistancePair(nums1, k1));  // Output: 0

        int[] nums2 = {1, 1, 1};
        int k2 = 2;
        System.out.println(solution.smallestDistancePair(nums2, k2));  // Output: 0

        int[] nums3 = {1, 6, 1};
        int k3 = 3;
        System.out.println(solution.smallestDistancePair(nums3, k3));  // Output: 5
    }
}
