/*
 * 874. Walking Robot Simulation
Solved
Medium
Topics
Companies
A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:

-2: Turn left 90 degrees.
-1: Turn right 90 degrees.
1 <= k <= 9: Move forward k units, one unit at a time.
Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.

Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).

Note:

North means +Y direction.
East means +X direction.
South means -Y direction.
West means -X direction.
There can be obstacle in [0,0].
 

Example 1:

Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 3 units to (3, 4).
The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
Example 2:

Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
4. Turn left.
5. Move north 4 units to (1, 8).
The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
Example 3:

Input: commands = [6,-1,-1,6], obstacles = []
Output: 36
Explanation: The robot starts at (0, 0):
1. Move north 6 units to (0, 6).
2. Turn right.
3. Turn right.
4. Move south 6 units to (0, 0).
The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
 

Constraints:

1 <= commands.length <= 10^4
commands[i] is either -2, -1, or an integer in the range [1, 9].
0 <= obstacles.length <= 10^4
-3 * 10^4 <= xi, yi <= 3 * 10^4
The answer is guaranteed to be less than 2^31.
 */


 import java.util.HashSet;
import java.util.Set;

public class WalkingRobotSimulation {

    /**
     * This method simulates the robot's movement based on commands and obstacles,
     * and returns the maximum squared distance from the origin that the robot ever gets.
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions correspond to North, East, South, West
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // Initialize the robot's starting position and direction (facing North)
        int x = 0, y = 0, directionIndex = 0;
        int maxDistanceSquared = 0;

        // Use a set to store obstacles for O(1) lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        // Process each command
        for (int command : commands) {
            if (command == -2) { // Turn left
                directionIndex = (directionIndex + 3) % 4; // Equivalent to -1 in modulo 4
            } else if (command == -1) { // Turn right
                directionIndex = (directionIndex + 1) % 4;
            } else {
                // Move forward command units in the current direction
                for (int i = 0; i < command; i++) {
                    int nextX = x + directions[directionIndex][0];
                    int nextY = y + directions[directionIndex][1];

                    // Check if the next position is an obstacle
                    if (!obstacleSet.contains(nextX + "," + nextY)) {
                        // Move to the next position
                        x = nextX;
                        y = nextY;
                        // Calculate the squared distance from the origin
                        maxDistanceSquared = Math.max(maxDistanceSquared, x * x + y * y);
                    } else {
                        // Stop moving if there's an obstacle
                        break;
                    }
                }
            }
        }

        return maxDistanceSquared;
    }

    public static void main(String[] args) {
        WalkingRobotSimulation solution = new WalkingRobotSimulation();

        // Test case 1
        int[] commands1 = {4, -1, 3};
        int[][] obstacles1 = {};
        System.out.println("Output 1: " + solution.robotSim(commands1, obstacles1)); // Expected output: 25

        // Test case 2
        int[] commands2 = {4, -1, 4, -2, 4};
        int[][] obstacles2 = {{2, 4}};
        System.out.println("Output 2: " + solution.robotSim(commands2, obstacles2)); // Expected output: 65

        // Test case 3
        int[] commands3 = {6, -1, -1, 6};
        int[][] obstacles3 = {};
        System.out.println("Output 3: " + solution.robotSim(commands3, obstacles3)); // Expected output: 36
    }
}


//=======================================Same Code with HashMap source ShashCode ============================
/*
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int directions[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        int curPos[] = {0,0};
        int res = 0;
        int curDir = 0;

        HashMap<Integer, HashSet<Integer>> obstacleMap = new HashMap<>();
        for(int[] obstacle : obstacles){
            if(!obstacleMap.containsKey(obstacle[0])){
                obstacleMap.put(obstacle[0], new HashSet<>());
            }
            obstacleMap.get(obstacle[0]).add(obstacle[1]);
        }

        for(int command : commands){
            if(command == -1){
                curDir = (curDir + 1) % 4;
            }
            else if(command == -2){
                curDir = (curDir - 1);
                // if we reach to starting direction and want to move to last then
                if(curDir == -1){
                    curDir = 3;
                }
            }
            else{
                int direction[] = directions[curDir];
                for(int step = 0; step<command ; step++){
                    int nextX = curPos[0] + direction[0];
                    int nextY = curPos[1] + direction[1];

                    if(obstacleMap.containsKey(nextX) && obstacleMap.get(nextX).contains(nextY)){
                        break;
                    }
                    curPos[0] = nextX;
                    curPos[1] = nextY;
                }
                res = Math.max(res, curPos[0]*curPos[0] + curPos[1]*curPos[1]);
            }
        }
        return res;

    }
}
    */