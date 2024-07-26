import java.util.*;
import java.io.*;

/*
Question:
Maximize X
Moderate
0/80
Average time to solve is 20m
Problem statement
You have a total of 'B' candies and ‘N’ friends each having various demands for candies. You need to find the maximum possible value ‘X’ such that you can fulfill all the demands having quantity less than or equal to ‘X’ of any of the friends. More formally, the value of all demands to be full-filled should be less than or equal to X and their total sum must be less than or equal to ‘B’.

Example:-
You have 3 friends and 20 total candies. 

The first friend demands [1,2,3] candies, 
The second friend demands [4,2,3] candies and
The third friend demands [1,10,2] candies. 

So if you choose X as 9, demands fulfilled of 1st friend are [1,2,3], total = 6, demands fulfilled of 2nd friend are [4,2,3], total = 9, and demands fulfilled of the third friend are [1,2], total = 3. 

So total candies used is 6+9+3=18 which is less than B(20). So X=9 is possible. (You have to find the maximum such value of X).

Detailed explanation (Input/output format, Notes, Images)
Constraints:
1 <= T <= 10
1 <= N <= 10^4
1 <= B <= 10^9
1 <= S <= 10^5
1 <= ARR[i][j] <= 10^9

where i varies from 0 to N
ARR[i][j] is the jth demand of ith friend.

It is guaranteed that the sum of all the demands(values) for all friends is greater than B and the total number of demands of all friends is less than or equal to 10^5.

Time Limit: 1 sec

Sample Input 1:
1
3 20
3 1 2 3
3 4 2 3
3 1 10 2

Sample Output 1:
9

Explanation for Sample Output 1:
In the First test case, 

The maximum value of X that satisfies the conditions is 9, so 9 is printed. 

So if you choose X as 9, demands fulfilled of 1st friend are [1,2,3], demands fulfilled of 2nd friend are [4,2,3], and demands fulfilled of the third friend are [1,2]. 

So total candies used is 1+2+3+4+2+3+1+2=18 which is less than B(20). So X=9 is possible.

X=10 is not possible as if we choose 10, demands fulfilled of 1st friend are [1,2,3], demands fulfilled of 2nd friend are [4,2,3], and demands fulfilled of the third friend are [1,10,2]. 

So total candies used is 1+2+3+4+2+3+1+10+2=28 which is greater than B(20).  So, the largest value of X possible is 9.

Sample Input 2:
1
1 2
3 4 3 1

Sample Output 2:
2
*/

public class CandyDistribution {

    public static void main(String[] args) {
        // Hardcoded input
        int T = 1; // Number of test cases

        // Test Case 1
        int N = 3; // Number of friends
        int B = 20; // Total number of candies
        int[][] demands = {
            {1, 2, 3},
            {4, 2, 3},
            {1, 10, 2}
        };

        System.out.println(findMaxX(N, demands, B));

        // Test Case 2
        N = 1;
        B = 2;
        demands = new int[][] {
            {4, 3, 1}
        };

        System.out.println(findMaxX(N, demands, B));
    }
    
    static int findMaxX(int n, int[][] arr, int B) {
        int left = 1, right = (int) 1e9;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canSatisfyWithX(arr, B, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    static boolean canSatisfyWithX(int[][] demands, int B, int X) {
        long totalCandiesUsed = 0;
        
        for (int[] demandArray : demands) {
            for (int demand : demandArray) {
                if (demand <= X) {
                    totalCandiesUsed += demand;
                }
                if (totalCandiesUsed > B) {
                    return false;
                }
            }
        }
        
        return totalCandiesUsed <= B;
    }
}
