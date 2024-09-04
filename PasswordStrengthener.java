/**
 * Problem Statement:
 * 
 * To enhance the robustness of a password, adherence to a set of stringent conditions is mandated. A password 
 * achieves strength if it meticulously fulfills the following criteria:
 * 
 * 1. The length of the password must be no less than 6 characters and no more than 20 characters.
 * 
 * 2. The password must encompass at least one lowercase letter, one uppercase letter, and one digit to fortify its complexity.
 * 
 * 3. Stringent avoidance of three consecutive repeating characters is imperative, for instance, the sequence "Baaabbo" 
 *    is deemed weak, while "Baabul" is acknowledged as strong.
 * 
 * The task at hand is to ascertain the minimum number of steps required to transform a given string, denoted as 'password', 
 * into a robust and secure form. If the password already complies with the stringent criteria, the function should yield zero as the result.
 * 
 * Each step in this context encompasses the ability to perform one of the following actions:
 * 
 * 1. "Insertion": Add one character to the existing password.
 * 
 * 2. "Deletion": Remove one character from the existing password.
 * 
 * 3. "Replacement": Substitute one character in the password with another character.
 * 
 * The objective is to determine the most efficient sequence of these operations, minimizing the steps necessary to 
 * transform the password into a formidable and secure configuration.
 * 
 * Example 1:
 * Input: password = "a"
 * Output: 5
 * 
 * Example 2:
 * Input: password = "Aa"
 * Output: 4
 * 
 * Example 3:
 * Input: password = "13370043"
 * Output: 0
 * 
 * Constraints: password.length < 50
 * password consists of letters, digits, dot, or exclamation mark.
 */

 public class PasswordStrengthener {
    public static void main(String[] args) {
        // Example test cases
        System.out.println(minSteps("a"));        // Output: 5
        System.out.println(minSteps("Aa"));       // Output: 4
        System.out.println(minSteps("13370043")); // Output: 0
    }

    public static int minSteps(String password) {
        int length = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;

        // Check for missing character types
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        // Check for repeating sequences
        int replace = 0;
        int i = 2;
        while (i < length) {
            int count = 1;
            while (i < length && password.charAt(i) == password.charAt(i - 1)) {
                count++;
                i++;
            }
            if (count >= 3) {
                replace += count / 3;
            }
        }

        // Case 1: Length is less than 6
        if (length < 6) {
            return Math.max(6 - length, missingTypes);
        }

        // Case 2: Length is between 6 and 20
        if (length <= 20) {
            return Math.max(replace, missingTypes);
        }

        // Case 3: Length is greater than 20
        int excess = length - 20;
        replace -= Math.min(excess, replace);
        return excess + Math.max(replace, missingTypes);
    }
}
