package com.github.sqlsalesproject.sale;

import com.github.sqlsalesproject.tools.OverSupplyLimitException;

import java.util.ArrayList;

/** Group of Purchases */
public class PurchaseHistory {
    //TODO - make more scalable and modular
    private final ArrayList<Purchase> PURCHASES;
    private double saleCost;
    private double productionCost;
    private final int SUPPLY_CHICKEN;
    private final int SUPPLY_BURGER;
    private final double SUPPLY_COST;
    private int burgerUsed;
    private int chickenUsed;

    /** Constructs a purchase history given a purchase array list */
    public PurchaseHistory(int supplyBurger, int supplyChicken) {
        PURCHASES = new ArrayList<>();
        SUPPLY_BURGER = supplyBurger;
        SUPPLY_CHICKEN = supplyChicken;
        SUPPLY_COST = (supplyBurger * Product.HAMBURGER.getCostToProduce()) + (supplyChicken * Product.CHICKEN_SANDWICH.getCostToProduce());
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
        return getSaleCost() - SUPPLY_COST;
    }
    /** Returns total cost of supplies */
    double getSupplyCost() {
        return SUPPLY_COST;
    }

    /** Stores a new purchase and recalculates everything */
    void add(Purchase purchaseToAdd) throws OverSupplyLimitException {
        burgerUsed += purchaseToAdd.getNumberHamburger();
        chickenUsed += purchaseToAdd.getNumberChicken();
        if (exceedsSupply(purchaseToAdd)) {
            throw new OverSupplyLimitException("Purchase cannot be added as it exceeds supply limit!");
        } else {
            PURCHASES.add(purchaseToAdd);
            calculateSaleCost();
            calculateProductionCost();
        }
    }
    /** Tests if a purchase would exceed the supply limit */
    private boolean exceedsSupply(Purchase purchaseToTest) {
        return burgerUsed > SUPPLY_BURGER || chickenUsed > SUPPLY_CHICKEN;
    }

    /** Returns a list of all purchases in the purchase history array */
    ArrayList<Purchase> getAllPurchases() {
        return PURCHASES;
    }
}
