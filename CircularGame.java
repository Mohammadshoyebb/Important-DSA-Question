/*
1823. Find the Winner of the Circular Game
Solved
Medium
Topics: Arrays, Queue, Recursion
Companies: Unknown

There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.

The rules of the game are as follows:
1. Start at the 1st friend.
2. Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
3. The last friend you counted leaves the circle and loses the game.
4. If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
5. Else, the last friend in the circle wins the game.

Given the number of friends, n, and an integer k, return the winner of the game.

Example 1:
Input: n = 5, k = 2
Output: 3
Explanation: Here are the steps of the game:
1) Start at friend 1.
2) Count 2 friends clockwise, which are friends 1 and 2.
3) Friend 2 leaves the circle. Next start is friend 3.
4) Count 2 friends clockwise, which are friends 3 and 4.
5) Friend 4 leaves the circle. Next start is friend 5.
6) Count 2 friends clockwise, which are friends 5 and 1.
7) Friend 1 leaves the circle. Next start is friend 3.
8) Count 2 friends clockwise, which are friends 3 and 5.
9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.

Example 2:
Input: n = 6, k = 5
Output: 1
Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is friend 1.

Constraints:
1 <= k <= n <= 500

Follow up: Could you solve this problem in linear time with constant space?
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CircularGame {

    // Approach 1: Recursive approach
    // Time Complexity: O(n)
    // Space Complexity: O(n) due to recursion stack
    public int findTheWinnerRecursive(int n, int k) {
        if (n == 1) return 1;
        return (findTheWinnerRecursive(n - 1, k) + k - 1) % n + 1;
    }

    // Approach 2: Using Queue
    // Time Complexity: O(n * k)
    // Space Complexity: O(n)
    public int findTheWinnerQueue(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            // k-1 rotations
            for (int i = 0; i < k - 1; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    // Approach 3: Using ArrayList
    // Time Complexity: O(n * k)
    // Space Complexity: O(n)
    public int findTheWinnerArrayList(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            index = (index + (k - 1)) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    // Approach 4: Improved Recursive approach
    // Time Complexity: O(n)
    // Space Complexity: O(n) due to recursion stack
    public int findTheWinnerImproved(int n, int k) {
        int index = findIndex(n, k);
        return index + 1;
    }

    private int findIndex(int n, int k) {
        if (n == 1) return 0;
        return (findIndex(n - 1, k) + k) % n;
    }

    // Best Approach: Iterative approach using Josephus problem solution
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int findTheWinnerBest(int n, int k) {
        int ans = 0;

        for (int i = 2; i <= n; i++) {
            ans = (ans + k) % i;
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        CircularGame game = new CircularGame();
        int n = 5;
        int k = 2;

        // Test Recursive approach
        System.out.println("Winner (Recursive): " + game.findTheWinnerRecursive(n, k)); // Output: 3

        // Test Queue approach
        System.out.println("Winner (Queue): " + game.findTheWinnerQueue(n, k)); // Output: 3

        // Test ArrayList approach
        System.out.println("Winner (ArrayList): " + game.findTheWinnerArrayList(n, k)); // Output: 3

        // Test Improved Recursive approach
        System.out.println("Winner (Improved Recursive): " + game.findTheWinnerImproved(n, k)); // Output: 3

        // Test Best approach
        System.out.println("Winner (Best): " + game.findTheWinnerBest(n, k)); // Output: 3

        // Another example
        n = 6;
        k = 5;

        // Test Recursive approach
        System.out.println("Winner (Recursive): " + game.findTheWinnerRecursive(n, k)); // Output: 1

        // Test Queue approach
        System.out.println("Winner (Queue): " + game.findTheWinnerQueue(n, k)); // Output: 1

        // Test ArrayList approach
        System.out.println("Winner (ArrayList): " + game.findTheWinnerArrayList(n, k)); // Output: 1

        // Test Improved Recursive approach
        System.out.println("Winner (Improved Recursive): " + game.findTheWinnerImproved(n, k)); // Output: 1

        // Test Best approach
        System.out.println("Winner (Best): " + game.findTheWinnerBest(n, k)); // Output: 1
    }
}
