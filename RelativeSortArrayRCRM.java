/*
 
 Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in 
 arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.Elements 
that do not appear in arr2 should be placed at the end of arr1 in ascending order.

 

Example 1:



Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
Example 2:



Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
Output: [22,28,8,6,17,44]
 

Constraints:

1 <= arr1.length, arr2.length <= 1000

0 <= arr1[i], arr2[i] <= 1000

All the elements of arr2 are distinct.

Each arr2[i] is in arr1.

 */

import java.util.*;

public class RelativeSortArrayRCRM {
    
    public static void main(String[] args) {
        // Example 1
        int arr1[] = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int arr2[] = {2, 1, 4, 3, 9, 6};
        System.out.println("Example 1: ");
        System.out.println("Bucket Sort Approach: " + Arrays.toString(relativeSortArrayBucketSort(arr1.clone(), arr2)));
        System.out.println("HashMap + Custom Sort Approach: " + Arrays.toString(relativeSortArrayHashMap(arr1.clone(), arr2)));
        System.out.println("Frequency Count + Two Passes Approach: " + Arrays.toString(relativeSortArrayFreqCount(arr1.clone(), arr2)));
        
        // Example 2
        int arr3[] = {28, 6, 22, 8, 44, 17};
        int arr4[] = {22, 28, 8, 6};
        System.out.println("Example 2: ");
        System.out.println("Bucket Sort Approach: " + Arrays.toString(relativeSortArrayBucketSort(arr3.clone(), arr4)));
        System.out.println("HashMap + Custom Sort Approach: " + Arrays.toString(relativeSortArrayHashMap(arr3.clone(), arr4)));
        System.out.println("Frequency Count + Two Passes Approach: " + Arrays.toString(relativeSortArrayFreqCount(arr3.clone(), arr4)));
    
        // Example 3: Mixed range numbers
        int arr5[] = {50, 100, 150, 75, 200, 25, 125};
        int arr6[] = {100, 50, 200};
        System.out.println("Example 3: ");
        System.out.println("Bucket Sort Approach: " + Arrays.toString(relativeSortArrayBucketSort(arr5.clone(), arr6)));
        System.out.println("HashMap + Custom Sort Approach: " + Arrays.toString(relativeSortArrayHashMap(arr5.clone(), arr6)));
        System.out.println("Frequency Count + Two Passes Approach: " + Arrays.toString(relativeSortArrayFreqCount(arr5.clone(), arr6)));
    
        // Example 4: Edge case with only one element in arr2
        int arr7[] = {12, 7, 5, 9, 12, 5, 7};
        int arr8[] = {7};
        System.out.println("Example 4: ");
        System.out.println("Bucket Sort Approach: " + Arrays.toString(relativeSortArrayBucketSort(arr7.clone(), arr8)));
        System.out.println("HashMap + Custom Sort Approach: " + Arrays.toString(relativeSortArrayHashMap(arr7.clone(), arr8)));
        System.out.println("Frequency Count + Two Passes Approach: " + Arrays.toString(relativeSortArrayFreqCount(arr7.clone(), arr8)));
    }
    

    // 1. Bucket Sort Approach (Best for fixed range: 0 to 1000)
    // Time Complexity: O(n), Space Complexity: O(1) (due to fixed range array)
    public static int[] relativeSortArrayBucketSort(int[] arr1, int[] arr2) {
        // Frequency array for elements in the range 0 to 1000
        int[] freq = new int[1001]; 
        
        // Count occurrences of each number in arr1
        for (int num : arr1) {
            freq[num]++;
        }

        int index = 0;

        // Place elements in the order specified by arr2
        for (int num : arr2) {
            while (freq[num]-- > 0) {
                arr1[index++] = num;
            }
        }

        // Place remaining elements in sorted order (those not in arr2)
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                arr1[index++] = i;
            }
        }

        return arr1;
    }

    // 2. HashMap + Custom Sorting Approach
// Time Complexity: O(n log n), Space Complexity: O(n)
public static int[] relativeSortArrayHashMap(int[] arr1, int[] arr2) {
    // Create a HashMap to store the index/order of elements in arr2
    Map<Integer, Integer> orderMap = new HashMap<>();
    
    // Populate the order map with arr2 elements and their index positions
    for (int i = 0; i < arr2.length; i++) {
        orderMap.put(arr2[i], i);
    }

    // Convert primitive int[] to Integer[] for custom sorting
    Integer[] arr1Integer = Arrays.stream(arr1).boxed().toArray(Integer[]::new);

    // Custom comparator to sort arr1 based on arr2 order and then naturally
    Arrays.sort(arr1Integer, (a, b) -> {
        if (orderMap.containsKey(a) && orderMap.containsKey(b)) {
            // If both elements are in arr2, sort based on their order in arr2
            return orderMap.get(a) - orderMap.get(b);
        } else if (orderMap.containsKey(a)) {
            // If only 'a' is in arr2, 'a' should come first
            return -1;
        } else if (orderMap.containsKey(b)) {
            // If only 'b' is in arr2, 'b' should come first
            return 1;
        } else {
            // If neither are in arr2, sort them in natural order (ascending)
            return a - b;
        }
    });

    // Convert the sorted Integer[] back to int[]
    return Arrays.stream(arr1Integer).mapToInt(Integer::intValue).toArray();
}

    // 3. Frequency Count + Two Passes Approach
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public static int[] relativeSortArrayFreqCount(int[] arr1, int[] arr2) {
        // Map to store the frequency of elements in arr1
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        // Count the frequency of each element in arr1
        for (int num : arr1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // List to store the result in the desired order
        List<Integer> result = new ArrayList<>();
        
        // Add elements from arr2 in the correct order and frequency
        for (int num : arr2) {
            if (freqMap.containsKey(num)) {
                int count = freqMap.get(num);
                for (int i = 0; i < count; i++) {
                    result.add(num);
                }
                freqMap.remove(num); // Remove after adding to result
            }
        }

        // Add remaining elements (not in arr2) in natural ascending order
        List<Integer> remaining = new ArrayList<>(freqMap.keySet());
        Collections.sort(remaining); // Sort remaining elements
        for (int num : remaining) {
            int count = freqMap.get(num);
            for (int i = 0; i < count; i++) {
                result.add(num);
            }
        }

        // Convert result list back to an array
        return result.stream().mapToInt(i -> i).toArray();
    }
}
