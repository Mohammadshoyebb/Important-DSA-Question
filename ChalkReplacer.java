/**
 * 1894. Find the Student that Will Replace the Chalk
 * 
 * There are n students in a class numbered from 0 to n - 1. The teacher will give each student a problem starting with the student number 0, then the student number 1, and so on until the teacher reaches the student number n - 1. 
 * After that, the teacher will restart the process, starting with the student number 0 again.
 *
 * You are given a 0-indexed integer array chalk and an integer k. There are initially k pieces of chalk. 
 * When the student number i is given a problem to solve, they will use chalk[i] pieces of chalk to solve that problem. 
 * However, if the current number of chalk pieces is strictly less than chalk[i], then the student number i will be asked to replace the chalk.
 *
 * Return the index of the student that will replace the chalk pieces.
 * 
 * Example 1:
 * Input: chalk = [5,1,5], k = 22
 * Output: 0
 * 
 * Example 2:
 * Input: chalk = [3,4,1,2], k = 25
 * Output: 1
 * 
 * Constraints:
 * chalk.length == n
 * 1 <= n <= 10^5
 * 1 <= chalk[i] <= 10^5
 * 1 <= k <= 10^9
 */

 public class ChalkReplacer {

    /**
     * Approach 1: Linear Search
     * 
     * This approach calculates the total chalk needed for one full round.
     * Then, it reduces the given k by the total chalk to find the remaining chalk.
     * Finally, it iterates over the chalk array to find the student who will need to replace the chalk.
     * 
     * @param chalk The array representing the number of chalk pieces each student uses.
     * @param k The total number of chalk pieces available.
     * @return The index of the student who will replace the chalk.
     */
    public int findStudentToReplaceChalkLinear(int[] chalk, int k) {
        int numOfStudents = chalk.length;
        long totalChalk = 0;
        
        // Calculate the total amount of chalk required in one full round
        for (int chalkRequired : chalk) {
            totalChalk += chalkRequired;
        }
        
        // Calculate the remaining chalk after full rounds
        int remainingChalk = (int) (k % totalChalk);

        // Identify the student who will need to replace the chalk
        for (int i = 0; i < numOfStudents; i++) {
            if (remainingChalk < chalk[i]) {
                return i;
            }
            remainingChalk -= chalk[i];
        }
        return -1; // This line will never be reached based on the problem constraints
    }

    /**
     * Approach 2: Binary Search
     * 
     * This approach builds a cumulative sum array for the chalk usage.
     * It then reduces k by the total chalk sum and uses binary search on the cumulative sum array 
     * to efficiently find the student who will replace the chalk.
     * 
     * @param chalk The array representing the number of chalk pieces each student uses.
     * @param k The total number of chalk pieces available.
     * @return The index of the student who will replace the chalk.
     */
    public int findStudentToReplaceChalkBinarySearch(int[] chalk, int k) {
        int numOfStudents = chalk.length;
        long[] cumulativeSum = new long[numOfStudents];
        cumulativeSum[0] = chalk[0];

        // Build cumulative sum array
        for (int i = 1; i < numOfStudents; i++) {
            cumulativeSum[i] = chalk[i] + cumulativeSum[i - 1];
        }

        // Reduce k by the sum of one full round
        k = (int) (k % cumulativeSum[numOfStudents - 1]);

        // Find the student who will replace the chalk using binary search
        return findStudentUsingBinarySearch(cumulativeSum, k);
    }

    /**
     * Helper method for binary search.
     * 
     * This method performs binary search on the cumulative sum array to find the first student 
     * whose cumulative chalk usage is greater than k.
     * 
     * @param cumulativeSum The cumulative sum array of chalk usage.
     * @param k The remaining number of chalk pieces.
     * @return The index of the student who will replace the chalk.
     */
    private int findStudentUsingBinarySearch(long[] cumulativeSum, int k) {
        int left = 0, right = cumulativeSum.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (cumulativeSum[mid] > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        ChalkReplacer chalkReplacer = new ChalkReplacer();

        // Test Approach 1: Linear Search
        int[] chalk1 = {5, 1, 5};
        int k1 = 22;
        System.out.println("Linear Search Output: " + chalkReplacer.findStudentToReplaceChalkLinear(chalk1, k1)); // Output: 0

        // Test Approach 2: Binary Search
        int[] chalk2 = {3, 4, 1, 2};
        int k2 = 25;
        System.out.println("Binary Search Output: " + chalkReplacer.findStudentToReplaceChalkBinarySearch(chalk2, k2)); // Output: 1
    }
}
