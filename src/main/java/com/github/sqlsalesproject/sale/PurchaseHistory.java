package com.github.sqlsalesproject.sale;

import java.util.ArrayList;

/** Group of Purchases */
public class PurchaseHistory {
    //TODO - Remove Supply from purchase history into separate supply class
    private final ArrayList<Purchase> PURCHASES;
    private double salePrice;
    private double productionCost;
    private double supplyCost;
    private final int MONTH;
    private final int YEAR;


    /** Constructs a purchase history given a purchase array list */
    public PurchaseHistory(double supplyCost, int month, int year) {
        PURCHASES = new ArrayList<>();
        this.supplyCost = supplyCost;
        MONTH = month;
        YEAR = year;
    }
    public PurchaseHistory(double supplyCost, int month, int year, ArrayList<Purchase> purchases) {
        PURCHASES = purchases;
        this.supplyCost = supplyCost;
        MONTH = month;
        YEAR = year;
        calculateSalePrice();
        calculateProductionCost();
    }

    //Sale Price//
    /** Calculates sale cost and stores it */
    private void calculateSalePrice() {
        double salePrice = 0.0;
        for(Purchase purchase : PURCHASES) {
            salePrice += purchase.getTotalSalePrice();
        }
        this.salePrice = salePrice;
    }
    /** Returns sale cost of all purchases */
    public Double getSalePrice() {
        return salePrice;
    }

    //Production Cost//
    /** Calculates production cost and stores it */
    private void calculateProductionCost () {
        double productionCost = 0.0;
        for(Purchase purchase : PURCHASES) {
            productionCost += purchase.getTotalProductionCost();
        }
        this.productionCost = productionCost;
    }
    /** Returns production cost of all purchases */
    public Double getProductionCost() {
        return productionCost;
    }

    //Profit Made//
    /** Returns profit made */
    public Double getProfit() {
        return getSalePrice() - supplyCost;
    }

    //Supply information//
    /** Returns total cost of supplies */
    public Double getSupplyCost() {
        return supplyCost;
    }
    public void setSupplyCost (double supplyCost) {
        this.supplyCost = supplyCost;
    }

    //Purchase Information//
    /** Stores a new purchase and recalculates everything */
    void add(Purchase purchaseToAdd) {
        PURCHASES.add(purchaseToAdd);
        calculateSalePrice();
        calculateProductionCost();
    }
    /** Returns a list of all purchases in the purchase history array */
    public ArrayList<Purchase> getAllPurchases() {
        return PURCHASES;
    }
    /** Gets number of purchases */
    public Integer getNumPurchases() {
        return  getAllPurchases().size();
    }
    //Month Information//
    /** Returns month of purchase history */
    public Integer getMonth() {
        return MONTH;
    }
    /** Returns year of purchase history*/
    public Integer getYear() {
        return YEAR;
    }

    //Get Products Purchased //
    public double getAllBurgers() {
        int burgers = 0;
        for (Purchase purchase : getAllPurchases()) {
            burgers += purchase.getNumberHamburger();
        }
        return burgers;
    }
    public double getAllChicken () {
        int chickens = 0;
        for (Purchase purchase : getAllPurchases()) {
            chickens += purchase.getNumberChicken();
        }
        return chickens;
    }
}
