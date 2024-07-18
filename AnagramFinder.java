/*

 438. Find All Anagrams in a String
Solved
Medium
Topics
Companies
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.

*/

import java.util.ArrayList;
import java.util.List;

public class AnagramFinder {

    // Method to calculate the frequency of each character in a given string
    private int[] frequency(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        return count;
    }

    // Method to compare two frequency arrays
    private boolean isSame(int[] s, int[] p) {
        for (int i = 0; i < 26; i++) {
            if (s[i] != p[i]) {
                return false;
            }
        }
        return true;
    }

    // Method to find starting indices of p's anagrams in s
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> lst = new ArrayList<>();
        if (s.length() < p.length()) {
            return lst;
        }

        int n = s.length();
        int m = p.length();

        int[] count = frequency(p);
        int[] currentCount = frequency(s.substring(0, m));

        if (isSame(currentCount, count)) {
            lst.add(0);
        }

        for (int i = 1; i <= n - m; i++) {
            currentCount[s.charAt(i - 1) - 'a']--;
            currentCount[s.charAt(i + m - 1) - 'a']++;

            if (isSame(count, currentCount)) {
                lst.add(i);
            }
        }
        return lst;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        AnagramFinder finder = new AnagramFinder();
        
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(finder.findAnagrams(s1, p1)); // Output: [0, 6]

        String s2 = "abab";
        String p2 = "ab";
        System.out.println(finder.findAnagrams(s2, p2)); // Output: [0, 1, 2]
    }
}

//============================HASHMAP++++++++++++++++++++++++++++++++++++++
/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnagramFinder {

  // Method to find starting indices of p's anagrams in s
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> lst = new ArrayList<>();
    if (s.length() < p.length()) {
      return lst;
    }

    HashMap<Character, Integer> pCount = new HashMap<>();
    for (char c : p.toCharArray()) {
      pCount.put(c, pCount.getOrDefault(c, 0) + 1);
    }

    int n = s.length();
    int m = p.length();

    HashMap<Character, Integer> currentCount = new HashMap<>();
    for (int i = 0; i < m; i++) {
      char c = s.charAt(i);
      currentCount.put(c, currentCount.getOrDefault(c, 0) + 1);
    }

    if (isSame(currentCount, pCount)) {
      lst.add(0);
    }

    for (int i = 1; i <= n - m; i++) {
      char leaving = s.charAt(i - 1);
      char entering = s.charAt(i + m - 1);

      currentCount.put(leaving, currentCount.get(leaving) - 1);
      if (currentCount.get(leaving) == 0) {
        currentCount.remove(leaving);
      }

      currentCount.put(entering, currentCount.getOrDefault(entering, 0) + 1);

      if (isSame(currentCount, pCount)) {
        lst.add(i);
      }
    }

    return lst;
  }

  // Method to compare two character frequency maps
  private boolean isSame(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
    if (map1.size() != map2.size()) {
      return false;
    }
    for (char key : map1.keySet()) {
      if (!map2.containsKey(key) || map1.get(key) != map2.get(key)) {
        return false;
      }
    }
    return true;
  }

  // Main method to test the functionality (same as previous example)
  public static void main(String[] args) {
    AnagramFinder finder = new AnagramFinder();

    String s1 = "cbaebabacd";
    String p1 = "abc";
    System.out.println(finder.findAnagrams(s1, p1)); // Output: [0, 6]

    String s2 = "abab";
    String p2 = "ab";
    System.out.println(finder.findAnagrams(s2, p2)); // Output: [0, 1, 2]
  }
}

 */