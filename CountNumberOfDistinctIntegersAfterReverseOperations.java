/*
2442. Count Number of Distinct Integers After Reverse Operations
You are given an array nums consisting of positive integers.

You have to take each integer in the array, reverse its digits, and add it to the end of the array. 
You should apply this operation to the original integers in nums.

Return the number of distinct integers in the final array.

Example 1:

Input: nums = [1,13,10,12,31]
Output: 6
Explanation: After including the reverse of each number, the resulting array is [1,13,10,12,31,1,31,1,21,13].
The reversed integers that were added to the end of the array are underlined. Note that for the integer 10, 
after reversing it, it becomes 01 which is just 1.
The number of distinct integers in this array is 6 (The numbers 1, 10, 12, 13, 21, and 31).

Example 2:

Input: nums = [2,2,2]
Output: 1
Explanation: After including the reverse of each number, the resulting array is [2,2,2,2,2,2].
The number of distinct integers in this array is 1 (The number 2).

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 106
*/

import java.util.HashSet;
import java.util.Set;

public class CountNumberOfDistinctIntegersAfterReverseOperations {
    
    // Approach 1

    private int findReverse(int n) {
        int sum = 0;
        while (n > 0) {
            int r = n % 10;
            sum = sum * 10 + r;
            n /= 10;
        }
        return sum;
    }
    
    public int countDistinctIntegersApproach1(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
            set.add(findReverse(num));
        }
        return set.size();
    }

    // Approach 2
    public int countDistinctIntegersApproach2(int[] nums) {
        int count = 0;
        boolean[] fre = new boolean[1000001];

        for (int num : nums) {
            if (!fre[num]) {
                fre[num] = true;
                count++;
            }

            int rev = findReverse(num);
            if (!fre[rev]) {
                fre[rev] = true;
                count++;
            }
        }
        return count;
    }

    

    public static void main(String[] args) {
        CountNumberOfDistinctIntegersAfterReverseOperations solution = new CountNumberOfDistinctIntegersAfterReverseOperations();
        
        int[] nums1 = {1, 13, 10, 12, 31};
        System.out.println("Approach 1 - Number of distinct integers: " + solution.countDistinctIntegersApproach1(nums1)); // Output: 6
        System.out.println("Approach 2 - Number of distinct integers: " + solution.countDistinctIntegersApproach2(nums1)); // Output: 6

        int[] nums2 = {2, 2, 2};
        System.out.println("Approach 1 - Number of distinct integers: " + solution.countDistinctIntegersApproach1(nums2)); // Output: 1
        System.out.println("Approach 2 - Number of distinct integers: " + solution.countDistinctIntegersApproach2(nums2)); // Output: 1
    }
}
