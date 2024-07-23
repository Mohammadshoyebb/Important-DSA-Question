/*
1636. Sort Array by Increasing Frequency
Solved
Easy
Topics
Companies
Hint
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.

Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.

Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]

Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SortByFrequency {

    // Approach 1
    public int[] frequencySortApproach1(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        Integer frequency[] = new Integer[n];
        for (int i = 0; i < n; i++) {
            frequency[i] = nums[i];
        }

        Arrays.sort(frequency, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (map.get(a) == map.get(b)) {
                    return b - a;
                } else {
                    return map.get(a) - map.get(b);
                }
            }
        });

        for (int i = 0; i < n; i++) {
            nums[i] = frequency[i];
        }
        return nums;
    }

    // Approach 2
    public int[] frequencySortApproach2(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        Integer frequency[] = new Integer[map.size()];
        int i = 0;
        for (Integer key : map.keySet()) {
            frequency[i++] = key;
        }

        Arrays.sort(frequency, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (map.get(a) == map.get(b)) {
                    return b - a;
                }
                return map.get(a) - map.get(b);
            }
        });
        i = 0;
        for (int key : frequency) {
            int f = map.get(key);
            for (int j = 0; j < f; j++) {
                nums[i++] = key;
            }
        }
        return nums;
    }

    // Approach 3
    public int[] frequencySortApproach3(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (map.get(a) == map.get(b)) {
                    return b - a;
                }
                return map.get(a) - map.get(b);
            }
        });

        for (int ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        for (Integer key : map.keySet()) {
            pq.offer(key);
        }
        int i = 0;
        while (!pq.isEmpty()) {
            int key = pq.poll();
            int f = map.get(key);
            for (int j = 0; j < f; j++) {
                nums[i++] = key;
            }
        }
        return nums;
    }

    // Main method to test the approaches
    public static void main(String[] args) {
        SortByFrequency solution = new SortByFrequency();

        // Example 1
        int[] nums1 = {1, 1, 2, 2, 2, 3};
        System.out.println("Example 1 Approach 1: " + Arrays.toString(solution.frequencySortApproach1(nums1.clone())));
        System.out.println("Example 1 Approach 2: " + Arrays.toString(solution.frequencySortApproach2(nums1.clone())));
        System.out.println("Example 1 Approach 3: " + Arrays.toString(solution.frequencySortApproach3(nums1.clone())));

        // Example 2
        int[] nums2 = {2, 3, 1, 3, 2};
        System.out.println("Example 2 Approach 1: " + Arrays.toString(solution.frequencySortApproach1(nums2.clone())));
        System.out.println("Example 2 Approach 2: " + Arrays.toString(solution.frequencySortApproach2(nums2.clone())));
        System.out.println("Example 2 Approach 3: " + Arrays.toString(solution.frequencySortApproach3(nums2.clone())));

        // Example 3
        int[] nums3 = {-1, 1, -6, 4, 5, -6, 1, 4, 1};
        System.out.println("Example 3 Approach 1: " + Arrays.toString(solution.frequencySortApproach1(nums3.clone())));
        System.out.println("Example 3 Approach 2: " + Arrays.toString(solution.frequencySortApproach2(nums3.clone())));
        System.out.println("Example 3 Approach 3: " + Arrays.toString(solution.frequencySortApproach3(nums3.clone())));
    }
}
