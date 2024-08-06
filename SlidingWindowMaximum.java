/**
 * 239. Sliding Window Maximum
 * 
 * You are given an array of integers nums, there is a sliding window of size k 
 * which is moving from the very left of the array to the very right. You can 
 * only see the k numbers in the window. Each time the sliding window moves 
 * right by one position.
 * 
 * Return the max sliding window.
 * 
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation: 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */

 import java.util.ArrayDeque;
 import java.util.Arrays;

 public class SlidingWindowMaximum {
     public static int[] maxSlidingWindow(int[] nums, int k) {
         int n = nums.length;
         ArrayDeque<Integer> dq = new ArrayDeque<>();
         int[] result = new int[n - k + 1];
         int index = 0;
 
         for (int i = 0; i < n; i++) {
             // Remove elements from the back of the deque that are smaller than the current element
             while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                 dq.pollLast();
             }
             // Add the current element index to the deque
             dq.offerLast(i);
 
             // Remove the front element if it's outside the window
             if (dq.peekFirst() + k <= i) {
                 dq.pollFirst();
             }
 
             // Add the maximum element of the current window to the result
             if (i >= k - 1) {
                 result[index++] = nums[dq.peekFirst()];
             }
         }
         return result;
     }
 
     public static void main(String[] args) {
         int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
         int k1 = 3;
         System.out.println(Arrays.toString(maxSlidingWindow(nums1, k1))); // [3, 3, 5, 5, 6, 7]
 
         int[] nums2 = {1};
         int k2 = 1;
         System.out.println(Arrays.toString(maxSlidingWindow(nums2, k2))); // [1]
     }
 }
 
