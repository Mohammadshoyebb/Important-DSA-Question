/*
1297. Maximum Number of Occurrences of a Substring
Medium

Given a string s, return the maximum number of occurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.
 
Example 1:
Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 occurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).

Example 2:
Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 
Constraints:
1 <= s.length <= 105
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s consists of only lowercase English letters.
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MaximumNumberOfOccurrencesOfASubstring {
    
    // Approach 1: Using a HashMap to store substring frequencies
    private boolean isValid(String cur, int maxLetters, int maxSize) {
        HashSet<Character> hs = new HashSet<>();
        for (char c : cur.toCharArray()) hs.add(c);
        return hs.size() <= maxLetters && cur.length() <= maxSize;
    }

    public int maxFreqApproach1(String s, int maxLetters, int minSize, int maxSize) {
        if (s == null || s.length() == 0 || maxLetters == 0) 
            return 0;

        HashMap<String, Integer> hm = new HashMap<>();
        int max = 0;

        for (int i = 0; i < s.length() - minSize + 1; i++) {
            String cur = s.substring(i, i + minSize);
            if (isValid(cur, maxLetters, maxSize)) {
                hm.put(cur, hm.getOrDefault(cur, 0) + 1);
                max = Math.max(max, hm.get(cur));
            }
        }
        return max;
    }

    // Approach 2: Using a sliding window and a HashMap
    public int maxFreqApproach2(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        int letters = 0;
        int[] count = new int[26];
        Map<String, Integer> substringCount = new HashMap<>();
        
        for (int l = 0, r = 0; r < s.length(); ++r) {
            if (++count[s.charAt(r) - 'a'] == 1)
                ++letters;
            
            while (letters > maxLetters || r - l + 1 > minSize) {
                if (--count[s.charAt(l++) - 'a'] == 0)
                    --letters;
            }
            
            if (r - l + 1 == minSize)
                ans = Math.max(ans, substringCount.merge(s.substring(l, l + minSize), 1, Integer::sum));
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumNumberOfOccurrencesOfASubstring solution = new MaximumNumberOfOccurrencesOfASubstring();
        
        // Test case 1
        String s1 = "aababcaab";
        int maxLetters1 = 2;
        int minSize1 = 3;
        int maxSize1 = 4;
        System.out.println("Approach 1 Output: " + solution.maxFreqApproach1(s1, maxLetters1, minSize1, maxSize1));
        System.out.println("Approach 2 Output: " + solution.maxFreqApproach2(s1, maxLetters1, minSize1, maxSize1));

        // Test case 2
        String s2 = "aaaa";
        int maxLetters2 = 1;
        int minSize2 = 3;
        int maxSize2 = 3;
        System.out.println("Approach 1 Output: " + solution.maxFreqApproach1(s2, maxLetters2, minSize2, maxSize2));
        System.out.println("Approach 2 Output: " + solution.maxFreqApproach2(s2, maxLetters2, minSize2, maxSize2));
    }
}

