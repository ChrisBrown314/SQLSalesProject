package com.github.sqlsalesproject.sale;

import java.time.LocalDate;
import java.util.ArrayList;

import it.unimi.dsi.util.XoShiRo256StarStarRandom;


/**Generate class.
 * Generates purchase information for demonstrating the program.
 * @author Christopher Brown
 */
public class Generate {

    /**XoShiRo256** Random generator. Random Number generator which utilizes the xoshiro256**
     * algorithm from the DSI Utilities library.*/
    private static final XoShiRo256StarStarRandom RANDOM = new XoShiRo256StarStarRandom();

    //TODO - redo this!

    /** Generates the purchase history for a given month.
     * See: {@link PurchaseHistory}
     * @param suppliesPurchased The amount of supplies available.
     * @param year The year the purchase history covers.
     * @param month The month the purchase history covers.
     * @param minNumPurchases The minimum number of purchases to generate for the purchase history.
     * @param maxNumPurchases The maximum number of purchases to generate for the purchase history.
     * @return Randomly generated purchase history containing a month's worth of purchases.
     */
    public static PurchaseHistory generatePurchaseHistory(Integer suppliesPurchased, int year, int month, int minNumPurchases, int maxNumPurchases) {
        SupplyTracker supplyTracker = new SupplyTracker(suppliesPurchased);
        PurchaseHistory generatedPurchaseHistory = new PurchaseHistory(0, month, year);
        boolean supplyNotExceeded = true;
        int purchaseCount = 0;
        int maxPurchaseCount = RANDOM.nextInt(maxNumPurchases-minNumPurchases)+minNumPurchases;
        while (purchaseCount < maxPurchaseCount && supplyNotExceeded) {
            Purchase generatedPurchase = generatePurchase(year, month);
            supplyTracker.countSuppliesUsed(Product.getProduct("Hamburger"), generatedPurchase.getNumberHamburger());
            supplyTracker.countSuppliesUsed(Product.getProduct("Chicken Strips"), generatedPurchase.getNumberStrip());
            supplyTracker.countSuppliesUsed(Product.getProduct("Chicken Sandwich"), generatedPurchase.getNumberSandwich());
            if (!supplyTracker.suppliesExceeded()) {
                generatedPurchaseHistory.add(generatedPurchase);
                purchaseCount++;
            } else {
                supplyNotExceeded = false;
            }
        }
        generatedPurchaseHistory.setSupplyCost(supplyTracker.getSupplyCost());
        return generatedPurchaseHistory;
    }

    /**Generates a single purchase given a year and month.
     * See: {@link Purchase}
     * @param year The year the purchase was made.
     * @param month the month the purchase was made.
     * @return Randomly generated purchase with date and product information.
     */
    static Purchase generatePurchase(int year, int month) {
        LocalDate dateInfo = Generate.generateDate(year, month) ;
        ArrayList<Product> productList = generateProducts();
        return new Purchase(dateInfo, productList);
    }

    /**Generates a list of products for use with {@link Generate#generatePurchase}.
     * See: {@link Product}
     * @return Array List containing 1 to 4 random products.
     */
    private static ArrayList<Product> generateProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        int productCount = RANDOM.nextInt(3) + 1;
        for (int count = 0; count < productCount; count++) {
            int product = RANDOM.nextInt(3);
            Product p = Product.getProduct("Chicken Strips");
            switch (product) {
                case 0 -> productList.add(Product.getProduct("Chicken Sandwich"));
                case 1 -> productList.add(Product.getProduct("Chicken Strips"));
                case 2 -> productList.add(Product.getProduct("Hamburger"));
            }
        }
        return productList;
    }

    /**Generates a {@link LocalDate} given a year and month.
     * @param year The year for the generated date.
     * @param month The month for the generated date.
     * @return Local date object with given year and month, with a randomly generated day.
     */
    static LocalDate generateDate(int year, int month) {
        //Ensures there aren't any errors with incorrect day month combinations
        int day = switch (month) {
            case 2 -> RANDOM.nextInt(27) + 1;
            case 4, 5, 9, 11 -> RANDOM.nextInt(29) + 1;
            default -> RANDOM.nextInt(30) + 1;
        };
        return LocalDate.of(year, month, day);
    }
}
