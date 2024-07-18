/*
 5. Longest Palindromic Substring
Solved
Medium
Topics
Companies
Hint
Given a string s, return the longest 
palindromic
 
substring
 in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }

        String longestPalindrome = "";

        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes
            int low = i, high = i;

            while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
                low--;
                high++;
            }
            String palindrome = s.substring(low + 1, high);

            if (longestPalindrome.length() < palindrome.length()) {
                longestPalindrome = palindrome;
            }

            // Check for even-length palindromes
            low = i;
            high = i + 1;

            while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
                low--;
                high++;
            }
            palindrome = s.substring(low + 1, high);

            if (longestPalindrome.length() < palindrome.length()) {
                longestPalindrome = palindrome;
            }
        }
        return longestPalindrome;
    }

    public static void main(String[] args) {
        LongestPalindromeSubstring solution = new LongestPalindromeSubstring();

        // Test cases
        String s1 = "babad";
        System.out.println(solution.longestPalindrome(s1)); // Output: "bab" or "aba"

        String s2 = "cbbd";
        System.out.println(solution.longestPalindrome(s2)); // Output: "bb"

        String s3 = "a";
        System.out.println(solution.longestPalindrome(s3)); // Output: "a"

        String s4 = "ac";
        System.out.println(solution.longestPalindrome(s4)); // Output: "a" or "c"
    }
}

