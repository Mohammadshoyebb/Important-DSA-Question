/**
 * 670. Maximum Swap
 * 
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 * 
 * Example 1:
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * 
 * Example 2:
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap is needed.
 * 
 * Constraints:
 * 0 <= num <= 10^8
 */

 public class MaximumSwap {

    // Approach 1: Brute Force Approach O(N^2)
    public int maximumSwapBruteForce(int num) {
        char[] ch = Integer.toString(num).toCharArray();
        int maxNum = num;
        
        // Try all possible pairs of digits and swap them
        for (int i = 0; i < ch.length; i++) {
            for (int j = i + 1; j < ch.length; j++) {
                // Swap ch[i] and ch[j]
                char temp = ch[i];
                ch[i] = ch[j];
                ch[j] = temp;

                // Convert back to integer and check if it's the maximum
                int swappedNum = Integer.parseInt(new String(ch));
                maxNum = Math.max(maxNum, swappedNum);

                // Swap them back to restore the original string
                ch[j] = ch[i];
                ch[i] = temp;
            }
        }

        return maxNum;
    }

    // Approach 2: Optimized Single Pass Approach O(N)
    public int maximumSwapSinglePass(int num) {
        // Convert the number to a character array for easy manipulation
        char[] ch = Integer.toString(num).toCharArray();
        int n = ch.length;

        // Variables to track the maximum element and its index
        int maxIndex = n - 1;
        char maxElem = ch[n - 1];
        int idx1 = -1;
        int idx2 = -1;

        // Traverse from right to left to track the largest digit to swap
        for (int i = n - 2; i >= 0; i--) {
            if (ch[i] > maxElem) {
                maxElem = ch[i];
                maxIndex = i;
            } else if (ch[i] < maxElem) {
                idx1 = i;
                idx2 = maxIndex;
            }
        }

        // Swap the found digits if a valid swap is possible
        if (idx1 != -1) {
            char temp = ch[idx1];
            ch[idx1] = ch[idx2];
            ch[idx2] = temp;
        }

        return Integer.parseInt(new String(ch));
    }

    // Approach 3: Optimized with Prefix Array of Indices O(2N)
    public int maximumSwapWithPrefixArray(int num) {
        char[] ch = Integer.toString(num).toCharArray();
        int n = ch.length;
        
        // Array to store the index of the maximum digit to the right of each index
        int[] maxRightIndex = new int[n];
        maxRightIndex[n - 1] = n - 1;

        // Fill the maxRightIndex array
        for (int i = n - 2; i >= 0; i--) {
            if (ch[i] > ch[maxRightIndex[i + 1]]) {
                maxRightIndex[i] = i;
            } else {
                maxRightIndex[i] = maxRightIndex[i + 1];
            }
        }

        // Traverse and find the first position where swap can maximize the number
        for (int i = 0; i < n; i++) {
            if (ch[i] != ch[maxRightIndex[i]]) {
                // Swap ch[i] with the maximum digit found
                char temp = ch[i];
                ch[i] = ch[maxRightIndex[i]];
                ch[maxRightIndex[i]] = temp;
                break;
            }
        }

        return Integer.parseInt(new String(ch));
    }

    // Main method to test all approaches
    public static void main(String[] args) {
        MaximumSwap solution = new MaximumSwap();
        
        // Test input 1: num = 2736
        int num1 = 2736;
        System.out.println("Brute Force Result for 2736: " + solution.maximumSwapBruteForce(num1));
        System.out.println("Single Pass Result for 2736: " + solution.maximumSwapSinglePass(num1));
        System.out.println("Prefix Array Result for 2736: " + solution.maximumSwapWithPrefixArray(num1));

        // Test input 2: num = 9973
        int num2 = 9973;
        System.out.println("Brute Force Result for 9973: " + solution.maximumSwapBruteForce(num2));
        System.out.println("Single Pass Result for 9973: " + solution.maximumSwapSinglePass(num2));
        System.out.println("Prefix Array Result for 9973: " + solution.maximumSwapWithPrefixArray(num2));
    }
}

