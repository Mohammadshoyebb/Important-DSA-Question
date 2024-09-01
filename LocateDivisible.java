// Locate Divisible
// Description:
// A range of positive numbers, from l to r, is provided to you.
// Locate a pair of numbers (x,y) such that x divides y, l ≤ x, y ≤ r, and x ≠ y.
// Additionally, There are T separate test cases.
// 
// Input Format:
// The first line contains a single integer T the number of queries.
// The next T lines are composed of two integers, l and r, which represent the inclusive limits of the range.
// It is guaranteed that the test only includes queries.
// 
// Output Format:
// The answer is two integers, x and y, such that l ≤ x, y ≤ r, x ≠ y, and x divides y. Print T lines with the answer on each line. The ith query from the input should be answered in the ith line.
// 
// Constraints:
// 1 ≤ T ≤ 1000
// 1 ≤ l ≤ r ≤ 998244353

public class LocateDivisible {
    public static void main(String[] args) {
        // Example test cases
        int[][] testCases = {
            {1, 10},
            {3, 14},
            {1, 10}
        };

        // Process each test case
        for (int[] testCase : testCases) {
            int l = testCase[0];
            int r = testCase[1];

            // The simplest pair can always be (l, 2*l) if 2*l <= r
            if (2 * l <= r) {
                System.out.println(l + " " + (2 * l));
            } else {
                System.out.println("-1 -1");
            }
        }
    }
}

