/*
1497. Check If Array Pairs Are Divisible by k
Solved
Medium

Given an array of integers arr of even length n and an integer k.

We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.

Return true If you can find a way to do that or false otherwise.

Example 1:
Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).

Example 2:
Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).

Example 3:
Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.

Constraints:
- arr.length == n
- 1 <= n <= 10^5
- n is even.
- -10^9 <= arr[i] <= 10^9
- 1 <= k <= 10^5
*/

import java.util.Scanner;

public class CheckArrayPairsDivisibleByK {

    // Method to check if array pairs are divisible by k
    public boolean canArrange(int[] arr, int k) {
        int[] remainderCount = new int[k];

        // Count the remainders when arr[i] is divided by k
        for (int num : arr) {
            int remainder = (num % k + k) % k;  // Handling negative numbers
            remainderCount[remainder]++;
        }

        // For remainder 0, the count must be even
        if (remainderCount[0] % 2 != 0) {
            return false;
        }

        // For remainders i and k - i, their counts must match
        for (int i = 1; i <= k / 2; i++) {
            if (remainderCount[i] != remainderCount[k - i]) {
                return false;
            }
        }

        return true;
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        CheckArrayPairsDivisibleByK solution = new CheckArrayPairsDivisibleByK();
        Scanner sc = new Scanner(System.in);

        // Input the array size and the divisor k
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the value of k: ");
        int k = sc.nextInt();

        // Call the method to check if pairs are divisible by k
        boolean result = solution.canArrange(arr, k);
        System.out.println("Can the array be paired such that the sum of each pair is divisible by k? " + result);
        
        sc.close();
    }
}
