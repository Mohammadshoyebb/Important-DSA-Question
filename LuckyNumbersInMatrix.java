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


 // another code is more efficient with name LuckyNumberInMatrix

 import java.util.ArrayList;
 import java.util.List;
 
 public class LuckyNumbersInMatrix {
     public List<Integer> luckyNumbers(int[][] matrix) {
         List<Integer> result = new ArrayList<>();
 
         int rows = matrix.length;
         int cols = matrix[0].length;
 
         // Find minimum element in each row
         for (int i = 0; i < rows; i++) {
             int minVal = matrix[i][0];
             int minIndex = 0;
             for (int j = 1; j < cols; j++) {
                 if (matrix[i][j] < minVal) {
                     minVal = matrix[i][j];
                     minIndex = j;
                 }
             }
 
             // Check if the minVal is the maximum in its column
             boolean isMaxInCol = true;
             for (int k = 0; k < rows; k++) {
                 if (matrix[k][minIndex] > minVal) {
                     isMaxInCol = false;
                     break;
                 }
             }
 
             if (isMaxInCol) {
                 result.add(minVal);
             }
         }
 
         return result;
     }
 
     public static void main(String[] args) {
         LuckyNumbersInMatrix solution = new LuckyNumbersInMatrix();
         
         int[][] matrix1 = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
         System.out.println(solution.luckyNumbers(matrix1)); // Output: [15]
         
         int[][] matrix2 = {{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}};
         System.out.println(solution.luckyNumbers(matrix2)); // Output: [12]
         
         int[][] matrix3 = {{7, 8}, {1, 2}};
         System.out.println(solution.luckyNumbers(matrix3)); // Output: [7]
     }
 }



 
