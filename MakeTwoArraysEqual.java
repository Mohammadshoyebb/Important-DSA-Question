/*
 * Question: 1460. Make Two Arrays Equal by Reversing Subarrays
 *
 * You are given two integer arrays of equal length target and arr. In one step, you can select any 
 * non-empty subarray of arr and reverse it. You are allowed to make any number of steps.
 *
 * Return true if you can make arr equal to target or false otherwise.
 *
 * Example 1:
 * Input: target = [1,2,3,4], arr = [2,4,1,3]
 * Output: true
 * Explanation: You can follow the next steps to convert arr to target:
 * 1- Reverse subarray [2,4,1], arr becomes [1,4,2,3]
 * 2- Reverse subarray [4,2], arr becomes [1,2,4,3]
 * 3- Reverse subarray [4,3], arr becomes [1,2,3,4]
 * There are multiple ways to convert arr to target, this is not the only way to do so.
 *
 * Example 2:
 * Input: target = [7], arr = [7]
 * Output: true
 * Explanation: arr is equal to target without any reverses.
 *
 * Example 3:
 * Input: target = [3,7,9], arr = [3,7,11]
 * Output: false
 * Explanation: arr does not have value 9 and it can never be converted to target.
 *
 * Constraints:
 * target.length == arr.length
 * 1 <= target.length <= 1000
 * 1 <= target[i] <= 1000
 * 1 <= arr[i] <= 1000
 */

 import java.util.*;

 public class MakeTwoArraysEqual {
 
     // Frequency Array Approach
     public boolean canBeEqualUsingFreqArray(int[] target, int[] arr) {
         int[] freq = new int[1001]; // Since the constraints say 1 <= target[i], arr[i] <= 1000
 
         for (int num : target) {
             freq[num]++;
         }
 
         for (int num : arr) {
             if (freq[num] == 0) {
                 return false;
             }
             freq[num]--;
         }
 
         return true;
     }
 
     // HashMap Approach
     public boolean canBeEqualUsingHashMap(int[] target, int[] arr) {
         Map<Integer, Integer> freqMap = new HashMap<>();
 
         for (int num : target) {
             freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
         }
 
         for (int num : arr) {
             if (!freqMap.containsKey(num) || freqMap.get(num) == 0) {
                 return false;
             }
             freqMap.put(num, freqMap.get(num) - 1);
         }
 
         return true;
     }
 
     public static void main(String[] args) {
         MakeTwoArraysEqual solution = new MakeTwoArraysEqual();
 
         // Test cases
         int[] target1 = {1, 2, 3, 4};
         int[] arr1 = {2, 4, 1, 3};
         System.out.println(solution.canBeEqualUsingFreqArray(target1, arr1)); // Output: true
         System.out.println(solution.canBeEqualUsingHashMap(target1, arr1));   // Output: true
 
         int[] target2 = {7};
         int[] arr2 = {7};
         System.out.println(solution.canBeEqualUsingFreqArray(target2, arr2)); // Output: true
         System.out.println(solution.canBeEqualUsingHashMap(target2, arr2));   // Output: true
 
         int[] target3 = {3, 7, 9};
         int[] arr3 = {3, 7, 11};
         System.out.println(solution.canBeEqualUsingFreqArray(target3, arr3)); // Output: false
         System.out.println(solution.canBeEqualUsingHashMap(target3, arr3));   // Output: false
     }
 }
 

 //=====================      Easy way for map approach      ==============================
/*
 public class MakeTwoArraysEqualHashMap {
    public boolean canBeEqual(int[] target, int[] arr) {
        // HashMap to store the frequency of elements in target array
        Map<Integer, Integer> map = new HashMap<>();
        
        // Increment frequency for target array
        for (int num : target) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Decrement frequency for arr array
        for (int num : arr) {
            if (!map.containsKey(num)) {
                return false;
            }
            map.put(num, map.get(num) - 1);
            if (map.get(num) == 0) {
                map.remove(num);
            }
        }
        
        // Check if all frequencies are zero
        return map.isEmpty();
    }

    public static void main(String[] args) {
        MakeTwoArraysEqualHashMap solution = new MakeTwoArraysEqualHashMap();
        
        // Test cases
        System.out.println(solution.canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})); // Output: true
        System.out.println(solution.canBeEqual(new int[]{7}, new int[]{7})); // Output: true
        System.out.println(solution.canBeEqual(new int[]{3, 7, 9}, new int[]{3, 7, 11})); // Output: false
    }
}
 */