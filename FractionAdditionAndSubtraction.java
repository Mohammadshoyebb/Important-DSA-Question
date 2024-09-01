/*
 * 592. Fraction Addition and Subtraction
Medium
Topics
Companies
Given a string expression representing an expression of fraction addition and subtraction, return the calculation result in string format.

The final result should be an irreducible fraction. If your final result is an integer, change it to the format of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.

 

Example 1:

Input: expression = "-1/2+1/2"
Output: "0/1"
Example 2:

Input: expression = "-1/2+1/2+1/3"
Output: "1/3"
Example 3:

Input: expression = "1/3-1/2"
Output: "-1/6"
 

Constraints:

The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
Each fraction (input and output) has the format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1, 10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
The number of given fractions will be in the range [1, 10].
The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
 */

public class FractionAdditionAndSubtraction {
     
     // Helper function to calculate the Greatest Common Divisor (GCD)
     private int gcd(int a, int b) {
         return b == 0 ? a : gcd(b, a % b);
     }
 
     // Helper function to parse the fractions and perform the addition/subtraction
     public String fractionAddition(String expression) {
         int numerator = 0, denominator = 1; // Start with 0/1
         
         int index = 0;
         while (index < expression.length()) {
             // Parse the sign
             int sign = 1;
             if (expression.charAt(index) == '+' || expression.charAt(index) == '-') {
                 sign = expression.charAt(index) == '-' ? -1 : 1;
                 index++;
             }
 
             // Parse the numerator
             int num = 0;
             while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                 num = num * 10 + (expression.charAt(index) - '0');
                 index++;
             }
 
             // Skip the '/'
             index++; 
 
             // Parse the denominator
             int denom = 0;
             while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                 denom = denom * 10 + (expression.charAt(index) - '0');
                 index++;
             }
 
             // Adjust the current numerator and denominator
             num *= sign;
             numerator = numerator * denom + num * denominator;
             denominator *= denom;
 
             // Simplify the fraction
             int gcd = gcd(Math.abs(numerator), denominator);
             numerator /= gcd;
             denominator /= gcd;
         }
 
         return numerator + "/" + denominator;
     }
 
     public static void main(String[] args) {
         FractionAdditionAndSubtraction solution = new FractionAdditionAndSubtraction();
         
         // Test cases
         System.out.println(solution.fractionAddition("-1/2+1/2"));  // Output: "0/1"
         System.out.println(solution.fractionAddition("-1/2+1/2+1/3"));  // Output: "1/3"
         System.out.println(solution.fractionAddition("1/3-1/2"));  // Output: "-1/6"
     }
 }


 //======================================FASTER METHOD===============================================
 /*
  class Solution {

    class Fraction {
        public boolean isNegative;
        public int numerator;
        public int denominator;

        public void placeNumerator(char n) {
            if(numerator > 0) {
                numerator *= 10;
            }
            numerator += Character.getNumericValue(n);
        }
        public void placeDenominator(char n) {
            if(denominator > 0) {
                denominator *= 10;
            }
            denominator += Character.getNumericValue(n);
        }
        public int getSignedNumerator() {
            return isNegative ? -numerator : numerator;
        }
        public void add(Fraction other) {
            int finalResult = this.getSignedNumerator() +
                other.getSignedNumerator();

            this.isNegative = (finalResult < 0);
            this.numerator = Math.abs(finalResult);

            if(finalResult == 0) {
                this.denominator = 1;
            }
        }
        public void reduce() {
            if(denominator == 1) {
                return;
            }
            if(denominator % numerator == 0) {
                denominator = denominator / numerator;
                numerator = 1;
                return;
            }
            if(numerator % denominator == 0) {
                numerator = numerator / denominator;
                denominator = 1;
                return;
            }
            int multipleCap = Math.min(numerator, denominator) / 2;

            for(int i = 2; i <= multipleCap; i++) {
                // If not evenly divisible, continue.
                if(numerator % i != 0 || denominator % i != 0) {
                    continue;
                }
                numerator = numerator / i;
                denominator = denominator / i;
                multipleCap = Math.min(numerator, denominator) / 2;
                i = 1;
            }
        }
        public String toString() {
            StringBuilder str = new StringBuilder();
            if(isNegative) {
                str.append('-');
            }
            str.append(numerator);
            str.append('/');
            str.append(denominator);
            return str.toString();
        }
    }
    public String fractionAddition(String expression) {

        ArrayList<Fraction> allFractions = parseFractions(expression);
        Fraction baseFraction = allFractions.get(0);

        for(int i = 1; i < allFractions.size(); i++) {
            Fraction actedUponFraction = allFractions.get(i);

            int baseDenominator = baseFraction.denominator;
            baseFraction.numerator *= actedUponFraction.denominator;
            baseFraction.denominator *= actedUponFraction.denominator;
            actedUponFraction.numerator *= baseDenominator;
            actedUponFraction.denominator *= baseDenominator;

            baseFraction.add(actedUponFraction);
            baseFraction.reduce();
        }
        return baseFraction.toString();
    }
    public ArrayList<Fraction> parseFractions(String expression) {
        ArrayList<Fraction> allFractions = new ArrayList<>();
        int i = 0;
        do {
            Fraction fraction = new Fraction();
            char currentChar = expression.charAt(i);

            if(currentChar == '-') {
                fraction.isNegative = true;
                i = i + 1;
            }
            if(currentChar == '+') {
                i = i + 1;
            }
            currentChar = expression.charAt(i);
            do {
                fraction.placeNumerator(currentChar);
                i = i + 1;
                currentChar = expression.charAt(i);
            } while(currentChar != '/');
            i = i + 1;

            currentChar = expression.charAt(i);
            do {
                fraction.placeDenominator(currentChar);
                i = i + 1;

                if(i >= expression.length()) {
                    break;
                }
                currentChar = expression.charAt(i);

            } while(i < expression.length() && 
                currentChar != '+' &&
                currentChar != '-');

            allFractions.add(fraction);

        } while(i < expression.length());

        return allFractions;
    }
}
  */
 