/**
 * 1380. Lucky Numbers in a Matrix
 * Solved
 * Easy
 * Topics
 * Companies
 * Hint
 * Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 * 
 * A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
 * 
 * Example 1:
 * Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * Output: [15]
 * Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
 * 
 * Example 2:
 * Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * Output: [12]
 * Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
 * 
 * Example 3:
 * Input: matrix = [[7,8],[1,2]]
 * Output: [7]
 * Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
 * 
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 105.
 * All elements in the matrix are distinct.
 */

 import java.util.ArrayList;
 import java.util.List;
 
 public class LuckyNumberInMatrix {
     public List<Integer> luckyNumbers (int[][] array) {
         List<Integer> result = new ArrayList<>();
 
         int rows = array.length;
         int cols = array[0].length;
         int maxOfRowMins = Integer.MIN_VALUE;
 
         // Find the minimum element in each row
         for (int i = 0; i < rows; i++) {
             int minVal = array[i][0];
             for (int j = 1; j < cols; j++) {
                 if (array[i][j] < minVal) {
                     minVal = array[i][j];
                 }
             }
             // Keep track of the maximum of these minimum values
             maxOfRowMins = Math.max(minVal, maxOfRowMins);
         }
 
         // Find the maximum element in each column
         for (int j = 0; j < cols; j++) {
             int maxVal = array[0][j];
             for (int i = 1; i < rows; i++) {
                 if (array[i][j] > maxVal) {
                     maxVal = array[i][j];
                 }
             }
             // Check if the maximum element is equal to the maximum of row minimums
             if(maxVal == maxOfRowMins){
                 result.add(maxOfRowMins);
                 // Since elements are distinct, we can break after finding one lucky number
                 break;
             }
         }
         return result;
     }
 
     public static void main(String[] args) {
         LuckyNumberInMatrix solution = new LuckyNumberInMatrix();
         
         int[][] matrix1 = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
         System.out.println(solution.luckyNumbers(matrix1)); // Output: [15]
         
         int[][] matrix2 = {{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}};
         System.out.println(solution.luckyNumbers(matrix2)); // Output: [12]
         
         int[][] matrix3 = {{7, 8}, {1, 2}};
         System.out.println(solution.luckyNumbers(matrix3)); // Output: [7]
     }
 }
 
