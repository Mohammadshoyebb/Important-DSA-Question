/*
    632. Smallest Range Covering Elements from K Lists

    You have k lists of sorted integers in non-decreasing order. 
    Find the smallest range that includes at least one number from each of the k lists.

    We define the range [a, b] is smaller than range [c, d] if:
    - b - a < d - c, or 
    - a < c if b - a == d - c.

    Example 1:
    Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
    Output: [20,24]
    Explanation: 
    List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
    List 2: [0, 9, 12, 20], 20 is in range [20,24].
    List 3: [5, 18, 22, 30], 22 is in range [20,24].

    Example 2:
    Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
    Output: [1,1]

    Constraints:
    - nums.length == k
    - 1 <= k <= 3500
    - 1 <= nums[i].length <= 50
    - -10^5 <= nums[i][j] <= 10^5
    - nums[i] is sorted in non-decreasing order.
*/

import java.util.*;

public class SmallestRangeKLists {
    
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();

        /*
        ============================same as comparator===============================

            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
         */

        
         // PriorityQueue (min-heap) to store the smallest element from each list
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // Comparator to sort elements by their value (first element in the int[] array)
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];  // Compare based on the value of the number (a[0] and b[0])
            }
        });
        
        int max = Integer.MIN_VALUE; // To track the maximum value in the current range

        // Add the first element from each list into the priority queue
        for (int i = 0; i < k; i++) {
            int minVal = nums.get(i).get(0); // Get the first element from the i-th list
            pq.offer(new int[]{minVal, i, 0}); // Add the element to the heap {value, list index, index in the list}
            max = Math.max(minVal, max); // Update the max value to the maximum of the first elements
        }

        // Initialize the result range with a large value
        int[] minRange = {0, Integer.MAX_VALUE}; // The minimum range that will store [start, end]

        // Process the priority queue (min-heap)
        while (true) {
            int[] top = pq.poll(); // Get the smallest element from the heap

            int minElement = top[0]; // The value of the smallest element
            int listIndex = top[1];  // The index of the list the element came from
            int elementIndex = top[2]; // The index of the element in its respective list

            // Update the smallest range if the current range is smaller
            if ((max - minElement) < (minRange[1] - minRange[0])) {
                minRange[0] = minElement; // Update the start of the range
                minRange[1] = max; // Update the end of the range
            }

            // Check if we have reached the end of the current list
            if (elementIndex == nums.get(listIndex).size() - 1) {
                // If we have processed all elements in one of the lists, break the loop
                // because we can't form a valid range anymore
                break;
            }

            // Get the next element from the same list
            int next = nums.get(listIndex).get(elementIndex + 1);
            
            // Update max with the new element if it is larger
            max = Math.max(max, next);
            
            // Add the next element from the same list into the priority queue
            pq.offer(new int[]{next, listIndex, elementIndex + 1});
        }

        // Return the smallest range found
        return minRange;

    }

    public static void main(String[] args) {
        SmallestRangeKLists solution = new SmallestRangeKLists();

        // Test case 1
        List<List<Integer>> nums1 = Arrays.asList(
            Arrays.asList(4, 10, 15, 24, 26),
            Arrays.asList(0, 9, 12, 20),
            Arrays.asList(5, 18, 22, 30)
        );
        System.out.println("Smallest Range: " + Arrays.toString(solution.smallestRange(nums1))); // Output: [20, 24]

        // Test case 2
        List<List<Integer>> nums2 = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(1, 2, 3),
            Arrays.asList(1, 2, 3)
        );
        System.out.println("Smallest Range: " + Arrays.toString(solution.smallestRange(nums2))); // Output: [1, 1]
    }
}
