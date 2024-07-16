/**
 * 670. Maximum Swap
 * Medium
 * Topics
 * Companies
 * 
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * 
 * Return the maximum valued number you can get.
 * 
 * Example 1:
 * 
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * 
 * Example 2:
 * 
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 * 
 * Constraints:
 * 
 * 0 <= num <= 10^8
 */

 public class MaxSwap {
    public int maximumSwap(int num) {
        // Convert the number to a character array for easy manipulation
        char[] digits = Integer.toString(num).toCharArray();

        // Array to track the last occurrence of each digit (0-9)
        int[] last = new int[10];
        for (int i = 0; i < digits.length; i++) {
            last[digits[i] - '0'] = i;
        }

        // Traverse the digits array to find the first position to swap
        for (int i = 0; i < digits.length; i++) {
            // Check from 9 to the current digit for a larger digit to swap
            for (int d = 9; d > digits[i] - '0'; d--) {
                if (last[d] > i) {
                    // Swap the digits
                    char temp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = temp;
                    // Convert the array back to integer and return
                    return Integer.parseInt(new String(digits));
                }
            }
        }

        // If no swap needed, return the original number
        return num;
    }

    public static void main(String[] args) {
        MaxSwap solution = new MaxSwap();
        
        // Test cases
        int num1 = 2736;
        int num2 = 9973;
        int num3 = 0;

        System.out.println("Maximum value after swap for " + num1 + ": " + solution.maximumSwap(num1)); // Output: 7236
        System.out.println("Maximum value after swap for " + num2 + ": " + solution.maximumSwap(num2)); // Output: 9973
        System.out.println("Maximum value after swap for " + num3 + ": " + solution.maximumSwap(num3)); // Output: 0
    }
}

