// 3016. Minimum Number of Pushes to Type Word II
// Difficulty: Medium
// Topics: String, Priority Queue
// Companies: Not mentioned
// 
// You are given a string word containing lowercase English letters.
// 
// Telephone keypads have keys mapped with distinct collections of lowercase English letters, which can be used to form words by pushing them. 
// For example, the key 2 is mapped with ["a","b","c"], we need to push the key one time to type "a", two times to type "b", and three times to type "c".
// 
// It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters. The keys can be remapped to any amount of letters, 
// but each letter must be mapped to exactly one key. You need to find the minimum number of times the keys will be pushed to type the string word.
// 
// Return the minimum number of pushes needed to type word after remapping the keys.
// 
// Example 1:
// Input: word = "abcde"
// Output: 5
// Explanation: The remapped keypad given in the image provides the minimum cost.
// "a" -> one push on key 2
// "b" -> one push on key 3
// "c" -> one push on key 4
// "d" -> one push on key 5
// "e" -> one push on key 6
// Total cost is 1 + 1 + 1 + 1 + 1 = 5.
// It can be shown that no other mapping can provide a lower cost.
// 
// Example 2:
// Input: word = "xyzxyzxyzxyz"
// Output: 12
// Explanation: The remapped keypad given in the image provides the minimum cost.
// "x" -> one push on key 2
// "y" -> one push on key 3
// "z" -> one push on key 4
// Total cost is 1 * 4 + 1 * 4 + 1 * 4 = 12
// It can be shown that no other mapping can provide a lower cost.
// Note that the key 9 is not mapped to any letter: it is not necessary to map letters to every key, but to map all the letters.
// 
// Example 3:
// Input: word = "aabbccddeeffgghhiiiiii"
// Output: 24
// Explanation: The remapped keypad given in the image provides the minimum cost.
// "a" -> one push on key 2
// "b" -> one push on key 3
// "c" -> one push on key 4
// "d" -> one push on key 5
// "e" -> one push on key 6
// "f" -> one push on key 7
// "g" -> one push on key 8
// "h" -> two pushes on key 9
// "i" -> one push on key 9
// Total cost is 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24.
// It can be shown that no other mapping can provide a lower cost.
// 
// Constraints:
// 1 <= word.length <= 105
// word consists of lowercase English letters.
// 
// This class includes two approaches to solve the problem.

import java.util.Arrays;

public class MinimumNumberOfPushesToTypeWordII {

    // Function to find the minimum number of pushes needed to type the word after remapping the keys using the first approach
    public int minimumPushesApproach1(String word) {
        int[] freq = new int[26];

        // Step 1: Calculate the frequency of each letter in the word
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort the frequency array in ascending order
        Arrays.sort(freq);

        int count = 0;
        int keyPress = 0;

        // Step 3: Calculate the minimum key presses needed
        for (int i = 25; i >= 0; i--) {
            int mul = 0;

            if (count < 8) {
                mul = 1;
            } else if (count < 16) {
                mul = 2;
            } else if (count < 24) {
                mul = 3;
            } else {
                mul = 4;
            }

            int res = freq[i] * mul;
            keyPress += res;
            count++;
        }

        return keyPress;
    }

    // Function to find the minimum number of pushes needed to type the word after remapping the keys using the second approach
    public int minimumPushesApproach2(String word) {
        int[] freq = new int[26];

        // Step 1: Calculate the frequency of each letter in the word
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort the frequency array in ascending order
        Arrays.sort(freq);

        int count = 0;
        int keyPress = 0;

        // Step 3: Calculate the minimum key presses needed
        for (int i = 25; i >= 0; i--) {
            int res = freq[i] * (count / 8 + 1);
            keyPress += res;
            count++;
        }

        return keyPress;
    }

    // Main method to test the solutions
    public static void main(String[] args) {
        MinimumNumberOfPushesToTypeWordII solution = new MinimumNumberOfPushesToTypeWordII();

        // Test cases
        String word1 = "abcde";
        String word2 = "xyzxyzxyzxyz";
        String word3 = "aabbccddeeffgghhiiiiii";

        // Using Approach 1
        System.out.println("Approach 1:");
        System.out.println("Minimum pushes for word1: " + solution.minimumPushesApproach1(word1)); // Output: 5
        System.out.println("Minimum pushes for word2: " + solution.minimumPushesApproach1(word2)); // Output: 12
        System.out.println("Minimum pushes for word3: " + solution.minimumPushesApproach1(word3)); // Output: 24

        // Using Approach 2
        System.out.println("Approach 2:");
        System.out.println("Minimum pushes for word1: " + solution.minimumPushesApproach2(word1)); // Output: 5
        System.out.println("Minimum pushes for word2: " + solution.minimumPushesApproach2(word2)); // Output: 12
        System.out.println("Minimum pushes for word3: " + solution.minimumPushesApproach2(word3)); // Output: 24
    }
}
