/**
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. 
 * You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, 
 * where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * 
 * Example 2:
 * Input: n = 1
 * Output: [["Q"]]
 * 
 * Constraints:
 * 1 <= n <= 9
 */

 import java.util.ArrayList;
 import java.util.List;
 
 public class NQueenProblem {
     public List<List<String>> solveNQueens(int n) {
         List<List<String>> result = new ArrayList<>();
         char[][] board = new char[n][n];
         
         // Initialize the chess board with '.' to represent empty squares
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < n; j++) {
                 board[i][j] = '.'; // Setting each position to '.'
             }
         }
         
         // Start the process of placing queens, beginning with the first row (row 0)
         assigningQueen(board, 0, result);
         return result;
     }
 
     // Recursive method to place queens on the chess board
     // arr is the current state of the chess board
     // row is the current row we are attempting to place a queen on
     public void assigningQueen(char arr[][], int row, List<List<String>> result) {
         // Base case: If we have processed all rows, add the board configuration to result
         if (row == arr.length) {
             result.add(construct(arr));
             return;
         }
         
         // Try placing a queen in each column of the current row
         for (int j = 0; j < arr.length; j++) {
             if (isSafe(arr, row, j)) { // Check if it's safe to place the queen at (row, j)
                 // Place a queen at position (row, j)
                 arr[row][j] = 'Q';
                 
                 // Recursively attempt to place queens in the next row
                 assigningQueen(arr, row + 1, result);
                 
                 // Backtracking step: Remove the queen and try the next position
                 arr[row][j] = '.'; // Reset the position to '.' (empty)
             }
         }
     }
 
     // Method to check if it's safe to place a queen at arr[row][col]
     public boolean isSafe(char arr[][], int row, int col) {
         // Check this column on upper side
         for (int i = row - 1; i >= 0; i--) {
             if (arr[i][col] == 'Q') {
                 return false; // There is another queen in the same column
             }
         }
 
         // Check upper left diagonal
         for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
             if (arr[i][j] == 'Q') {
                 return false; // There is another queen on the upper left diagonal
             }
         }
 
         // Check upper right diagonal
         for (int i = row - 1, j = col + 1; i >= 0 && j < arr.length; i--, j++) {
             if (arr[i][j] == 'Q') {
                 return false; // There is another queen on the upper right diagonal
             }
         }
 
         return true; // It's safe to place the queen
     }
 
     // Method to convert the current state of the chess board to List<String>
     public List<String> construct(char[][] board) {
         List<String> result = new ArrayList<>();
         for (int i = 0; i < board.length; i++) {
             String row = new String(board[i]);
             result.add(row);
         }
         return result;
     }
 
     public static void main(String[] args) {
         NQueenProblem solution = new NQueenProblem();
         int n = 4; // You can change this value to test other cases
         List<List<String>> solutions = solution.solveNQueens(n);
         
         System.out.println("Number of Solutions: " + solutions.size());
         for (List<String> solutionBoard : solutions) {
             for (String row : solutionBoard) {
                 System.out.println(row);
             }
             System.out.println();
         }
     }
 }
 


 //=================================   SAME APPROACH WITH ANOTHER STYLE   =============================================

 /*
   
import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> board = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        
        solve(result, board, n, 0, cols, diag1, diag2);
        return result;
    }

    private void solve(List<List<String>> result, List<String> board, int n, int row, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            result.add(new ArrayList<>(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1; // Diagonal 1
            int d2 = row + col;         // Diagonal 2

            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                char[] rowArray = new char[n];
                for (int i = 0; i < n; i++) {
                    rowArray[i] = '.';
                }
                rowArray[col] = 'Q';
                String rowString = new String(rowArray);
                
                board.add(rowString);
                cols[col] = true;
                diag1[d1] = true;
                diag2[d2] = true;

                solve(result, board, n, row + 1, cols, diag1, diag2);

                board.remove(board.size() - 1);
                cols[col] = false;
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }

    public static void main(String[] args) {
        NQueens solver = new NQueens();
        List<List<String>> solutions = solver.solveNQueens(4);
        System.out.println("Number of Solutions: " + solutions.size());
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}

  */