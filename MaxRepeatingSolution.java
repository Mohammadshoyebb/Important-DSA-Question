/*
 1668. Maximum Repeating Substring
Solved
Easy
Topics
Companies
Hint
For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence. The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence. If word is not a substring of sequence, word's maximum k-repeating value is 0.

Given strings sequence and word, return the maximum k-repeating value of word in sequence.

 

Example 1:

Input: sequence = "ababc", word = "ab"
Output: 2
Explanation: "abab" is a substring in "ababc".
Example 2:

Input: sequence = "ababc", word = "ba"
Output: 1
Explanation: "ba" is a substring in "ababc". "baba" is not a substring in "ababc".
Example 3:

Input: sequence = "ababc", word = "ac"
Output: 0
Explanation: "ac" is not a substring in "ababc". 
 

Constraints:

1 <= sequence.length <= 100
1 <= word.length <= 100
sequence and word contains only lowercase English letters.
 */


public class MaxRepeatingSolution {
    public int maxRepeating(String sequence, String word) {
        int count = 0;
        int n = sequence.length();
        int m = word.length();

        // Edge cases
        if (n < m) {
            return 0;
        }

        StringBuilder repeatedWord = new StringBuilder(word);
        while (sequence.contains(repeatedWord)) {
            count++;
            repeatedWord.append(word);
        }
        
        return count;
    }

    public static void main(String[] args) {
        MaxRepeatingSolution solution = new MaxRepeatingSolution();
        
        // Test cases
        System.out.println(solution.maxRepeating("ababc", "ab")); // Output: 2
        System.out.println(solution.maxRepeating("ababc", "ba")); // Output: 1
        System.out.println(solution.maxRepeating("ababc", "ac")); // Output: 0
        System.out.println(solution.maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba")); // Output: 5
    }
}
//================================================Using SubString Method==============================================================
/*
 class MaxRepeatingSolution {
    public int maxRepeating(String sequence, String word) {
        int maxCount = 0;
        int currentCount = 0;
        int n = sequence.length();
        int m = word.length();

        // Edge cases
        if (n < m) {
            return 0;
        }

        // Iterate through each possible starting position in the sequence
        for (int i = 0; i <= n - m; i++) {
            currentCount = 0;
            
            // Check for repeated occurrences of word starting at position i
            while (i + (currentCount + 1) * m <= n && sequence.substring(i, i + (currentCount + 1) * m).endsWith(word.repeat(currentCount + 1))) {
                currentCount++;
            }
            
            // Update maxCount if we found a longer sequence of repeating word
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        MaxRepeatingSolution solution = new MaxRepeatingSolution();
        
        // Test cases
        System.out.println(solution.maxRepeating("ababc", "ab")); // Output: 2
        System.out.println(solution.maxRepeating("ababc", "ba")); // Output: 1
        System.out.println(solution.maxRepeating("ababc", "ac")); // Output: 0
        System.out.println(solution.maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba")); // Output: 5
    }
}

 */
