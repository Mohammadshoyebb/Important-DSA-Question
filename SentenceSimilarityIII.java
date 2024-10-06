/*
1813. Sentence Similarity III

You are given two strings sentence1 and sentence2, each representing a sentence composed of words. 
A sentence is a list of words that are separated by a single space with no leading or trailing spaces. 
Each word consists of only uppercase and lowercase English characters.

Two sentences sentence1 and sentence2 are considered similar if it is possible to insert an arbitrary sentence 
(possibly empty) inside one of these sentences such that the two sentences become equal. 
Note that the inserted sentence must be separated from existing words by spaces.

For example:
- sentence1 = "Hello Jane" and sentence2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence1.
- sentence1 = "Frog cool" and sentence2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into sentence1, it is not separated from "Frog" by a space.

Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.

Examples:

Example 1:
Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
Output: true
Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".

Example 2:
Input: sentence1 = "of", sentence2 = "A lot of words"
Output: false
Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.

Example 3:
Input: sentence1 = "Eating right now", sentence2 = "Eating"
Output: true
Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.

Constraints:
1. 1 <= sentence1.length, sentence2.length <= 100
2. sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
3. The words in sentence1 and sentence2 are separated by a single space.
*/

public class SentenceSimilarityIII {

    // Method to check if two sentences are similar
    public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        // Split both sentences into words
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        
        // Initialize two pointers
        int i = 0;  // Pointer starting from the beginning
        int j = 0;  // Pointer starting from the end
        
        // Move i forward as long as the words match from the start
        while (i < words1.length && i < words2.length && words1[i].equals(words2[i])) {
            i++;  // Move the start pointer forward
        }
        
        // Move j backward as long as the words match from the end
        while (j < words1.length - i && j < words2.length - i && 
               words1[words1.length - 1 - j].equals(words2[words2.length - 1 - j])) {
            j++;  // Move the end pointer backward
        }
        
        // If the sum of the matched parts from start and end covers one of the sentences,
        // then the sentences are similar
        return i + j == Math.min(words1.length, words2.length);
    }

    // Main method to test the solution with different test cases
    public static void main(String[] args) {
        // Test case 1
        String sentence1 = "My name is Haley";
        String sentence2 = "My Haley";
        System.out.println(areSentencesSimilar(sentence1, sentence2));  // Output: true
        
        // Test case 2
        sentence1 = "of";
        sentence2 = "A lot of words";
        System.out.println(areSentencesSimilar(sentence1, sentence2));  // Output: false
        
        // Test case 3
        sentence1 = "Eating right now";
        sentence2 = "Eating";
        System.out.println(areSentencesSimilar(sentence1, sentence2));  // Output: true
    }
}
