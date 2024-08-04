/*
 * Job Sequencing Problem
 * Difficulty: Medium
 * Accuracy: 34.51%
 * Submissions: 221K+
 * Points: 4
 * 
 * Given a set of n jobs where each jobi has a deadline and profit associated with it.
 * Each job takes 1 unit of time to complete and only one job can be scheduled at a time. 
 * We earn the profit associated with a job if and only if the job is completed by its deadline.
 *
 * Find the number of jobs done and the maximum profit.
 *
 * Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job. 
 * Deadline of the job is the time on or before which job needs to be completed to earn the profit.
 *
 * Example 1:
 * Input:
 * N = 4
 * Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
 * Output:
 * 2 60
 * Explanation:
 * Job1 and Job3 can be done with maximum profit of 60 (20+40).
 *
 * Example 2:
 * Input:
 * N = 5
 * Jobs = {(1,2,100),(2,1,19),(3,2,27),(4,1,25),(5,1,15)}
 * Output:
 * 2 127
 * Explanation:
 * 2 jobs can be done with maximum profit of 127 (100+27).
 *
 * Your Task:
 * You don't need to read input or print anything. Your task is to complete the function JobScheduling() 
 * which takes an integer n and an array Jobs(Job id, Deadline, Profit) as input and returns the count of jobs 
 * and maximum profit as a list or vector of 2 elements.
 *
 * Expected Time Complexity: O(nlogn)
 * Expected Auxiliary Space: O(n)
 *
 * Constraints:
 * 1 <= n <= 105
 * 1 <= Deadline <= n
 * 1 <= Profit <= 500
 */

 import java.util.*;

 class Job {
     int id, deadline, profit;
 
     Job(int id, int deadline, int profit) {
         this.id = id;
         this.deadline = deadline;
         this.profit = profit;
     }
 }
 
 public class JobSequencingProblem {
     
     public static int[] JobScheduling(Job arr[], int n) {
         Arrays.sort(arr, (a, b) -> b.profit - a.profit);
         
         boolean[] slot = new boolean[n];
         int[] result = new int[2];  // result[0] = count of jobs, result[1] = total profit
         
         for (int i = 0; i < n; i++) {
             for (int j = Math.min(n, arr[i].deadline) - 1; j >= 0; j--) {
                 if (!slot[j]) {
                     slot[j] = true;
                     result[0]++;
                     result[1] += arr[i].profit;
                     break;
                 }
             }
         }
         
         return result;
     }
 
     public static void main(String[] args) {
         Job[] jobs1 = { new Job(1, 4, 20), new Job(2, 1, 10), new Job(3, 1, 40), new Job(4, 1, 30) };
         int[] result1 = JobScheduling(jobs1, jobs1.length);
         System.out.println("Number of jobs: " + result1[0] + ", Maximum profit: " + result1[1]); // Output: 2 60
         
         Job[] jobs2 = { new Job(1, 2, 100), new Job(2, 1, 19), new Job(3, 2, 27), new Job(4, 1, 25), new Job(5, 1, 15) };
         int[] result2 = JobScheduling(jobs2, jobs2.length);
         System.out.println("Number of jobs: " + result2[0] + ", Maximum profit: " + result2[1]); // Output: 2 127
         
         Job[] jobs3 = { new Job(1, 3, 35), new Job(2, 4, 30), new Job(3, 4, 25), new Job(4, 2, 20), new Job(5, 3, 15) };
         int[] result3 = JobScheduling(jobs3, jobs3.length);
         System.out.println("Number of jobs: " + result3[0] + ", Maximum profit: " + result3[1]); // Output: 3 80
         
         Job[] jobs4 = { new Job(1, 1, 5), new Job(2, 2, 10), new Job(3, 3, 15), new Job(4, 4, 20) };
         int[] result4 = JobScheduling(jobs4, jobs4.length);
         System.out.println("Number of jobs: " + result4[0] + ", Maximum profit: " + result4[1]); // Output: 4 50
     }
 }
 