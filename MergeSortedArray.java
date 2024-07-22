/**
     * 88. Merge Sorted Array
     * Easy
     * 
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, 
     * representing the number of elements in nums1 and nums2 respectively.
     * 
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * 
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
     * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should 
     * be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     * 
     * Example 1:
     * 
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     * 
     * Example 2:
     * 
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     * 
     * Example 3:
     * 
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     * 
     * Constraints:
     * 
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -10^9 <= nums1[i], nums2[j] <= 10^9
     * 
     * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
     */

     public class MergeSortedArray {

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1; // Point to the end of the initialized part of nums1
            int j = n - 1; // Point to the end of nums2
            int k = m + n - 1; // Point to the end of the nums1 array

            // Merge in reverse order
            while (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }

            // If there are remaining elements in nums2
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }

        public static void main(String[] args) {
            MergeSortedArray merger = new MergeSortedArray();
            
            int[] nums1 = new int[6]; // Size of nums1 is 6
            int m = 3; // Number of initialized elements in nums1
            int[] nums2 = {2, 5, 6}; // Elements to merge into nums1
            int n = 3; // Number of elements in nums2

            // Initial values of nums1 (first m elements)
            nums1[0] = 1;
            nums1[1] = 2;
            nums1[2] = 3;
            nums1[3] = 0; // Placeholder for merging
            nums1[4] = 0; // Placeholder for merging
            nums1[5] = 0; // Placeholder for merging
            
            // Merge arrays
            merger.merge(nums1, m, nums2, n);

            // Print merged array
            for (int num : nums1) {
                System.out.print(num + " ");
            }
        }
    }