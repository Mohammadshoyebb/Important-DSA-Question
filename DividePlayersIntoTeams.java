/*
2491. Divide Players Into Teams of Equal Skill
You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. 
Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.

The chemistry of a team is equal to the product of the skills of the players on that team.

Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.

Example 1:
Input: skill = [3,2,5,1,3,4]
Output: 22
Explanation: 
Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.

Example 2:
Input: skill = [3,4]
Output: 12
Explanation: The two players form a team with a total skill of 7.
The chemistry of the team is 3 * 4 = 12.

Example 3:
Input: skill = [1,1,2,3]
Output: -1
Explanation: There is no way to divide the players into teams such that the total skill of each team is equal.

Constraints:
2 <= skill.length <= 10^5
skill.length is even.
1 <= skill[i] <= 1000
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DividePlayersIntoTeams {

    // 1. Using a frequency array approach (sorting)
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public static int dividePlayersFreqArray(int[] skill) {
        // Sort the skill array to make it easier to pair players from both ends
        Arrays.sort(skill);
        int n = skill.length;  // Total number of players
        int totalSkill = skill[0] + skill[n - 1];  // Expected team skill sum for each pair
        int chemistrySum = 0;  // Variable to store the total chemistry

        // Loop through the array to form teams
        for (int i = 0; i < n / 2; i++) {
            // Check if the skill sum of the current team matches the expected value
            if (skill[i] + skill[n - 1 - i] != totalSkill) {
                return -1;  // If it doesn't match, return -1
            }
            // Calculate the chemistry of the current team and add it to the total
            chemistrySum += skill[i] * skill[n - 1 - i];
        }
        return chemistrySum;  // Return the total chemistry of all teams
    }

    // 2. Using a HashMap approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int dividePlayersHashMap(int[] skill) {
        int sum = 0;  // Variable to store the sum of all player skills
        // Calculate the total sum of skills
        for (int s : skill) {
            sum += s;
        }
        // If the sum is not divisible by the number of teams, return -1
        if (sum % (skill.length / 2) != 0) {
            return -1;
        }

        int targetSkill = sum / (skill.length / 2);  // Calculate the target skill for each team
        Map<Integer, Integer> skillCount = new HashMap<>();  // HashMap to store skill counts
        int chemistrySum = 0;  // Variable to store the total chemistry

        // Populate the HashMap with the frequency of each skill
        for (int s : skill) {
            skillCount.put(s, skillCount.getOrDefault(s, 0) + 1);
        }

        // Loop through the skill array to form teams
        for (int s : skill) {
            // If the current skill is already used in a team, skip it
            if (skillCount.get(s) == 0) {
                continue;
            }
            int complement = targetSkill - s;  // Calculate the complement skill needed to form a valid team
            // If the complement skill exists in the HashMap, form a team
            if (skillCount.getOrDefault(complement, 0) > 0) {
                chemistrySum += s * complement;  // Calculate and add the team's chemistry
                skillCount.put(s, skillCount.get(s) - 1);  // Decrement the count of the current skill
                skillCount.put(complement, skillCount.get(complement) - 1);  // Decrement the count of the complement skill
            } else {
                return -1;  // If no valid team can be formed, return -1
            }
        }
        return chemistrySum;  // Return the total chemistry of all teams
    }

    // 3. Two-pointer approach after sorting
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public static int dividePlayersTwoPointer(int[] skill) {
        // Sort the skill array to make pairing easier
        Arrays.sort(skill);
        int n = skill.length;  // Total number of players
        int totalSkill = skill[0] + skill[n - 1];  // Expected skill sum for each team
        int chemistrySum = 0;  // Variable to store the total chemistry

        int left = 0;  // Left pointer
        int right = n - 1;  // Right pointer

        // Use two pointers to form teams
        while (left < right) {
            // If the skill sum of the current team doesn't match the expected total, return -1
            if (skill[left] + skill[right] != totalSkill) {
                return -1;
            }
            // Calculate and add the team's chemistry
            chemistrySum += skill[left] * skill[right];
            left++;  // Move left pointer
            right--;  // Move right pointer
        }
        return chemistrySum;  // Return the total chemistry of all teams
    }

    // 4. Using frequency array approach without sorting
    // Time Complexity: O(n), Space Complexity: O(1)
    public static long dividePlayersFreqNoSort(int[] skill) {
        int sum = 0;  // Variable to store the sum of all player skills
        int size = skill.length / 2;  // Number of teams
        int[] freq = new int[1001];  // Frequency array for skill counts (1 <= skill[i] <= 1000)

        // Calculate the total sum of skills and populate the frequency array
        for (int i = 0; i < skill.length; i++) {
            sum += skill[i];
            freq[skill[i]]++;
        }
        // If the sum is not divisible by the number of teams, return -1
        if (sum % size != 0) return -1;

        long res = 0;  // Variable to store the total chemistry
        int target = sum / size;  // Calculate the target skill for each team

        // Loop through the skill array to form teams
        for (int ele : skill) {
            // If the current skill is already used in a team, skip it
            if (freq[ele] == 0) {
                continue;
            }
            freq[ele]--;  // Decrease the frequency of the current skill
            int finder = target - ele;  // Calculate the complement skill needed to form a valid team
            // If the complement skill is not available, return -1
            if (freq[finder] == 0) {
                return -1;
            }
            freq[finder]--;  // Decrease the frequency of the complement skill
            res += ((long) finder * (long) ele);  // Calculate and add the team's chemistry
        }
        return res;  // Return the total chemistry of all teams
    }

    // Main method to test all approaches
    public static void main(String[] args) {
        int[] skill1 = {3, 2, 5, 1, 3, 4};  // Test case 1
        System.out.println("Result using frequency array (sorted): " + dividePlayersFreqArray(skill1));  // Output: 22
        System.out.println("Result using HashMap: " + dividePlayersHashMap(skill1));  // Output: 22
        System.out.println("Result using two-pointer: " + dividePlayersTwoPointer(skill1));  // Output: 22
        System.out.println("Result using frequency array (no sorting): " + dividePlayersFreqNoSort(skill1));  // Output: 22

        int[] skill2 = {3, 4};  // Test case 2
        System.out.println("Result using frequency array (sorted): " + dividePlayersFreqArray(skill2));  // Output: 12
        System.out.println("Result using HashMap: " + dividePlayersHashMap(skill2));  // Output: 12
        System.out.println("Result using two-pointer: " + dividePlayersTwoPointer(skill2));  // Output: 12
        System.out.println("Result using frequency array (no sorting): " + dividePlayersFreqNoSort(skill2));  // Output: 12

        int[] skill3 = {1, 1, 2, 3};  // Test case 3
        System.out.println("Result using frequency array (sorted): " + dividePlayersFreqArray(skill3));  // Output: -1
        System.out.println("Result using HashMap: " + dividePlayersHashMap(skill3));  // Output: -1
        System.out.println("Result using two-pointer: " + dividePlayersTwoPointer(skill3));  // Output: -1
        System.out.println("Result using frequency array (no sorting): " + dividePlayersFreqNoSort(skill3));  // Output: -1
    }
}
