/**
 * Problem: 1539. Kth Missing Positive Number
 * 
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * 
 * Return the kth positive integer that is missing from this array.
 * 
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * 
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 * 
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 */

 public class FindKthMissingPositiveNumber {

    // O(N) Solution
    public int findKthPositiveO_N(int[] arr, int k) {
        int i = 0;
        // Traverse through the array
        for (i = 0; i < arr.length; i++) {
            // Check if the current element is not equal to i+1
            if (arr[i] != i + 1) {
                // If the difference between the current element and i+1 is greater or equal to k, break the loop
                if (arr[i] - (i + 1) >= k) {
                    break;
                }
            }
        }
        // Return the kth missing positive integer
        return i + k;
    }

    // O(log N) Solution
    public int findKthPositiveO_log_N(int[] arr, int k) {
        int low = 0;
        int high = arr.length;
        // Binary search for the kth missing positive number
        while (low < high) {
            int mid = low + (high - low) / 2;
            // Check if the number of missing numbers up to mid is less than k
            if (arr[mid] - (mid - 1) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        // Return the kth missing positive integer
        return low + k;
    }

    public static void main(String[] args) {
        FindKthMissingPositiveNumber solution = new FindKthMissingPositiveNumber();

        // Test cases
        int[] arr1 = {2, 3, 4, 7, 11};
        int k1 = 5;
        System.out.println("O(N) Solution Output: " + solution.findKthPositiveO_N(arr1, k1)); // Output: 9
        System.out.println("O(log N) Solution Output: " + solution.findKthPositiveO_log_N(arr1, k1)); // Output: 9

        int[] arr2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println("O(N) Solution Output: " + solution.findKthPositiveO_N(arr2, k2)); // Output: 6
        System.out.println("O(log N) Solution Output: " + solution.findKthPositiveO_log_N(arr2, k2)); // Output: 6

        // Edge cases
        int[] arr3 = {2};
        int k3 = 1;
        System.out.println("O(N) Solution Output: " + solution.findKthPositiveO_N(arr3, k3)); // Output: 1
        System.out.println("O(log N) Solution Output: " + solution.findKthPositiveO_log_N(arr3, k3)); // Output: 1

        int[] arr4 = {1, 2, 3, 4, 5};
        int k4 = 3;
        System.out.println("O(N) Solution Output: " + solution.findKthPositiveO_N(arr4, k4)); // Output: 8
        System.out.println("O(log N) Solution Output: " + solution.findKthPositiveO_log_N(arr4, k4)); // Output: 8
    }
}
