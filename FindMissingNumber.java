/*
 268. Missing Number
Solved
Easy
Topics
Companies
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

 

Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
Example 2:

Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
             2 is the missing number in the range since it does not appear in nums.
Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
             8 is the missing number in the range since it does not appear in nums.
 

Constraints:

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.
 

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */

public class FindMissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length; // Length of the array
        // Sum of the first n natural numbers formula: n * (n + 1) / 2
        int sum = (n * (n + 1)) / 2; // Expected sum of numbers from 0 to n
        int sum2 = 0; // Sum of numbers in the given array

        // Calculate the sum of numbers present in the array
        for (int i = 0; i < n; i++) {
            sum2 += nums[i];
        }

        // The missing number is the difference between the expected sum and the actual sum
        return sum - sum2;
    }

    public static void main(String[] args) {
        FindMissingNumber solution = new FindMissingNumber();

        // Test case
        int[] nums = {0, 1, 2, 4}; // Missing number is 3
        System.out.println(solution.missingNumber(nums)); // Output: 3
    }
}

/*
Explanation:
1. The method calculates the expected sum of the first `n` natural numbers using the formula `n * (n + 1) / 2`.
   This formula gives the sum of numbers from `0` to `n`.

2. It then computes the actual sum of the numbers present in the given array.

3. The missing number is found by subtracting the actual sum from the expected sum.
   This is because the difference between these sums will be the missing number.
*/
