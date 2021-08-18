package com.github.sqlsalesproject.sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Purchase implements Comparable<Purchase> {
    //TODO - Add constructor w/ GregorianCalendar
    //TODO - Possibly round the prices and profit to two decimal points
    private final GregorianCalendar PURCHASE_DATE; //In the format of hour:minute AM/PM month-day-year
    private final double SALE_PRICE;
    private final double PRODUCTION_COST;

    /** Constructs a purchase with given date and product list */
    public Purchase(String purchaseDate, ArrayList<Product> productsPurchased) {
        //Date Information
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a MM-dd-yyyy"); //hour:minute AM/PM month-day-year
        PURCHASE_DATE = new GregorianCalendar();
        try {
            PURCHASE_DATE.setTime(dateFormatter.parse(purchaseDate));
        } catch (ParseException e) {
            System.err.println("ERR: Failed to parse Date in purchase, calender date not set!");
        }
        //Calculation caching
        SALE_PRICE = calcTotalSalePrice(productsPurchased);
        PRODUCTION_COST = calcTotalProductionCost(productsPurchased);
    }

    /** Returns the date of the purchase */
    GregorianCalendar getDate() {
        return PURCHASE_DATE;
    }
    /** Returns the date of purchase as a string */
    String getDateAsString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a MM-dd-yyyy");
        return dateFormatter.format(PURCHASE_DATE.getTime());
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
