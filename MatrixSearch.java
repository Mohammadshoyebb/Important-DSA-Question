/*
 74. Search a 2D Matrix
Solved
Medium
Topics
Companies
You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

 

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
 */
public class MatrixSearch {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length; // Number of rows
        int n = matrix[0].length; // Number of columns
        
        int left = 0; // Start of the search range
        int right = m * n - 1; // End of the search range
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Find the middle index
            int midValue = matrix[mid / n][mid % n]; // Convert mid to 2D index
            
            if (midValue == target) {
                return true; // Target found
            } else if (midValue < target) {
                left = mid + 1; // Move right
            } else {
                right = mid - 1; // Move left
            }
        }
        
        return false; // Target not found
    }

    public static void main(String[] args) {
        MatrixSearch solution = new MatrixSearch();

        // Test cases
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target1 = 3;
        System.out.println(solution.searchMatrix(matrix1, target1)); // Output: true

        int[][] matrix2 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target2 = 13;
        System.out.println(solution.searchMatrix(matrix2, target2)); // Output: false
    }
}


// Another Method

/*
 class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;

        while(col>=0 && row < matrix.length){
            if(matrix[row][col] == target){
                return true;
            }
            else if(target > matrix[row][col]){
                row++;
            }
            else{
                col--;
            }
        }
        return false;
    }
}
 */