/*
 * 387. First Unique Character in a String
 * Given a string s, find the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 * 
 * Example 1:
 * Input: s = "leetcode"
 * Output: 0
 * 
 * Example 2:
 * Input: s = "loveleetcode"
 * Output: 2
 * 
 * Example 3:
 * Input: s = "aabb"
 * Output: -1
 * 
 * Constraints:
 * 1 <= s.length <= 105
 * s consists of only lowercase English letters.
 */

 import java.util.*;

 public class FirstUniqueCharacter {
     public int firstUniqChar(String s) {
         Map<Character, Integer> frequencyMap = new HashMap<>();
         Queue<int[]> queue = new LinkedList<>();
         
         // Fill the frequency map and queue with characters and their indices
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
             queue.offer(new int[] {c, i});
         }
         
         // Process the queue to find the first unique character
         while (!queue.isEmpty()) {
             int[] front = queue.peek();
             char c = (char) front[0];
             int index = front[1];
             
             if (frequencyMap.get(c) == 1) {
                 return index;
             } else {
                 queue.poll();
             }
         }
         
         return -1;
     }
 
     public static void main(String[] args) {
         FirstUniqueCharacter solution = new FirstUniqueCharacter();
 
         // Test cases
         System.out.println(solution.firstUniqChar("leetcode"));      // Output: 0
         System.out.println(solution.firstUniqChar("loveleetcode"));  // Output: 2
         System.out.println(solution.firstUniqChar("aabb"));          // Output: -1
     }
 }
 