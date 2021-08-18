package com.github.sqlsalesproject.sale;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Purchase implements Comparable<Purchase> {
    //TODO - Possibly round the prices and profit to two decimal points
    private final LocalDate PURCHASE_DATE; //In the format of hour:minute AM/PM month-day-year
    private final double SALE_PRICE;
    private final double PRODUCTION_COST;

    /** Constructs a purchase with given string and product list */
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
        //Calculation caching
        SALE_PRICE = calcTotalSalePrice(productsPurchased);
        PRODUCTION_COST = calcTotalProductionCost(productsPurchased);
    }
    /** Constructs a purchase from a local date and products purchased*/
    public Purchase(LocalDate purchaseDate, ArrayList<Product> productsPurchased) {
        PURCHASE_DATE = purchaseDate;
        SALE_PRICE = calcTotalSalePrice(productsPurchased);
        PRODUCTION_COST = calcTotalProductionCost(productsPurchased);
    }

    /** Returns the date of the purchase */
    LocalDate getDate() {
        return PURCHASE_DATE;
    }
    /** Returns the date of purchase as a string */
    String getDateAsString() {
        return PURCHASE_DATE.toString();
    }
    /** Compares two purchases by their dates for sorting */
    @Override
    public int compareTo(Purchase purchase) {
        return PURCHASE_DATE.compareTo(purchase.PURCHASE_DATE);
    }

    /** Calculates Production Cost for purchase */
    private double calcTotalProductionCost(ArrayList<Product> productsPurchased) {
        double totalProductionCost = 0.0;
        if (productsPurchased.isEmpty()) {
            System.err.println("ERR: Empty product array found in getTotalProduction, returning 0.0!");
        } else {
            for (Product product : productsPurchased) {
                totalProductionCost += product.getCostToProduce();
            }
        }
        return totalProductionCost;
    }
    /** Returns cached production cost */
    double getTotalProductionCost() {
        return PRODUCTION_COST;
    }

    /** Calculates Sale Price of purchase*/
    private double calcTotalSalePrice(ArrayList<Product> productsPurchased) {
        double totalSalePrice = 0.0;
        if (productsPurchased.isEmpty()) {
            System.err.println("ERR: Empty product array found in getTotalSalePrice, returning 0.0!");
        } else {
            for (Product product : productsPurchased) {
                totalSalePrice += product.getSalePrice();
            }
        }
        return totalSalePrice;
    }
    /**Returns cached sale price*/
    double getTotalSalePrice () {
        return SALE_PRICE;
    }

    /** Calculates the profit made from the purchase*/
    double getProfitMade () {
        return SALE_PRICE - PRODUCTION_COST;
    }
}
