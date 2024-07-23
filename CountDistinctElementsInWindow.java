//  Count distinct elements in every window
// Difficulty: Easy
// Given an array of integers and a number K. Find the count of distinct elements in every window of size K in the array.
// Example 1:
// Input: N = 7, K = 4
// A[] = {1,2,1,3,4,2,3}
// Output: 3 4 4 3
// Explanation: Window 1 of size k = 4 is 1 2 1 3. Number of distinct elements in this window are 3. 
// Window 2 of size k = 4 is 2 1 3 4. Number of distinct elements in this window are 4.
// Window 3 of size k = 4 is 1 3 4 2. Number of distinct elements in this window are 4.
// Window 4 of size k = 4 is 3 4 2 3. Number of distinct elements in this window are 3.
// Example 2:
// Input: N = 3, K = 2
// A[] = {4,1,1}
// Output: 2 1
// Constraints:
// 1 <= K <= N <= 105
// 1 <= A[i] <= 105 , for each valid i

import java.util.ArrayList;
import java.util.HashMap;

public class CountDistinctElementsInWindow {
    // Method to count distinct elements in every window of size K
    ArrayList<Integer> countDistinct(int A[], int N, int K) {
        // ArrayList to store the count of distinct elements for each window
        ArrayList<Integer> result = new ArrayList<>();
        
        // HashMap to store the frequency of each element in the current window
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        
        // Initialize the frequency map with the first window
        for (int i = 0; i < K; i++) {
            freqMap.put(A[i], freqMap.getOrDefault(A[i], 0) + 1);
        }
        
        // Add the count of distinct elements in the first window
        result.add(freqMap.size());
        
        // Slide the window across the array
        for (int i = K; i < N; i++) {
            // Element that is going out of the window
            int outgoingElement = A[i - K];
            // Element that is coming into the window
            int incomingElement = A[i];
            
            // Remove or update the outgoing element
            if (freqMap.get(outgoingElement) == 1) {
                freqMap.remove(outgoingElement);
            } else {
                freqMap.put(outgoingElement, freqMap.get(outgoingElement) - 1);
            }
            
            // Add or update the incoming element
            freqMap.put(incomingElement, freqMap.getOrDefault(incomingElement, 0) + 1);
            
            // Add the count of distinct elements for the current window
            result.add(freqMap.size());
        }
        
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        CountDistinctElementsInWindow solution = new CountDistinctElementsInWindow();
        
        // Example 1
        int[] A1 = {1, 2, 1, 3, 4, 2, 3};
        int N1 = 7;
        int K1 = 4;
        System.out.println("Example 1: " + solution.countDistinct(A1, N1, K1)); // Output: [3, 4, 4, 3]
        
        // Example 2
        int[] A2 = {4, 1, 1};
        int N2 = 3;
        int K2 = 2;
        System.out.println("Example 2: " + solution.countDistinct(A2, N2, K2)); // Output: [2, 1]
        
        // Additional Example 3
        int[] A3 = {1, 2, 2, 1, 3, 3, 4};
        int N3 = 7;
        int K3 = 3;
        System.out.println("Example 3: " + solution.countDistinct(A3, N3, K3)); // Output: [2, 3, 3, 3, 3]
    }
}
