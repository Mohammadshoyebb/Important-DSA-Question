/*
Question: 
You are given a string that consists of characters typed on a QWERTY keyboard.
Calculate the total distance traveled by the finger moving from one character to the next 
based on the Manhattan distance. 
The starting position is at the character 'Q' (0, 0) on the keyboard layout. 
Return the total distance for the input string.

Input: "QAZWSXEDCRFVTGBYHNUJIMOLKJIHGFDSA"
Output: 116
*/

import java.util.HashMap;
import java.util.Map;

public class KeyboardDistance {

    // Method to calculate the total typing distance
    public static int getDistance(String word) {
        // Define the keyboard layout as coordinates
        Map<Character, int[]> keyboard = new HashMap<>();
        keyboard.put('Q', new int[]{0, 0});
        keyboard.put('W', new int[]{0, 1});
        keyboard.put('E', new int[]{0, 2});
        keyboard.put('R', new int[]{0, 3});
        keyboard.put('T', new int[]{0, 4});
        keyboard.put('Y', new int[]{0, 5});
        keyboard.put('U', new int[]{0, 6});
        keyboard.put('I', new int[]{0, 7});
        keyboard.put('O', new int[]{0, 8});
        keyboard.put('P', new int[]{0, 9});
        keyboard.put('A', new int[]{1, 0});
        keyboard.put('S', new int[]{1, 1});
        keyboard.put('D', new int[]{1, 2});
        keyboard.put('F', new int[]{1, 3});
        keyboard.put('G', new int[]{1, 4});
        keyboard.put('H', new int[]{1, 5});
        keyboard.put('J', new int[]{1, 6});
        keyboard.put('K', new int[]{1, 7});
        keyboard.put('L', new int[]{1, 8});
        keyboard.put('Z', new int[]{2, 1});
        keyboard.put('X', new int[]{2, 2});
        keyboard.put('C', new int[]{2, 3});
        keyboard.put('V', new int[]{2, 4});
        keyboard.put('B', new int[]{2, 5});
        keyboard.put('N', new int[]{2, 6});
        keyboard.put('M', new int[]{2, 7});

        // Initialize start position at 'Q'
        int[] currentPosition = keyboard.get('Q');
        int totalDistance = 0;

        // Calculate distance for each character in the word
        for (char c : word.toCharArray()) {
            int[] nextPosition = keyboard.get(c);
            // Calculate Manhattan distance
            totalDistance += Math.abs(nextPosition[0] - currentPosition[0]) +
                             Math.abs(nextPosition[1] - currentPosition[1]);
            // Update current position
            currentPosition = nextPosition;
        }

        return totalDistance;
    }

    // Main method to test the function
    public static void main(String[] args) {
        System.out.println(getDistance("QZ"));   // Output: 3
        System.out.println(getDistance("QA"));   // Output: 1
        System.out.println(getDistance("HELLO")); // Output: 18
    }
}
