// 264. Ugly Number II
// Given an integer n, this class provides three approaches to find the nth ugly number.
// An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

import java.util.HashSet;
import java.util.PriorityQueue;

public class UglySNumberII {

    // Approach 1: Precompute Ugly Numbers using Dynamic Programming
    // This approach precomputes the first 1690 ugly numbers and stores them in an array.
    // The nth ugly number is then retrieved in constant time.
    static class Ugly {
        public int[] nums = new int[1690];
        
        Ugly() {
            nums[0] = 1;
            int i2 = 0, i3 = 0, i5 = 0; // Indexes for 2, 3, and 5
            
            for (int i = 1; i < 1690; i++) {
                int ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
                nums[i] = ugly;
                
                if (ugly == nums[i2] * 2) ++i2;
                if (ugly == nums[i3] * 3) ++i3;
                if (ugly == nums[i5] * 5) ++i5;
            }
        }
    }

    static class Solution1 {
        public static Ugly u = new Ugly();
        
        public int nthUglyNumber(int n) {
            return u.nums[n - 1];
        }
    }

    // Approach 2: Dynamic Programming with Inline Calculation
    // This approach dynamically calculates ugly numbers up to the nth ugly number during runtime.
    public static class Solution2 {
        public int nthUglyNumber(int n) {
            int[] arr = new int[n];
            arr[0] = 1;
            
            int i2 = 0, i3 = 0, i5 = 0; // Indexes for 2, 3, and 5
            int f2 = 2, f3 = 3, f5 = 5; // Initial multiples of 2, 3, and 5
            
            for (int i = 1; i < n; i++) {
                arr[i] = Math.min(Math.min(f2, f3), f5);
                
                if (f2 == arr[i]) f2 = 2 * arr[++i2];
                if (f3 == arr[i]) f3 = 3 * arr[++i3];
                if (f5 == arr[i]) f5 = 5 * arr[++i5];
            }
            return arr[n - 1];
        }
    }

    // Approach 3: Using Min-Heap (Priority Queue) with Set for Duplicates
    // This approach uses a priority queue to efficiently calculate the nth ugly number.
    public static class Solution3 {
        public int nthUglyNumber(int n) {
            int[] primes = {2, 3, 5};
            PriorityQueue<Long> uglyHeap = new PriorityQueue<>();
            HashSet<Long> visited = new HashSet<>();
            
            uglyHeap.add(1L);
            visited.add(1L);
            
            long curr = 1L;
            for (int i = 0; i < n; i++) {
                curr = uglyHeap.poll();
                for (int prime : primes) {
                    long newUgly = curr * prime;
                    if (!visited.contains(newUgly)) {
                        uglyHeap.add(newUgly);
                        visited.add(newUgly);
                    }
                }
            }
            return (int) curr;
        }
    }

    public static void main(String[] args) {
        Solution1 sol1 = new Solution1();
        Solution2 sol2 = new Solution2();
        Solution3 sol3 = new Solution3();
        
        int n = 10;

        // Testing Approach 1
        System.out.println("Approach 1: " + sol1.nthUglyNumber(n)); // Output: 12

        // Testing Approach 2
        System.out.println("Approach 2: " + sol2.nthUglyNumber(n)); // Output: 12

        // Testing Approach 3
        System.out.println("Approach 3: " + sol3.nthUglyNumber(n)); // Output: 12
    }
}

