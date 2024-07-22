import java.util.ArrayList;
import java.util.List;


//78. Subsets(Leetcode)
public class SubsetsGenerator {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3}; // Example input
        List<List<Integer>> subsets = generateSubsets(nums);
        System.out.println("All subsets:");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n; // 2^n

        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if the j-th bit of i is set
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            allSubsets.add(subset);
        }

        return allSubsets;
    }
}

