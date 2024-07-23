public class PermutationString {

    // Method to find and print all permutations of a given string
    public static void findPermutation(String str, String ans) {
        // Base case: If the string is empty, print the permutation
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        // Recursive case: Generate permutations by fixing each character
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);

            // Generate a new string excluding the current character
            String newStr = str.substring(0, i) + str.substring(i + 1);

            // Recur with the new string and updated permutation
            findPermutation(newStr, ans + curr);
        }
    }

    public static void main(String[] args) {
        // Example 1: Permutations of "abc"
        System.out.println("Permutations of 'abc':");
        findPermutation("abc", "");

        // Example 2: Permutations of "123"
        System.out.println("\nPermutations of '123':");
        findPermutation("123", "");

        // Example 3: Permutations of "a1b"
        System.out.println("\nPermutations of 'a1b':");
        findPermutation("a1b", "");

        // Example 4: Permutations of "ab"
        System.out.println("\nPermutations of 'ab':");
        findPermutation("ab", "");

        // Example 5: Permutations of "xyz"
        System.out.println("\nPermutations of 'xyz':");
        findPermutation("xyz", "");
    }
}

