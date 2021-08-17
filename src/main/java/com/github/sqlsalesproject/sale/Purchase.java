package com.github.sqlsalesproject.sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Purchase {
    //TODO - Also calculate profit made
    private GregorianCalendar dateOfPurchase; //In the format of hour:minute AM/PM month-day-year
    private ArrayList<Product> productsPurchased;

    /** Constructs a purchase with given date and product list */ //TODO - Unit Tests to make sure date is working
    public Purchase(String dateOfPurchase, ArrayList<Product> productsPurchased) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a MM-dd-yyyy"); //hour:minute AM/PM month-day-year
        this.dateOfPurchase = new GregorianCalendar();
        try {
            this.dateOfPurchase.setTime(dateFormatter.parse(dateOfPurchase));
        } catch (ParseException e) {
            System.err.println("ERR: Failed to parse Date in purchase, calender date not set!");
        }
        this.productsPurchased = productsPurchased;
    }

    /** Returns the date of the purchase */
    GregorianCalendar getDate() {
        return dateOfPurchase;
    }
    /** Returns the date of purchase as a string */
    String getDateAsString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a MM-dd-yyyy");
        return dateFormatter.format(dateOfPurchase.getTime());
    }
    /** Calculates Production Cost for purchase */
    double getTotalProductionCost() {
        double totalProductionCost = 0.0;
        if (productsPurchased.isEmpty()) {
            System.err.println("ERR: Empty product array found in getTotalProduction, returning 0.0!");
            return totalProductionCost;
        } else {
            for (Product product : productsPurchased) {
                totalProductionCost += product.getCostToProduce();
            }
            return totalProductionCost;
        }
    }
    /** Calculates Sale Price of purchase*/
    double getTotalSalePrice() {
        double totalSalePrice = 0.0;
        if (productsPurchased.isEmpty()) {
            System.err.println("ERR: Empty product array found in getTotalSalePrice, returning 0.0!");
            return totalSalePrice;
        } else {
            for (Product product : productsPurchased) {
                totalSalePrice += product.getSalePrice();
            }
            return totalSalePrice;
        }
    }
    /** Calculates the profit made from the purchase*/
    double getProfitMade () {
        return getTotalSalePrice() - getTotalProductionCost();
    }
}
