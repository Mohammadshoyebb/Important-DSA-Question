/**
 * 487. Max Consecutive Ones II
 * 
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * 
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * 
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000.
 * 
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */
public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int maxConsecutiveOnes = 0;
        int zeroCount = 0;

        // Iterate over the array with the right pointer
        for (int right = 0; right < nums.length; right++) {
            // If the current element is 0, increment the zero count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If more than one zero is in the window, move the left pointer to reduce the window
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Calculate the length of the current valid window and update maxConsecutiveOnes
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, right - left + 1);
        }

        return maxConsecutiveOnes;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesII solution = new MaxConsecutiveOnesII();

        // Example 1
        int[] nums1 = {1, 0, 1, 1, 0};
        System.out.println("Example 1 Output: " + solution.findMaxConsecutiveOnes(nums1)); // Output: 4

        // Example 2
        int[] nums2 = {1, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        System.out.println("Example 2 Output: " + solution.findMaxConsecutiveOnes(nums2)); // Output: 7

        // Example 3
        int[] nums3 = {0, 0, 0, 0, 0, 0, 1};
        System.out.println("Example 3 Output: " + solution.findMaxConsecutiveOnes(nums3)); // Output: 1
    }
}

