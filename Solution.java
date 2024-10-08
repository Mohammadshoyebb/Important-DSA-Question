/*
There are two arrays of integers, arr1 and arr2. One move is defined as an increment or decrement of one element in an array. 
Determine the minimum number of moves to match arr1 with arr2. No reordering of the digits is allowed.

Example:
arr1 = [123, 543]
arr2 = [321, 279]

Match arr1[0] = 123 with arr2[0] = 321:
    - Increment 1 twice to get 3 (2 moves)
    - Decrement 3 twice to get 1 (2 moves)
    - 4 moves are needed to match 123 with 321

Match arr1[1] = 543 with arr2[1] = 279:
    - Decrement 5 three times to get 2 (3 moves)
    - Increment 4 four times to get 7 (4 moves)
    - Increment 3 six times to get 9 (6 moves)
    - 12 moves are needed to match 543 with 279

A total of 16 moves are needed to match the arrays arr1 and arr2.

Function Description:
Complete the function `minimumMoves` in the editor below. The function has the following parameters:
    - int arr1[n]: array to modify
    - int arr2[n]: array to match

Returns:
    - int: the minimum number of moves to match arr1 with arr2

Constraints:
    - 1 ≤ n ≤ 10^5
    - 1 ≤ arr1[i], arr2[i] ≤ 10^9
    - The lengths of arr1 and arr2 are equal.
    - The elements arr1[i] and arr2[i] have an equal number of digits.

Input Format:
The first line contains an integer n, the number of elements in arr1 and arr2.
The second line contains n space-separated integers, the elements of arr1.
The third line contains n space-separated integers, the elements of arr2.

Output Format:
Return an integer, the minimum number of moves to match arr1 with arr2.
*/

import java.util.*;

public class Solution {
    
    // Function to calculate the minimum number of moves
    public static int minimumMoves(List<Integer> arr1, List<Integer> arr2) {
        int totalMoves = 0;

        for (int i = 0; i < arr1.size(); i++) {
            // Get the corresponding numbers from arr1 and arr2
            int num1 = arr1.get(i);
            int num2 = arr2.get(i);

            // Compare digit by digit and count the moves
            while (num1 > 0 && num2 > 0) {
                int digit1 = num1 % 10;
                int digit2 = num2 % 10;
                totalMoves += Math.abs(digit1 - digit2);
                num1 /= 10;
                num2 /= 10;
            }
        }
        return totalMoves;
    }

    // Main method to execute the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size of arrays
        int n = sc.nextInt();
        
        // Input array1
        List<Integer> arr1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr1.add(sc.nextInt());
        }

        // Input array2
        List<Integer> arr2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr2.add(sc.nextInt());
        }

        // Call the function and print the result
        int result = minimumMoves(arr1, arr2);
        System.out.println(result);
    }
}
