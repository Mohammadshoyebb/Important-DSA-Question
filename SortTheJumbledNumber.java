/**
 * 2191. Sort the Jumbled Numbers
 * Medium
 * 
 * Given an integer array mapping which represents the mapping rule of a shuffled decimal system. mapping[i] = j means digit i should be mapped to digit j in this system.
 * 
 * The mapped value of an integer is the new integer obtained by replacing each occurrence of digit i in the integer with mapping[i] for all 0 <= i <= 9.
 * 
 * You are also given another integer array nums. Return the array nums sorted in non-decreasing order based on the mapped values of its elements.
 * 
 * Notes:
 * - Elements with the same mapped values should appear in the same relative order as in the input.
 * - The elements of nums should only be sorted based on their mapped values and not be replaced by them.
 * 
 * Example 1:
 * Input: mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
 * Output: [338,38,991]
 * Explanation: 
 * Map the number 991 as follows:
 * 1. mapping[9] = 6, so all occurrences of the digit 9 will become 6.
 * 2. mapping[1] = 9, so all occurrences of the digit 1 will become 9.
 * Therefore, the mapped value of 991 is 669.
 * 338 maps to 007, or 7 after removing the leading zeros.
 * 38 maps to 07, which is also 7 after removing leading zeros.
 * Since 338 and 38 share the same mapped value, they should remain in the same relative order, so 338 comes before 38.
 * Thus, the sorted array is [338,38,991].
 * 
 * Example 2:
 * Input: mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
 * Output: [123,456,789]
 * Explanation: 789 maps to 789, 456 maps to 456, and 123 maps to 123. Thus, the sorted array is [123,456,789].
 * 
 * Constraints:
 * mapping.length == 10
 * 0 <= mapping[i] <= 9
 * All the values of mapping[i] are unique.
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] < 10^9
 */



 import java.util.*;
// Solution1: Approach using transformation and sorting based on mapped values
class Solution1 {
    // Method to transform the numbers in the nums array using the given mapping
    public int[] newArray(int[] mapping, int[] nums) {
        int n = nums.length;
        int[] newSys = new int[n];
        for (int j = 0; j < n; j++) {
            int num = nums[j];
            if (num == 0) {
                newSys[j] = mapping[0]; // Special case for 0
                continue;
            }
            int i = 0;
            int res = 0;
            // Transform each digit of num using the mapping
            while (num > 0) {
                int d = num % 10;
                int newD = mapping[d];
                res = newD * (int) Math.pow(10, i++) + res;
                num = num / 10;
            }
            newSys[j] = res;
        }
        return newSys;
    }

    // Method to sort the nums array based on the transformed values using the mapping
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[] newSys = newArray(mapping, nums);

        // Create an index array to keep track of original indices
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        // Sort the index array based on the mapped values
        Arrays.sort(index, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return newSys[a] - newSys[b];
            }
        });

        // Build the result array based on the sorted indices
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[index[i]];
        }
        return res;
    }
}

// Solution2: Approach using radix sort based on mapped values
class Solution2 {
    // Function to map a single digit using the given mapping
    private int mapDigit(int digit, int[] mapping) {
        return mapping[digit];
    }

    // Function to map an entire number based on the given mapping
    private int mapNumber(int num, int[] mapping) {
        if (num == 0) return mapping[0]; // Special case for 0
        
        StringBuilder mappedValue = new StringBuilder();
        // Transform each digit of num using the mapping
        while (num > 0) {
            mappedValue.append(mapDigit(num % 10, mapping));
            num /= 10;
        }
        // Convert the mapped value to an integer
        return Integer.parseInt(mappedValue.reverse().toString());
    }

    // Function to sort using radix sort based on the mapped values
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int max = -1;
        // Find the maximum number to determine the number of digits
        for (int x : nums) {
            if (x > max) max = x;
        }

        int[] ans = nums;

        // Apply radix sort for each digit place
        for (int place = 1; max / place > 0; place *= 10) {
            ans = countSort(ans, place, mapping);
        }

        return ans;
    }

    // Function to perform counting sort based on the given digit place and mapping
    static int[] countSort(int[] arr, int place, int[] mapping) {
        int n = arr.length;
        if (n <= 1) return arr;

        int[] ans = new int[n];
        int[] freqArr = new int[10];

        // Create a frequency array based on the digit at the current place
        for (int x : arr) {
            if (x / place != 0 || x == 0) {
                int val = (x / place) % 10;
                freqArr[mapping[val]]++;
            } else {
                freqArr[0]++;
            }
        }

        // Convert frequency array to prefix sum array
        for (int i = 1; i < 10; i++) {
            freqArr[i] += freqArr[i - 1];
        }

        // Build the sorted output array
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] / place != 0 || arr[i] == 0) {
                int val = (arr[i] / place) % 10;
                ans[freqArr[mapping[val]] - 1] = arr[i];
                freqArr[mapping[val]]--;
            } else {
                ans[freqArr[0] - 1] = arr[i];
                freqArr[0]--;
            }
        }

        return ans;
    }
}

public class SortTheJumbledNumber {
    public static void main(String[] args) {
        int[] mapping = {8, 9, 4, 0, 2, 1, 3, 5, 7, 6};
        int[] nums = {991, 338, 38};

        // Testing Solution1 approach
        Solution1 solution1 = new Solution1();
        System.out.println("Solution 1 Output: " + Arrays.toString(solution1.sortJumbled(mapping, nums)));

        // Testing Solution2 approach
        Solution2 solution2 = new Solution2();
        System.out.println("Solution 2 Output: " + Arrays.toString(solution2.sortJumbled(mapping, nums)));
    }
}
