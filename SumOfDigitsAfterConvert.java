/**
 * 1945. Sum of Digits of String After Convert
 * 
 * You are given a string s consisting of lowercase English letters, and an integer k.
 * 
 * First, convert s into an integer by replacing each letter with its position in the alphabet 
 * (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26). Then, transform the integer by replacing 
 * it with the sum of its digits. Repeat the transform operation k times in total.
 * 
 * Example 1:
 * Input: s = "iiii", k = 1
 * Output: 36
 * 
 * Example 2:
 * Input: s = "leetcode", k = 2
 * Output: 6
 * 
 * Example 3:
 * Input: s = "zbax", k = 2
 * Output: 8
 * 
 * Constraints:
 * 1 <= s.length <= 100
 * 1 <= k <= 10
 * s consists of lowercase English letters.
 */

 public class SumOfDigitsAfterConvert {

    /**
     * This method converts each character in the string to its corresponding alphabet position,
     * then repeatedly transforms the resulting number by summing its digits k times.
     * 
     * @param s The input string consisting of lowercase English letters.
     * @param k The number of transformations to apply.
     * @return The resulting integer after performing the operations described above.
     */
    public int getLucky(String s, int k) {
        int sum = 0;
        int n = s.length();

        // Convert the string to its corresponding numeric value
        for (int i = 0; i < n; i++) {
            int temp = s.charAt(i) - 'a' + 1; // Convert each character to its alphabet position
            while (temp > 0) {
                int rem = temp % 10; // Extract the last digit
                sum += rem; // Sum the digits
                temp /= 10; // Remove the last digit
            }
        }

        k--; // We already performed the first transformation, so decrement k
        int num = sum;

        // Perform the remaining k-1 transformations
        while (k > 0) {
            sum = 0;
            int temp = num;
            while (temp > 0) {
                int rem = temp % 10; // Extract the last digit
                sum += rem; // Sum the digits
                temp /= 10; // Remove the last digit
            }
            num = sum; // Update num to the new sum
            k--; // Decrement the number of transformations remaining
        }

        return sum; // Return the final result
    }

    public static void main(String[] args) {
        SumOfDigitsAfterConvert solution = new SumOfDigitsAfterConvert();

        // Test case 1
        String s1 = "iiii";
        int k1 = 1;
        System.out.println("Output 1: " + solution.getLucky(s1, k1)); // Expected output: 36

        // Test case 2
        String s2 = "leetcode";
        int k2 = 2;
        System.out.println("Output 2: " + solution.getLucky(s2, k2)); // Expected output: 6

        // Test case 3
        String s3 = "zbax";
        int k3 = 2;
        System.out.println("Output 3: " + solution.getLucky(s3, k3)); // Expected output: 8
    }
}
