/**
 * 539. Minimum Time Difference
 * 
 * Given a list of 24-hour clock time points in "HH:MM" format, return the 
 * minimum minutes difference between any two time points in the list.
 * 
 * Example 1:
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * 
 * Example 2:
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 * 
 * Constraints:
 * 2 <= timePoints.length <= 2 * 10^4
 * timePoints[i] is in the format "HH:MM".
 */

 import java.util.*;

public class MinimumTimeDifference {
     /**
      * This method calculates the minimum time difference between any two time points
      * given in "HH:MM" format.
      * 
      * Time Complexity: O(n log n) due to sorting, where n is the number of time points.
      * Space Complexity: O(n) due to the storage of time points in minutes.
      * 
      * @param timePoints A list of strings representing time points in "HH:MM" format.
      * @return The minimum time difference between any two time points in minutes.
      */
     public int findMinDifference(List<String> timePoints) {
         // List to store all time points in minutes format
         List<Integer> minutesList = new ArrayList<>();
         
         // Convert each time point "HH:MM" to its total minutes from 00:00 (midnight)
         for (String time : timePoints) {
             String[] parts = time.split(":");
             int hours = Integer.parseInt(parts[0]);
             int minutes = Integer.parseInt(parts[1]);
             // Convert "HH:MM" to total minutes
             minutesList.add(hours * 60 + minutes);
         }
         
         // Sort the list to compare consecutive time points
         Collections.sort(minutesList);
         
         // Initialize the minimum difference as a large value
         int minDifference = Integer.MAX_VALUE;
         
         // Calculate the difference between consecutive time points
         for (int i = 1; i < minutesList.size(); i++) {
             int diff = minutesList.get(i) - minutesList.get(i - 1);
             minDifference = Math.min(minDifference, diff);
         }
         
         // To handle circular time (between the last and the first time points), 
         // we calculate the time difference between the first time point of the day
         // and the last time point wrapping over midnight
         int firstTime = minutesList.get(0);
         int lastTime = minutesList.get(minutesList.size() - 1);
         int circularDifference = 1440 - lastTime + firstTime;
         
         // Return the minimum of the consecutive differences or the circular difference
         return Math.min(minDifference, circularDifference);
     }
 
     // Main method for testing
     public static void main(String[] args) {
         MinimumTimeDifference solution = new MinimumTimeDifference();
 
         // Test case 1
         List<String> timePoints1 = Arrays.asList("23:59", "00:00");
         System.out.println(solution.findMinDifference(timePoints1));  // Output: 1
 
         // Test case 2
         List<String> timePoints2 = Arrays.asList("00:00", "23:59", "00:00");
         System.out.println(solution.findMinDifference(timePoints2));  // Output: 0
     }
 }
 