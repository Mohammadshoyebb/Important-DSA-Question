/**
 * There is a shop with old-style cash registers. Rather than scanning items and 
 * pulling the price from a database, the price of each item is typed in manually. 
 * This method sometimes leads to errors. Given a list of items and their correct 
 * prices, compare the prices to those entered when each item was sold.
 * Determine the number of errors in selling prices.
 * 
 * Example:
 * 
 * products = ['eggs', 'milk', 'cheese']
 * productPrices = [2.89, 3.29, 5.79]
 * productSold = ['eggs', 'eggs', 'cheese', 'milk']
 * soldPrice = [2.89, 2.99, 5.97, 3.29]
 * 
 * Output: 2
 * 
 * The second sale of eggs has a wrong price, as does the sale of cheese. 
 * There are 2 errors in pricing.
 * 
 * Function Description:
 * Complete the function priceCheck in the editor below.
 * 
 * priceCheck has the following parameters:
 * 1. string products[n]: each products[i] is the name of an item for sale
 * 2. float productPrices[n]: each productPrices[i] is the price of products[i]
 * 3. string productSold[m]: each productSold[j] is the name of a product sold
 * 4. float soldPrice[m]: each soldPrice[j] contains the sale price recorded for productSold[j]
 * 
 * Returns:
 * int: the number of sale prices that were entered incorrectly
 * 
 * Constraints:
 * 1 ≤ n ≤ 10^5
 * 1 ≤ m ≤ n
 * 1.00 ≤ productPrices[i], soldPrice[j] ≤ 100000.00, where 0 ≤ i < n, and 0 ≤ j < m
 */

 import java.util.HashMap;

 public class PriceCheck {
 
     public static int priceCheck(String[] products, double[] productPrices, String[] productSold, double[] soldPrice) {
         // Creating a hashmap to store correct product prices
         HashMap<String, Double> priceMap = new HashMap<>();
         
         // Populating the price map with product names and their corresponding prices
         for (int i = 0; i < products.length; i++) {
             priceMap.put(products[i], productPrices[i]);
         }
         
         // Variable to count the number of errors in the sold price
         int errorCount = 0;
         
         // Checking the sold prices against the actual prices
         for (int i = 0; i < productSold.length; i++) {
             double actualPrice = priceMap.get(productSold[i]);
             if (actualPrice != soldPrice[i]) {
                 errorCount++; // Incrementing the error count if the price doesn't match
             }
         }
         
         return errorCount; // Returning the total number of errors
     }
 
     public static void main(String[] args) {
         // Sample Input
         String[] products = {"eggs", "milk", "cheese"};
         double[] productPrices = {2.89, 3.29, 5.79};
         String[] productSold = {"eggs", "eggs", "cheese", "milk"};
         double[] soldPrice = {2.89, 2.99, 5.97, 3.29};
         
         // Calling the priceCheck method and printing the result
         int result = priceCheck(products, productPrices, productSold, soldPrice);
         System.out.println("Number of errors: " + result);
     }
 }
 