package com.github.sqlsalesproject.sale;

import java.util.ArrayList;

/** Group of Purchases */
public class PurchaseHistory {
    //TODO - Remove Supply from purchase history into separate supply class
    private final ArrayList<Purchase> PURCHASES;
    private double salePrice;
    private double productionCost;
    //private final int SUPPLY_CHICKEN;
    //private final int SUPPLY_BURGER;
    private double supplyCost;
    private final int MONTH;
    //private int burgerUsed;
    //private int chickenUsed;


    /** Constructs a purchase history given a purchase array list */
    public PurchaseHistory(double supplyCost, int month) {
        PURCHASES = new ArrayList<>();
        this.supplyCost = supplyCost;
        MONTH = month;
    }
    public PurchaseHistory(double supplyCost, int month, ArrayList<Purchase> purchases) {
        PURCHASES = purchases;
        this.supplyCost = supplyCost;
        MONTH = month;
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
    public double getSalePrice() {
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
    public double getProductionCost() {
        return productionCost;
    }

    //Profit Made//
    /** Returns profit made */
    public double getProfit() {
        return getSalePrice() - supplyCost;
    }

    //Supply information//
    /** Returns total cost of supplies */
    public double getSupplyCost() {
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

    //Month Information//
    public int getMonth () {
        return MONTH;
    }
}
