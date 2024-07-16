/**
 * 912. Sort an Array
 * Given an array of integers nums, sort the array in ascending order and return it.
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 * 
 * Example 1:
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
 * 
 * Example 2:
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessarily unique.
 * 
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 */

 import java.util.Arrays;
 public class MergeSortArray {

    // Function to sort an array using merge sort
    public int[] sortArray(int[] nums) {
        // Call mergeSort on the entire array
        mergeSort(nums, 0, nums.length - 1);
        return nums; // Return the sorted array
    }

    // Recursive function to divide and sort the array
    private void mergeSort(int[] nums, int left, int right) {
        // Base case: if left is less than right, proceed with division
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;
            
            // Recursively sort the first half
            mergeSort(nums, left, mid);
            // Recursively sort the second half
            mergeSort(nums, mid + 1, right);
            
            // Merge the sorted halves
            merge(nums, left, mid, right);
        }
    }

    // Function to merge two sorted halves
    private void merge(int[] nums, int left, int mid, int right) {
        // Calculate the sizes of the two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays L[] and R[]
        System.arraycopy(nums, left, L, 0, n1);
        System.arraycopy(nums, mid + 1, R, 0, n2);

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        // Initial index of merged subarray array
        int k = left;

        // Merge the temporary arrays back into nums[l..r]
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i]; // If L[i] is smaller, add it to the result
                i++; // Move to the next element in L
            } else {
                nums[k] = R[j]; // If R[j] is smaller, add it to the result
                j++; // Move to the next element in R
            }
            k++; // Move to the next position in the merged array
        }

        // Copy the remaining elements of L[], if any
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }

        // Copy the remaining elements of R[], if any
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        MergeSortArray solution = new MergeSortArray();
        
        // Test cases
        int[] nums1 = {5, 2, 3, 1};
        int[] nums2 = {5, 1, 1, 2, 0, 0};
        int[] nums3 = {3, 2, 1, 4, 5, 6};
        int[] nums4 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] nums5 = {-1, -3, -2, -5, -4};

        // Print results
        System.out.println("Sorted array 1: " + Arrays.toString(solution.sortArray(nums1))); // Output: [1, 2, 3, 5]
        System.out.println("Sorted array 2: " + Arrays.toString(solution.sortArray(nums2))); // Output: [0, 0, 1, 1, 2, 5]
        System.out.println("Sorted array 3: " + Arrays.toString(solution.sortArray(nums3))); // Output: [1, 2, 3, 4, 5, 6]
        System.out.println("Sorted array 4: " + Arrays.toString(solution.sortArray(nums4))); // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println("Sorted array 5: " + Arrays.toString(solution.sortArray(nums5))); // Output: [-5, -4, -3, -2, -1]
    }
}

