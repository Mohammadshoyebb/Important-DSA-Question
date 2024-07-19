/**
 * 2024. Maximize the Confusion of an Exam
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
 * 
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
 * 
 * Example 1:
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
 * There are four consecutive 'T's.
 * 
 * Example 2:
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
 * Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
 * In both cases, there are three consecutive 'F's.
 * 
 * Example 3:
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
 * Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT". 
 * In both cases, there are five consecutive 'T's.
 * 
 * Constraints:
 * n == answerKey.length
 * 1 <= n <= 5 * 104
 * answerKey[i] is either 'T' or 'F'
 * 1 <= k <= n
 */

 public class MaximizeConfusionOfExam {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    private int maxConsecutiveChar(String answerKey, int k, char ch) {
        int left = 0, right;
        int maxCount = 0;
        int count = 0;

        for (right = 0; right < answerKey.length(); right++) {
            if (answerKey.charAt(right) != ch) {
                count++;
            }

            while (count > k) {
                if (answerKey.charAt(left) != ch) {
                    count--;
                }
                left++;
            }

            maxCount = Math.max(maxCount, right - left + 1);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        MaximizeConfusionOfExam solution = new MaximizeConfusionOfExam();

        // Test cases
        String answerKey1 = "TTFF";
        int k1 = 2;
        System.out.println(solution.maxConsecutiveAnswers(answerKey1, k1)); // Output: 4

        String answerKey2 = "TFFT";
        int k2 = 1;
        System.out.println(solution.maxConsecutiveAnswers(answerKey2, k2)); // Output: 3

        String answerKey3 = "TTFTTFTT";
        int k3 = 1;
        System.out.println(solution.maxConsecutiveAnswers(answerKey3, k3)); // Output: 5
    }
}

/*
 class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int leftTrue = 0;
        int leftFalse = 0;
        int maxCount = -1;
        int falseCount = 0, trueCount = 0;
        for (int right = 0; right < answerKey.length(); right++) {
            char ch = answerKey.charAt(right);

            // Flipping False values
            if (ch == 'F') {
                falseCount++;
            }
            while (falseCount > k) {
                if (answerKey.charAt(leftFalse) == 'F') {
                    falseCount--;
                }
                leftFalse++;
            }
            maxCount = Math.max(maxCount, right - leftFalse + 1);

            // Flipping True values
            if (ch == 'T') {
                trueCount++;
            }
            while (trueCount > k) {
                if (answerKey.charAt(leftTrue) == 'T') {
                    trueCount--;
                }
                leftTrue++;
            }
            maxCount = Math.max(maxCount, right - leftTrue + 1);
        }
        return maxCount;
    }
}
 */

