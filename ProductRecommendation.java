/* 
Question:

You are tasked with developing two functions to suggest relevant products for cross-selling and up-selling on a product-based e-commerce website. 
Products are represented by four properties: productId, productName, productCategory, and productPrice. 
Your goal is to recommend cross-sell and up-sell products from the inventory based on the user's previous purchases.

Definitions:

1. crossSell(purchasedProducts, inventory)

Input:
- purchasedProducts: A list of strings, where each string is a comma-separated representation of a purchased product. 
  Each string is in the format: productId,productName,productCategory,productPrice.
  
- inventory: A list of strings, where each string is a comma-separated representation of a product in the store's inventory.
  Each string is in the format: productId,productName,productCategory,productPrice.

Output:
- A list of strings representing products from the inventory that belong to the same category as any of the purchased products 
  and are not priced higher than the most expensive product in that category among the purchased products. 
  The list should be sorted by productPrice in ascending order.

2. upSell(purchasedProducts, inventory)

Input:
- purchasedProducts: A list of strings, where each string is a comma-separated representation of a purchased product. 
  Each string is in the format: productId,productName,productCategory,productPrice.
  
- inventory: A list of strings, where each string is a comma-separated representation of a product in the store's inventory. 
  Each string is in the format: productId,productName,productCategory,productPrice.

Output:
- A list of strings representing products from the inventory that belong to the same category as any of the purchased products 
  and are priced higher than the most expensive product in that category among the purchased products. 
  The list should be sorted by productPrice in ascending order.

Example:

Input: 
purchasedProducts = ["1,product1,electronics,100", "2,product2,furniture,200"]
inventory = ["3,product3,electronics,80", "4,product4,appliances,300", "5,product5,automobiles,400", "6,product6,electronics,120", "7,product7,furniture,250"]

Output:
CROSS_SELL:
["3,product3,electronics,80", "6,product6,electronics,120"]

UP_SELL:
["7,product7,furniture,250"]

*/

import java.util.*;

public class ProductRecommendation {

    // Helper method to convert product string to map (productId, productName, productCategory, productPrice)
    private static Map<String, String> parseProductDetails(String product) {
        String[] details = product.split(",");
        Map<String, String> productMap = new HashMap<>();
        productMap.put("productId", details[0]);
        productMap.put("productName", details[1]);
        productMap.put("productCategory", details[2]);
        productMap.put("productPrice", details[3]);
        return productMap;
    }

    public static String[] crossSell(String[] purchasedProducts, String[] inventory) {
        List<String> crossSellProducts = new ArrayList<>();
        Map<String, Integer> maxCategoryPriceMap = new HashMap<>(); // Track max price per category
        Set<String> purchasedIds = new HashSet<>();

        // Populate maxCategoryPriceMap with the maximum price for each purchased product's category
        for (String product : purchasedProducts) {
            Map<String, String> productDetails = parseProductDetails(product);
            purchasedIds.add(productDetails.get("productId"));

            String category = productDetails.get("productCategory");
            int price = Integer.parseInt(productDetails.get("productPrice"));
            maxCategoryPriceMap.put(category, Math.max(maxCategoryPriceMap.getOrDefault(category, 0), price));
        }

        // Cross-sell logic: Same category, not purchased, price <= max purchased price in that category
        for (String product : inventory) {
            Map<String, String> productDetails = parseProductDetails(product);
            String productId = productDetails.get("productId");
            String category = productDetails.get("productCategory");
            int price = Integer.parseInt(productDetails.get("productPrice"));

            if (!purchasedIds.contains(productId) && maxCategoryPriceMap.containsKey(category)
                    && price <= maxCategoryPriceMap.get(category)) {
                crossSellProducts.add(product);
            }
        }

        // Sort cross-sell products by price in ascending order
        crossSellProducts.sort(Comparator.comparingInt(p -> Integer.parseInt(p.split(",")[3])));

        return crossSellProducts.toArray(new String[0]);
    }

    public static String[] upSell(String[] purchasedProducts, String[] inventory) {
        List<String> upSellProducts = new ArrayList<>();
        Map<String, Integer> maxCategoryPriceMap = new HashMap<>(); // Track max price per category
        Set<String> purchasedIds = new HashSet<>();

        // Populate maxCategoryPriceMap with the maximum price for each purchased product's category
        for (String product : purchasedProducts) {
            Map<String, String> productDetails = parseProductDetails(product);
            purchasedIds.add(productDetails.get("productId"));

            String category = productDetails.get("productCategory");
            int price = Integer.parseInt(productDetails.get("productPrice"));
            maxCategoryPriceMap.put(category, Math.max(maxCategoryPriceMap.getOrDefault(category, 0), price));
        }

        // Up-sell logic: Same category, not purchased, price > max purchased price in that category
        for (String product : inventory) {
            Map<String, String> productDetails = parseProductDetails(product);
            String productId = productDetails.get("productId");
            String category = productDetails.get("productCategory");
            int price = Integer.parseInt(productDetails.get("productPrice"));

            if (!purchasedIds.contains(productId) && maxCategoryPriceMap.containsKey(category)
                    && price > maxCategoryPriceMap.get(category)) {
                upSellProducts.add(product);
            }
        }

        // Sort up-sell products by price in ascending order
        upSellProducts.sort(Comparator.comparingInt(p -> Integer.parseInt(p.split(",")[3])));

        return upSellProducts.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] purchasedProducts = {
            "1,product1,electronics,100",
            "2,product2,furniture,200"
        };

        String[] inventory = {
            "3,product3,electronics,80",
            "4,product4,appliances,300",
            "5,product5,automobiles,400",
            "6,product6,electronics,120",
            "7,product7,furniture,250"
        };

        String[] crossSellResult = crossSell(purchasedProducts, inventory);
        String[] upSellResult = upSell(purchasedProducts, inventory);

        System.out.println("CROSS_SELL:");
        for (String product : crossSellResult) {
            System.out.println(product);
        }

        System.out.println("UP_SELL:");
        for (String product : upSellResult) {
            System.out.println(product);
        }
    }
}
