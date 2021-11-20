package com.github.sqlsalesproject.sale;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Purchase implements Comparable<Purchase> {
    /**Total production cost of the purchase*/
    private final double PRODUCTION_COST;
    /**The date the purchase occurred*/
    private final LocalDate PURCHASE_DATE; //In the format of hour:minute AM/PM month-day-year
    /**The total sale price of the purchase*/
    private final double SALE_PRICE;
    /**The number of sandwiches purchased*/
    private final int SANDWICH_AMOUNT;
    /**The number of chicken strips purchased*/
    private final int STRIP_AMOUNT;
    /**The number of hamburgers purchased*/
    private final int HAMBURGER_AMOUNT;

    /** Purchase Constructor.
     * @param purchaseDate The date of the purchase in form yyyy-mm-dd
     * @param productsPurchased A list of all products purchased.
     */
    public Purchase(String purchaseDate, ArrayList<Product> productsPurchased) {
        LocalDate localPurchaseDate;
        //Date Information in form yyyy-mm-dd
        //Try Catch is there to ensure that if a bad string is passed in, the program will catch it
        //and continue on with a correctly formatted date string that will work
        try{
            localPurchaseDate = LocalDate.parse(purchaseDate);
        } catch (DateTimeParseException e) {
            System.err.println("ERR: Incorrect date string used when initializing purchase, " +
                    "using 1111-11-11 instead!");
            localPurchaseDate = LocalDate.of(1111,11,11);
        }
        PURCHASE_DATE = localPurchaseDate;
        SALE_PRICE = calcTotalSalePrice(productsPurchased);
        PRODUCTION_COST = calcTotalProductionCost(productsPurchased);
        SANDWICH_AMOUNT = getNumberProducts("chicken sandwich", productsPurchased);
        STRIP_AMOUNT = getNumberProducts("chicken strips", productsPurchased);
        HAMBURGER_AMOUNT = getNumberProducts("hamburger", productsPurchased);
    }

    /** Purchase Constructor.
     * @param purchaseDate A local date storing the date of the purchase.
     * @param productsPurchased A list of all products purchased.
     */
    public Purchase(LocalDate purchaseDate, ArrayList<Product> productsPurchased) {
        PURCHASE_DATE = purchaseDate;
        SALE_PRICE = calcTotalSalePrice(productsPurchased);
        PRODUCTION_COST = calcTotalProductionCost(productsPurchased);
        // TODO - refactor to have any number of products
        SANDWICH_AMOUNT = getNumberProducts("Chicken Sandwich", productsPurchased);
        STRIP_AMOUNT = getNumberProducts("Chicken Strips", productsPurchased);
        HAMBURGER_AMOUNT = getNumberProducts("Hamburger", productsPurchased);
    }

    /** Returns the date of the purchase */
    public LocalDate getDate() {
        return PURCHASE_DATE;
    }

    /** Returns the date of purchase as a string */
    public String getDateAsString() {
        return PURCHASE_DATE.toString();
    }

    /** Compares two purchases by their dates for sorting */
    @Override
    public int compareTo(Purchase purchase) {
        return PURCHASE_DATE.compareTo(purchase.PURCHASE_DATE);
    }

    /** Calculates production cost for purchase.
     * @param productsPurchased List of all products purchased.
     * @return Total production cost of the purchase.
     */
    private double calcTotalProductionCost(ArrayList<Product> productsPurchased) {
        double totalProductionCost = 0.0;
        for (Product product : productsPurchased) {
            totalProductionCost += product.getCostToProduce();
        }
        return totalProductionCost;
    }
    /** Returns cached production cost */
    public Double getTotalProductionCost() {
        return PRODUCTION_COST;
    }

    /** Calculates Sale Price of purchase.
     * @param productsPurchased List of all products purchased.
     * @return Total sale price of the purchase.
     */
    private double calcTotalSalePrice(ArrayList<Product> productsPurchased) {
        double totalSalePrice = 0.0;
        for (Product product : productsPurchased) {
            totalSalePrice += product.getSalePrice();
        }
        return totalSalePrice;
    }
    /**Returns cached sale price*/
    public Double getTotalSalePrice () {
        return SALE_PRICE;
    }

    /** Returns the profit made from the purchase*/
    public Double getProfitMade () {
        return SALE_PRICE - PRODUCTION_COST;
    }

    //TODO - refactor to be more modular
    /** Returns number of products for given product.
     * @param productName The name of the product to count.
     * @param productList List of all products in the purchase.
     */
    private int getNumberProducts (String productName, ArrayList<Product> productList) {
        int productNumber = 0;
        for (Product product : productList) {
            if (product.toString().equals(productName)) {
                productNumber++;
            }
        }
        return productNumber;
    }

    /** Returns total amount of chicken products purchased */
    public int getNumberChicken() {
        return STRIP_AMOUNT+SANDWICH_AMOUNT;
    }
    /** Returns how many sandwiches were purchased */
    public int getNumberSandwich () {
        return SANDWICH_AMOUNT;
    }
    /** Returns how many strips were purchased */
    public int getNumberStrip () {
        return STRIP_AMOUNT;
    }
    /** Returns how many burgers were purchased */
    public int getNumberHamburger() {
        return HAMBURGER_AMOUNT;
    }
    /** Returns month of purchase */
    public Integer getMonth() {
        return PURCHASE_DATE.getMonthValue();
    }
    /** Returns day of purchase */
    public Integer getDay() {
        return PURCHASE_DATE.getDayOfMonth();
    }
    /** Returns year of purchase */
    public Integer getYear() {
        return PURCHASE_DATE.getYear();
    }
}
