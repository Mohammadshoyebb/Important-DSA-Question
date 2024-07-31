/**
 * 7. Reverse Integer
 * 
 * Given a signed 32-bit integer x, return x with its digits reversed. 
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * 
 * Example 1:
 * Input: x = 123
 * Output: 321
 * 
 * Example 2:
 * Input: x = -123
 * Output: -321
 * 
 * Example 3:
 * Input: x = 120
 * Output: 21
 * 
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 */

 public class ReverseInteger {

    // Method to reverse the digits of an integer
    public int reverse(int x) {
        int ans = 0;
        
        while (x != 0) {
            int rem = x % 10;
            // Check for overflow/underflow before updating ans
            if (ans > Integer.MAX_VALUE / 10 || ans < Integer.MIN_VALUE / 10) return 0;
            ans = ans * 10 + rem;
            x /= 10;
        }
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();

        int x1 = 123;
        System.out.println("Reversed integer for x1: " + solution.reverse(x1)); // Output: 321

        int x2 = -123;
        System.out.println("Reversed integer for x2: " + solution.reverse(x2)); // Output: -321

        int x3 = 120;
        System.out.println("Reversed integer for x3: " + solution.reverse(x3)); // Output: 21

        int x4 = 1534236469;
        System.out.println("Reversed integer for x4: " + solution.reverse(x4)); // Output: 0 (overflow)
    }
}
