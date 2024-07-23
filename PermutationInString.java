// Problem 567. Permutation in String
// Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
// In other words, return true if one of s1's permutations is the substring of s2.

// Example 1:
// Input: s1 = "ab", s2 = "eidbaooo"
// Output: true
// Explanation: s2 contains one permutation of s1 ("ba").

// Example 2:
// Input: s1 = "ab", s2 = "eidboaoo"
// Output: false

// Constraints:
// 1 <= s1.length, s2.length <= 10^4
// s1 and s2 consist of lowercase English letters.

public class PermutationInString {
    // Method to check if s2 contains any permutation of s1
    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        // If s1 is longer than s2, s2 cannot contain a permutation of s1
        if (l1 > l2) {
            return false;
        }

        // Frequency array for characters in s1
        int[] arr = new int[26];

        // Initialize frequency count for s1
        for (int i = 0; i < l1; i++) {
            arr[s1.charAt(i) - 'a']++;
        }

        // Process the first window of size l1 in s2
        for (int i = 0; i < l2; i++) {
            // Decrease count for the character that is added to the window
            arr[s2.charAt(i) - 'a']--;

            // If window has more than l1 characters, increase count for the character that is removed from the window
            if (i >= l1) {
                arr[s2.charAt(i - l1) - 'a']++;
            }

            // Check if all counts are zero, meaning current window is a permutation of s1
            if (allZeroes(arr)) {
                return true;
            }
        }

        // No permutation found
        return false;
    }

    // Method to check if all elements in the frequency array are zero
    public boolean allZeroes(int[] nums) {
        for (int i = 0; i < 26; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        PermutationInString solution = new PermutationInString();

        // Example 1: s2 contains a permutation of s1
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println("Example 1: " + solution.checkInclusion(s1, s2)); // Output: true

        // Example 2: s2 does not contain a permutation of s1
        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println("Example 2: " + solution.checkInclusion(s1, s2)); // Output: false

        // Additional Example 3: s2 contains a permutation of s1
        s1 = "abc";
        s2 = "cbabcacab";
        System.out.println("Example 3: " + solution.checkInclusion(s1, s2)); // Output: true

        // Additional Example 4: s2 does not contain a permutation of s1
        s1 = "abcd";
        s2 = "abdc";
        System.out.println("Example 4: " + solution.checkInclusion(s1, s2)); // Output: false
    }
}
