/*
 347. Top K Frequent Elements
Solved
Medium
Topics
Companies
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */



import java.util.*;

public class TopKFrequentElements {
    
    // Method 1: Using Bucket Sort
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // Create a frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // Create an array of lists to act as buckets
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // Collect the top k frequent elements
        int[] res = new int[k];
        int counter = 0;
        for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {
            if (bucket[pos] != null) {
                for (int num : bucket[pos]) {
                    if (counter < k) {
                        res[counter++] = num;
                    }
                }
            }
        }

        return res;
    }

    // Method 2: Using Max Heap
    public int[] topKFrequentMaxHeap(int[] nums, int k) {
        // Create a frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // Create a max heap (priority queue) based on frequencies
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );

        // Add entries to the max heap
        maxHeap.addAll(frequencyMap.entrySet());

        // Collect the top k frequent elements
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll().getKey();
        }

        return res;
    }

    // Method 3: Using Min Heap
    public int[] topKFrequentMinHeap(int[] nums, int k) {
        // Create a frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // Create a min heap (priority queue) based on frequencies
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(Map.Entry::getValue)
        );

        // Add entries to the min heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Collect the top k frequent elements
        int[] res = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.poll().getKey();
        }

        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[] nums = {1, -1, 1, -2, -2, -2, 3};
        int k = 2;

        System.out.println("Bucket Sort Method: " + Arrays.toString(solution.topKFrequentBucketSort(nums, k))); // Output: [-2, 1]
        System.out.println("Max Heap Method: " + Arrays.toString(solution.topKFrequentMaxHeap(nums, k))); // Output: [-2, 1]
        System.out.println("Min Heap Method: " + Arrays.toString(solution.topKFrequentMinHeap(nums, k))); // Output: [-2, 1]
    }
}
