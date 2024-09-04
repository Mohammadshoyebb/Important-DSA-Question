/**
 * Problem Statement:
 * 
 * You have been given a list of scores representing the games played by a team. The team is interested in
 * finding the number of sets they've played, where a set is defined as a series of back-to-back games where
 * the score difference between any two consecutive games is at most 2 points. Can you help the team to 
 * determine how many such sets can they find in their list of scores?
 * 
 * Note: A set or subarray is defined as a contiguous non-empty sequence of elements within the array.
 * 
 * Sample Testcase 1:
 * Input: 4 5 4 2 4
 * Output: 8
 * 
 * Explanation:
 * Continuous subarray of size 1: [5], [4], [2], [4].
 * Continuous subarray of size 2: [5,4], [4,2], [2,4].
 * Continuous subarray of size 3: [4,2,4].
 * Total continuous subarrays = 4 + 3 + 1 = 8.
 * 
 * Sample Testcase 2:
 * Input: 1 2 3
 * Output: 6
 * 
 * Explanation:
 * Continuous subarray of size 1: [1], [2], [3].
 * Continuous subarray of size 2: [1,2], [2,3].
 * Continuous subarray of size 3: [1,2,3].
 * Total continuous subarrays = 3 + 2 + 1 = 6.
 */

 public class CountSets {
    public static void main(String[] args) {
        System.out.println(countSets(new int[]{5, 4, 2, 4}));  // Output: 8
        System.out.println(countSets(new int[]{1, 2, 3}));     // Output: 6
    }

    public static int countSets(int[] scores) {
        int n = scores.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                boolean isSet = true;
                for (int k = i; k < j; k++) {
                    if (Math.abs(scores[k] - scores[k + 1]) > 2) {
                        isSet = false;
                        break;
                    }
                }
                if (isSet) {
                    count++;
                }
            }
        }
        return count;
    }
}




/*
public class NumberOfSets {

    public static void main(String[] args) {
        // Sample Testcase 1
        int[] scores1 = {5, 4, 2, 4};
        System.out.println(countSets(scores1)); // Output: 8
        
        // Sample Testcase 2
        int[] scores2 = {1, 2, 3};
        System.out.println(countSets(scores2)); // Output: 6
    }

    public static int countSets(int[] scores) {
        int n = scores.length;
        int count = 0;

        // Iterate through each possible starting point of subarrays
        for (int i = 0; i < n; i++) {
            int minValue = scores[i];
            int maxValue = scores[i];
            // Expand the subarray to include more elements
            for (int j = i; j < n; j++) {
                minValue = Math.min(minValue, scores[j]);
                maxValue = Math.max(maxValue, scores[j]);
                // Check if the current subarray meets the condition
                if (maxValue - minValue <= 2) {
                    count++;
                } else {
                    // If it exceeds the difference, break out of the inner loop
                    break;
                }
            }
        }

        return count;
    }
}
    */
