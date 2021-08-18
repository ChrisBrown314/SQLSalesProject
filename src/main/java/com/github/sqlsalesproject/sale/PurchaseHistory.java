package com.github.sqlsalesproject.sale;

import java.util.ArrayList;

/** Group of Purchases */
public class PurchaseHistory {
    private final ArrayList<Purchase> PURCHASES;
    private double saleCost;
    private double productionCost;

    /** Constructs a purchase history given a purchase array list */
    public PurchaseHistory(ArrayList<Purchase> purchases) {
        PURCHASES = purchases;
    }

    /** Calculates sale cost and stores it */
    private void calculateSaleCost () {
        double saleCost = 0.0;
        for(Purchase purchase : PURCHASES) {
            saleCost += purchase.getTotalSalePrice();
        }
        this.saleCost = saleCost;
    }
    /** Returns sale cost of all purchases */
    double getSaleCost() {
        return  saleCost;
    }

    /** Calculates production cost and stores it */
    private void calculateProductionCost () {
        double productionCost = 0.0;
        for(Purchase purchase : PURCHASES) {
            productionCost += purchase.getTotalProductionCost();
        }
        this.productionCost = productionCost;
    }
    /** Returns production cost of all purchases */
    double getProductionCost() {
        return productionCost;
    }

    /** Returns profit made */
    double getProfit() {
        return getSaleCost() - getProductionCost();
    }

    /** Stores a new purchase and recalculates everything */
    void add(Purchase purchaseToAdd) {
        PURCHASES.add(purchaseToAdd);
        calculateSaleCost();
        calculateProductionCost();
    }
}
