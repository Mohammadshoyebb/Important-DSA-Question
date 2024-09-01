// Description:
// Given a string str containing both lowercase and uppercase letters, your task is to find the length of the longest palindrome that can be constructed from the letters in the string. Please note that the letters are case-sensitive.

// Input Format:
// The input is provided as a single line that contains a string, which is referred to as 'str'.

// Output Format:
// Return the length of the longest palindrome that can be formed using a given string.

// Constraints:
// 1 <= s.length <= 10^9

// Public Test Cases:
// Test Case 1
// Input:
// abccccdd

// Output:
// 7

// Explanation:
// Self Explanatory

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class longestPalindromeStringFormation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();

        System.out.println(longestPalindrome(str));
    }

    private static int longestPalindrome(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        int length = 0;
        boolean oddFound = false;

        for (int count : charCount.values()) {
            if (count % 2 == 0) {
                length += count;
            } else {
                length += count - 1;
                oddFound = true;
            }
        }

        if (oddFound) {
            length += 1;
        }

        return length;
    }
}
