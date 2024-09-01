// Karan's Birthday Gift
// Description:
// Swetha wants to purchase a gift for Karan's birthday party. She visits a stationary shop and selects the best gift for Karan, costing X rupees. Swetha's wallet contains only the coins that are given in the array `coins`.
// Help Swetha determine the minimum number of coins she needs to use from her wallet to pay the exact amount (X rupees) for Karan's gift.
// 
// Input Format:
// The first line of input contains an integer X which is the amount Swetha needs to pay for the gift.
// The second line of input contains an integer array `coins` representing coins of different denominations that Swetha contains in her wallet.
// 
// Output Format:
// Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
// You may assume that she has an infinite number of each kind of coin.
// 
// Constraints:
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 2147483648
// 0 <= amount <= 104

import java.util.Arrays;

public class KaransBirthdayGift {

    // Function to find the minimum number of coins to make up the amount
    public static int minCoins(int[] coins, int amount) {
        // Create a dp array of size amount + 1 and initialize with a large value
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);  // Initialize with amount + 1, which is effectively infinity
        dp[0] = 0;  // Base case: 0 coins needed to make the amount 0

        // Fill the dp array
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still amount + 1, then it means it's not possible to form that amount
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        // Example values for testing
        int amount = 13;
        int[] coins = {1, 3, 4, 5};

        // Calculate the minimum number of coins needed
        int result = minCoins(coins, amount);

        // Output the result
        System.out.println(result);  // Expected output: 3
    }
}
