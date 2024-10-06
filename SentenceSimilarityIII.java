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
        if(sentence1.length() > sentence2.length()){
            return areSentencesSimilar(sentence2,  sentence1);
        }
        String smallerSentence[] = sentence1.split(" ");
        String largerSentence[] = sentence2.split(" ");

        
        int start = 0;
        int end1 = smallerSentence.length-1;
        int end2 = largerSentence.length-1;

        while(start <= end1 && smallerSentence[start].equals(largerSentence[start])){
            start++;
        }
        while(start <= end1 && smallerSentence[end1].equals(largerSentence[end2])){
            end1--;
            end2--;
        }
        return start>end1;
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


/*
 import java.util.*;

class Solution {
    // Helper function to split a sentence into words and store them in a deque
    private Deque<String> toDeque(String sentence) {
        // Split the sentence by spaces and return a deque of words
        return new LinkedList<>(Arrays.asList(sentence.split(" ")));
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        // Convert both sentences into deques of words
        Deque<String> deque1 = toDeque(sentence1);
        Deque<String> deque2 = toDeque(sentence2);
        
        // Ensure deque1 refers to the longer sentence
        if (deque1.size() < deque2.size()) {
            Deque<String> temp = deque1;
            deque1 = deque2;
            deque2 = temp;
        }
        
        // Compare words from the front while they match
        while (!deque2.isEmpty() && deque1.peekFirst().equals(deque2.peekFirst())) {
            deque1.pollFirst(); // Remove the matching word from the front
            deque2.pollFirst(); // Remove the matching word from the front
        }
        
        // Compare words from the back while they match
        while (!deque2.isEmpty() && deque1.peekLast().equals(deque2.peekLast())) {
            deque1.pollLast(); // Remove the matching word from the back
            deque2.pollLast(); // Remove the matching word from the back
        }
        
        // The two sentences are similar if deque2 is empty after the comparisons
        return deque2.isEmpty();
    }
}

 */