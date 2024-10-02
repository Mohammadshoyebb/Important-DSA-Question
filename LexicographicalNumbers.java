/**
 * 386. Lexicographical Numbers
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * 
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 * 
 * Example 1:
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 
 * Example 2:
 * Input: n = 2
 * Output: [1,2]
 * 
 * Constraints:
 * 1 <= n <= 5 * 10^4
 */

 import java.util.*;

 public class LexicographicalNumbers {
     
     // Approach 1: Iterative approach
     public List<Integer> lexicalOrderIterative(int n) {
         List<Integer> res = new ArrayList<>();
         int currNum = 1;
         for (int i = 1; i <= n; i++) {
             res.add(currNum);
             if (currNum * 10 <= n) {
                 currNum *= 10;
             } else {
                 while (currNum % 10 == 9 || currNum == n) {
                     currNum /= 10;
                 }
                 currNum++;
             }
         }
         return res;
     }
 
     // Approach 2: Recursive approach
     public void solve(int n, List<Integer> lst, int curr) {
         if (curr > n) {
             return;
         }
         lst.add(curr);
         for (int append = 0; append <= 9; append++) {
             int num = curr * 10 + append;
             if (num > n) {
                 return;
             }
             solve(n, lst, num);
         }
     }
 
     public List<Integer> lexicalOrderRecursive(int n) {
         List<Integer> lst = new ArrayList<>();
         for (int i = 1; i <= 9; i++) {
             solve(n, lst, i);
         }
         return lst;
     }
 
     // Approach 3: String-based sorting
     public List<Integer> lexicalOrderStringSort(int n) {
         List<String> lst = new ArrayList<>();
         for (int i = 1; i <= n; i++) {
             lst.add(Integer.toString(i));
         }
         Collections.sort(lst);
 
         ArrayList<Integer> integerList = new ArrayList<>();
         for (String str : lst) {
             integerList.add(Integer.parseInt(str));  // Convert each String to Integer and add to list
         }
         return integerList;
     }
 
     // Main method to test all approaches
     public static void main(String[] args) {
         LexicographicalNumbers solution = new LexicographicalNumbers();
         int n = 13;
 
         // Testing Approach 1
         System.out.println("Approach 1 (Iterative):");
         List<Integer> resultIterative = solution.lexicalOrderIterative(n);
         System.out.println(resultIterative);
 
         // Testing Approach 2
         System.out.println("Approach 2 (Recursive):");
         List<Integer> resultRecursive = solution.lexicalOrderRecursive(n);
         System.out.println(resultRecursive);
 
         // Testing Approach 3
         System.out.println("Approach 3 (String-based sorting):");
         List<Integer> resultStringSort = solution.lexicalOrderStringSort(n);
         System.out.println(resultStringSort);
     }
 }
 