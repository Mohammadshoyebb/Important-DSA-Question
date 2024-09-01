/*
 2022. Convert 1D Array Into 2D Array
Solved
Easy
Topics
Companies
Hint
You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n. You are tasked with creating a 2-dimensional (2D) array with  m rows and n columns using all the elements from original.

The elements from indices 0 to n - 1 (inclusive) of original should form the first row of the constructed 2D array, the elements from indices n to 2 * n - 1 (inclusive) should form the second row of the constructed 2D array, and so on.

Return an m x n 2D array constructed according to the above procedure, or an empty 2D array if it is impossible.

 

Example 1:


Input: original = [1,2,3,4], m = 2, n = 2
Output: [[1,2],[3,4]]
Explanation: The constructed 2D array should contain 2 rows and 2 columns.
The first group of n=2 elements in original, [1,2], becomes the first row in the constructed 2D array.
The second group of n=2 elements in original, [3,4], becomes the second row in the constructed 2D array.
Example 2:

Input: original = [1,2,3], m = 1, n = 3
Output: [[1,2,3]]
Explanation: The constructed 2D array should contain 1 row and 3 columns.
Put all three elements in original into the first row of the constructed 2D array.
Example 3:

Input: original = [1,2], m = 1, n = 1
Output: []
Explanation: There are 2 elements in original.
It is impossible to fit 2 elements in a 1x1 2D array, so return an empty 2D array.
 

Constraints:

1 <= original.length <= 5 * 104
1 <= original[i] <= 105
1 <= m, n <= 4 * 104
 */

 public class Convert1DArrayInto2DArray {
    public int[][] construct2DArray(int[] original, int m, int n) {
        // Check if it's possible to create an m x n 2D array from the original array
        if (original.length != m * n) {
            return new int[0][0];  // Return an empty 2D array if not possible
        }

        int[][] result = new int[m][n];
        for (int i = 0; i < original.length; i++) {
            result[i / n][i % n] = original[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Convert1DArrayInto2DArray converter = new Convert1DArrayInto2DArray();

        // Example 1
        int[] original1 = {1, 2, 3, 4};
        int[][] result1 = converter.construct2DArray(original1, 2, 2);
        print2DArray(result1);  // Output: [[1, 2], [3, 4]]

        // Example 2
        int[] original2 = {1, 2, 3};
        int[][] result2 = converter.construct2DArray(original2, 1, 3);
        print2DArray(result2);  // Output: [[1, 2, 3]]

        // Example 3
        int[] original3 = {1, 2};
        int[][] result3 = converter.construct2DArray(original3, 1, 1);
        print2DArray(result3);  // Output: []
    }

    // Helper method to print the 2D array
    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
