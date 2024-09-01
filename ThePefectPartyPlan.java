// Description:
// Shivanch, a master party planner, is organizing an extravagant bash for his circle of friends. He has an array of activities lined up, each assigned a score based on its appeal. However, Shivanch knows that his friends have varied tastes, so he wants to arrange the activities in a way that maximizes the overall enjoyment.

// To do this, Shivanch plans to partition the activities into at most k non-empty adjacent groups. The score of each partition is determined by the sum of the averages of each subarray. However, Shivanch must ensure that every activity is included, and the resulting score might not always be an integer.

// Your task is to help Shivanch find the maximum score he can achieve with his party plan. Print it out for him to see and make his party a roaring success!

// Note - the partition must use every integer in nums, and that the score is not necessarily an integer. Answers within 10^-6 of the actual answer will be accepted.

// Input Format:
// The first line contains two integers n and k, denoting the size of the array and the maximum number of non-empty adjacent subarrays to partition it into.
// The second line contains n space-separated integers, representing the elements of the array.

// Output Format:
// Print a single real number, representing the maximum score Shivanch can achieve with his party plan.

// Constraints:
// 1 <= nums.length <= 100
// 1 <= nums[i] <= 10^4
// 1 <= k <= nums.length

// Public Test Cases:
// Test Case 1
// Input:
// 5 3
// 9 1 2 3 9

// Output:
// 20.00000

// Explanation:
// The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
// We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
// That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.

import java.util.Scanner;

public class ThePefectPartyPlan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        System.out.printf("%.5f\n", maxScore(nums, k));
    }

    private static double maxScore(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        double[] prefixSum = new double[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = prefixSum[i] / i;
        }

        for (int j = 2; j <= k; j++) {
            for (int i = j; i <= n; i++) {
                for (int m = j - 1; m < i; m++) {
                    dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + (prefixSum[i] - prefixSum[m]) / (i - m));
                }
            }
        }

        return dp[n][k];
    }
}

