// 273. Integer to English Words
// Solved
// Hard
// Topics
// Companies
// Hint
// Convert a non-negative integer num to its English words representation.

import java.util.*;

public class IntegerToEnglishWords {
    
    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }

    private String helper(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 20) {
            result.append(belowTwenty[num]);
        } else if (num < 100) {
            result.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        } else if (num < 1000) {
            result.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
        } else if (num < 1000000) {
            result.append(helper(num / 1000)).append(" Thousand ").append(helper(num % 1000));
        } else if (num < 1000000000) {
            result.append(helper(num / 1000000)).append(" Million ").append(helper(num % 1000000));
        } else {
            result.append(helper(num / 1000000000)).append(" Billion ").append(helper(num % 1000000000));
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        IntegerToEnglishWords solution = new IntegerToEnglishWords();
        
        int[] testNumbers = {123, 12345, 1234567, 0, 1000000000};
        
        for (int num : testNumbers) {
            System.out.println(num + " -> " + solution.numberToWords(num));
        }
    }
}

// Shash Code same logic

/*
 class Solution {

    private final String[] belowTen = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = {"", "","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int nums) {
        if (nums == 0) {
            return "Zero";
        }
        if(nums<10){
            return belowTen[nums];
        }
        if(nums < 20){
            return belowTwenty[nums-10];
        }
        if(nums < 100){
            return belowHundred[nums/10] +  (nums%10 == 0 ? "" : " "+numberToWords(nums%10));
        }
        if(nums<1000){
            return belowTen[nums/100] + " Hundred" + (nums%100 == 0 ? "" : " "+numberToWords(nums%100));
        }
        if(nums<1000000){
             return numberToWords(nums/1000) + " Thousand" + (nums%1000 == 0 ? "" : " "+numberToWords(nums%1000));
        }
        if(nums<1000000000){
             return numberToWords(nums/1000000) + " Million" + (nums%1000000 == 0 ? "" : " "+numberToWords(nums%1000000));
        }
        
        return numberToWords(nums/1000000000) + " Billion" + (nums%1000000000 == 0 ? "" : " "+numberToWords(nums%1000000000));

    }
}
 */